package com.stefanini.taskmanager.command.utils;

import com.stefanini.taskmanager.command.utils.Command;
import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.service.UserService;

public class GetAllUsersCommand implements Command {

    private UserService userService = ServiceFactory.getInstance().getUserService();


    @Override
    public void execute() {
        System.out.println("All users : ");
        userService.getAllUsers().forEach(System.out::println);
    }
}
