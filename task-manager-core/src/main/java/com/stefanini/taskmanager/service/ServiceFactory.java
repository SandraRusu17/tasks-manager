package com.stefanini.taskmanager.service;


import com.stefanini.taskmanager.repository.RepositoryFactory;
import com.stefanini.taskmanager.service.impl.EmailServiceImpl;
import com.stefanini.taskmanager.service.impl.TaskServiceImpl;
import com.stefanini.taskmanager.service.impl.UserServiceImpl;

import java.lang.reflect.Proxy;

public class ServiceFactory {
    private static ServiceFactory INSTANCE;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceFactory();
        }

        return INSTANCE;
    }

    public UserService getUserService() {
     return UserServiceImpl.getInstance(RepositoryFactory.getInstance().getUserRepository());
    }

    public EmailService getEmailService(){
        return EmailServiceImpl.getInstance();
    }

    public TaskService getTaskService() {
        return TaskServiceImpl.getInstance(RepositoryFactory.getInstance().getTaskRepository());
    }
}
