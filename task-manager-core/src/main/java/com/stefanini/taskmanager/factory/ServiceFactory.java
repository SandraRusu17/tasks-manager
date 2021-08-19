package com.stefanini.taskmanager.factory;


import com.stefanini.taskmanager.repository.UserRepository;
import com.stefanini.taskmanager.service.UserService;
import com.stefanini.taskmanager.service.UserServiceImpl;

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
}
