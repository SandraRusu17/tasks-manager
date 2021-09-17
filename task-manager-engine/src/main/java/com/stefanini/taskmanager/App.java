package com.stefanini.taskmanager;


import com.stefanini.taskmanager.command.Command;
import com.stefanini.taskmanager.command.CommandFactory;
import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.UserService;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;
import java.io.IOException;


public class App {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws InvalidCommandException {

        Command command = CommandFactory.parseCommandArguments(args);
        try {
            command.execute();
        } catch (InvalidCommandException | UserNotFoundException e) {
            log.error("Something bad happened during entering command",e.getMessage());
            System.exit(0);
        }

    }
}

