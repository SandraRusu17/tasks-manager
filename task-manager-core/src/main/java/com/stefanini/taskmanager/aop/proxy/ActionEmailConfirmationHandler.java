package com.stefanini.taskmanager.aop.proxy;

import com.stefanini.taskmanager.annotations.ActionEmailConfirmation;
import com.stefanini.taskmanager.entity.Email;
import com.stefanini.taskmanager.service.EmailService;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class ActionEmailConfirmationHandler implements InvocationHandler {
    private final UserService target;
    private final EmailService emailService = ServiceFactory.getInstance().getEmailService();

    public ActionEmailConfirmationHandler(final UserService target) {
        this.target = target;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        if (args != null)
            for (final Object arg : args) {
                if (method.getName().equals("saveUser") && arg.getClass().isAnnotationPresent(ActionEmailConfirmation.class)) {
                    final ActionEmailConfirmation annotation = arg.getClass().getAnnotation(ActionEmailConfirmation.class);
                    log.info("Sending email to {}", Arrays.toString(annotation.email()));
                    log.info("Action [{}] from {} with {} completed successfully", method.getName(),
                            proxy.getClass().getSimpleName(), Arrays.toString(args));
                    final Object result = method.invoke(target, args);

                    String[] sa = Arrays.toString(args).split(",");

                    final String content = String.format(
                            "<p> User " + sa[1] + " identified by " + sa[3] + " has been created ");

                    Arrays.stream(annotation.email()).forEach(
                            e -> emailService.sendEmail(new Email(e, "task-man action completed", content)));
                    return result;
                }
            }
        return method.invoke(target, args);
    }
}


