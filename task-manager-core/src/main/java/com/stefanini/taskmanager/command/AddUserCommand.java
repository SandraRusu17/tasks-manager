package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.service.UserService;

public class AddUserCommand implements Command{
    private String userName;
    private String firstName;
    private String lastName;

    private UserService userService = ServiceFactory.getInstance().getUserService();

    public AddUserCommand(final String userName, final String firstName, final String lastName){
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public void execute(){
        final User user = new User(userName, firstName, lastName);
        userService.saveUser(user);
        System.out.println(user + " created successfully!");
    }
}
