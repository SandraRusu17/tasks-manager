package com.stefanini.taskmanager.aspect;

import com.stefanini.taskmanager.annotations.ActionEmailConfirmation;
import com.stefanini.taskmanager.entity.Email;
import com.stefanini.taskmanager.repository.impl.TaskHibernateRepositoryImpl;
import com.stefanini.taskmanager.service.EmailService;
import com.stefanini.taskmanager.service.ServiceFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.Arrays;

@Aspect
public class EmailAspect {
    private static final EmailService emailService = ServiceFactory.getInstance().getEmailService();
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TaskHibernateRepositoryImpl.class);


    @Around("execution(* com.stefanini.taskmanager.service.impl..*.*(..)) && @args(com.stefanini.taskmanager.annotations.ActionEmailConfirmation)")
    public Object userCreationEmailConfirmation(ProceedingJoinPoint joinPoint) throws Throwable {
        final Object[] args = joinPoint.getArgs();
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final String methodName = signature.getName();
        if (methodName.equals("saveUser")) {
            Parameter[] parameters = signature.getMethod().getParameters();
            for (Parameter parameter : parameters) {
                ActionEmailConfirmation annotation = parameter.getDeclaringExecutable().getAnnotation(ActionEmailConfirmation.class);
                if (annotation != null) {
                    final Object result = joinPoint.proceed();
                    final String content = String.format(
                            "<p>User method <b>[%s]</b> with following arguments <b>%s</b> and following result <b>[%s]</b> completed successfully</p>",
                            methodName, Arrays.toString(args), result);

                    Arrays.stream(annotation.email()).forEach(
                            e -> {
                                try {
                                    emailService.sendEmail(new Email(e, "task-man action completed", content));
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            });
                    return result;
                }
            }
        }
        return joinPoint.proceed();
    }

}




