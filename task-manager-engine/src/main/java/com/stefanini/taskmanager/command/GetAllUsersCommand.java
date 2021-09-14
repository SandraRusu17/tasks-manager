package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.UserService;

public class GetAllUsersCommand implements Command {

    private UserService userService = ServiceFactory.getInstance().getUserService();

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GetAllUsersCommand.class);

    @Override
    public void execute() {
        log.info("All users : ");
        userService.getAllUsers().forEach(System.out::println);
    }
}
