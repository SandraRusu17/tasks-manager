package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.UserService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class GetAllUsersCommand implements Command {

    private UserService userService = ServiceFactory.getInstance().getUserService();


    @Override
    public void execute() {
        log.info("All users : ");
        userService.getAllUsers().forEach(System.out::println);
    }
}
