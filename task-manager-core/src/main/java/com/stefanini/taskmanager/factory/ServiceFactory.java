package com.stefanini.taskmanager.factory;


import com.stefanini.taskmanager.repository.TaskRepository;
import com.stefanini.taskmanager.repository.UserRepository;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.TaskServiceImpl;
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

    public TaskService getTaskService() {
        return TaskServiceImpl.getInstance(RepositoryFactory.getInstance().getTaskRepository());
    }
}
