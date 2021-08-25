package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

import static com.stefanini.taskmanager.command.utils.CommandParameterParser.getTaskTitle;
import static com.stefanini.taskmanager.command.utils.CommandParameterParser.getUsername;

public class DeleteTaskCommand implements Command {

    private final TaskService taskService = ServiceFactory.getInstance().getTaskService();

    private final String[] commandAndParameters;

    public DeleteTaskCommand(final String[] commandAndParameters) {
        this.commandAndParameters = commandAndParameters;
    }


    @Override
    public void execute() throws UserNotFoundException, InvalidCommandException {
       if(commandAndParameters.length < 3) {
           throw new InvalidCommandException(
                   "Oops. Please refer to the usage of the command: -deleteTask -un='UserName' -tt='TaskTitle'");
       }
       final String taskTitle = getTaskTitle(commandAndParameters);
       final String username = getUsername(commandAndParameters);
       taskService.deleteTaskByTitleFor(taskTitle, username);
        System.out.println("Task [" + taskTitle + "] deleted successfully");
    }
}
