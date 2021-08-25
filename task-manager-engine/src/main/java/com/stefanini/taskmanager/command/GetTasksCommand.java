package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.command.utils.CommandParameterParser;
import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public class GetTasksCommand implements Command {
    private final TaskService taskService = ServiceFactory.getInstance().getTaskService();

    private final String[] commandAndParameters;


    public GetTasksCommand(final String[] commandAndParameters) {
        this.commandAndParameters = commandAndParameters;
    }

    @Override
    public void execute() throws UserNotFoundException, InvalidCommandException {
        if (commandAndParameters.length < 2) {
            throw new InvalidCommandException(
                    "Oops. Please refer to the usage of the command : -showTasks -un='UserName'");
        }
        final String username = CommandParameterParser.getUsername(commandAndParameters);
        System.out.println("All tasks for [" + username + "] :");
        taskService.getTasksFor(username).forEach(System.out::println);
    }


}
