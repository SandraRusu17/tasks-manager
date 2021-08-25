package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

import static com.stefanini.taskmanager.command.utils.CommandParameterParser.*;

public class AddTaskCommand implements Command {
    private final TaskService taskService = ServiceFactory.getInstance().getTaskService();

    private final String[] commandAndParameters;

    public AddTaskCommand(final String[] commandAndParameters) {
        this.commandAndParameters = commandAndParameters;
    }

    @Override
    public void execute() throws UserNotFoundException, InvalidCommandException {
        if (commandAndParameters.length < 4) {
            throw new InvalidCommandException(
                    "Oops. Please refer to the usage of the command : -addTask -un='UserName' -tt='TaskTitle' -td='TaskDescription'");
        }
        taskService.addTaskFor( getTaskTitle(commandAndParameters),
                                getTaskDescription(commandAndParameters),
                                getUsername(commandAndParameters));
        System.out.println("Task [" + getTaskTitle(commandAndParameters) + "] created successfully");
        }
    }

