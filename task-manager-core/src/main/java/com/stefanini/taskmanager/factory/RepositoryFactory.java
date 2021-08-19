package com.stefanini.taskmanager.factory;

import com.stefanini.taskmanager.repository.UserJDBCRepositoryImpl;
import com.stefanini.taskmanager.repository.UserRepository;

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
}
