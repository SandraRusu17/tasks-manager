package com.stefanini.taskmanager.command;

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
