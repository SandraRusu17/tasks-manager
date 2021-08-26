package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.command.utils.CommandParameterParser;
import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public class GetTasksCommand implements Command {
    private String username;

    public GetTasksCommand(String username) {
        this.username = username;
    }

    private final TaskService taskService = ServiceFactory.getInstance().getTaskService();

    @Override
    public void execute() throws UserNotFoundException, InvalidCommandException {

        System.out.println("All tasks for [" + username + "] :");
        taskService.getTasksFor(username).forEach(System.out::println);
    }


}
