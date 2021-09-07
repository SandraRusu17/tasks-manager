package com.stefanini.taskmanager.repository;

import com.stefanini.taskmanager.repository.impl.TaskHibernateRepositoryImpl;
import com.stefanini.taskmanager.repository.impl.TaskJDBCRepositoryImpl;
import com.stefanini.taskmanager.repository.impl.UserHibernateRepositoryImpl;

public class RepositoryFactory {
    private static RepositoryFactory INSTANCE;

    private RepositoryFactory() {
    }

    public static RepositoryFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RepositoryFactory();
        }

        return INSTANCE;
    }

    public UserRepository getUserRepository() {
//        return UserFileRepositoryImpl.getInstance();
        return UserHibernateRepositoryImpl.getInstance();
    }

    public TaskRepository getTaskRepository() {
//        return UserFileRepositoryImpl.getInstance();
        return TaskHibernateRepositoryImpl.getInstance();
    }
}
