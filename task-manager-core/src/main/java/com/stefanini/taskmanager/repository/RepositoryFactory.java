package com.stefanini.taskmanager.repository;

import com.stefanini.taskmanager.repository.impl.TaskJDBCRepositoryImpl;
import com.stefanini.taskmanager.repository.impl.UserJDBCRepositoryImpl;

public class RepositoryFactory {
    private static RepositoryFactory INSTANCE;

    private RepositoryFactory() {
    }

    public static RepositoryFactory getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RepositoryFactory();
        }

        return INSTANCE;
    }

    public UserRepository getUserRepository(){
//        return UserFileRepositoryImpl.getInstance();
        return UserJDBCRepositoryImpl.getInstance();
    }

    public TaskRepository getTaskRepository(){
//        return UserFileRepositoryImpl.getInstance();
        return TaskJDBCRepositoryImpl.getInstance();
    }
}
