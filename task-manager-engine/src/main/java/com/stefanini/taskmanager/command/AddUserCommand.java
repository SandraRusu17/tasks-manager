package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.service.UserService;
import lombok.extern.slf4j.Slf4j;

import static com.stefanini.taskmanager.command.utils.CommandParameterParser.*;


@Slf4j
public class AddUserCommand implements Command {

    private String username;
    private String firstName;
    private String lastName;

    private UserService userService = ServiceFactory.getInstance().getUserService();

    public AddUserCommand(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    @Override
    public void execute() throws InvalidCommandException {
        final User user = new User(username, firstName, lastName);
        userService.saveUser(user);
        log.info(user + "created successfully");
    }
}
