package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.UserService;

public class GetAllUsersCommand implements Command, Runnable {

    private UserService userService = ServiceFactory.getInstance().getUserService();

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GetAllUsersCommand.class);

    @Override
    public void execute() throws InvalidCommandException{
        log.info("All users : ");
        userService.getAllUsers().forEach(System.out::println);
    }

    @Override
    public void run() {
        try {
            execute();
        } catch (InvalidCommandException e) {
            System.out.println("Something bad happened during getting all users : " + e.getMessage());
            log.trace(e.getMessage(), e);
        }
    }
}
