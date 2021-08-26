package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

import static com.stefanini.taskmanager.command.utils.CommandParameterParser.*;

public class AddTaskCommand implements Command {

    private String taskTitle;
    private String taskDescription;
    private String username;

    public AddTaskCommand(String taskTitle, String taskDescription, String username) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.username = username;
    }


    private final TaskService taskService = ServiceFactory.getInstance().getTaskService();


    @Override
    public void execute() throws UserNotFoundException, InvalidCommandException {

        taskService.addTaskFor(taskTitle, taskDescription, username);
        System.out.println("Task [" + taskTitle + "] created successfully");
    }
}

