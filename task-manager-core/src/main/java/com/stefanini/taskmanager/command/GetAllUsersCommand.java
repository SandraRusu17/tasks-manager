package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.service.UserService;

public class GetAllUsersCommand implements Command {

    private UserService userService = ServiceFactory.getInstance().getUserService();


    @Override
    public void execute() {

        userService.getAllUsers().forEach(System.out::println);
    }
}
