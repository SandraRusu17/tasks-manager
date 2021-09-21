package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.UserService;

public class AddUserCommand implements Command, Runnable {

    private String username;
    private String firstName;
    private String lastName;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AddUserCommand.class);

    private UserService userService = ServiceFactory.getInstance().getUserService();

    public AddUserCommand(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    @Override
    public void execute() throws InvalidCommandException{
        final User user = new User(username, firstName, lastName);
        userService.saveUser(user);
        log.info(user + "created successfully");
    }

    @Override
    public void run(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            execute();
        } catch (InvalidCommandException e) {
            System.out.println("Something bad happened during adding a user : " + e.getMessage());
            log.trace(e.getMessage(), e);
        }
    }
}
