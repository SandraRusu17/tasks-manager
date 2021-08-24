package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public class AddTaskCommand implements Command {
    public String titleTask;
    public String taskDescription;
    public String username;

    private TaskService taskService = ServiceFactory.getInstance().getTaskService();


    public AddTaskCommand(String titleTask, String taskDescription, String username) {
        this.titleTask = titleTask;
        this.taskDescription = taskDescription;
        this.username = username;
    }

    @Override
    public void execute() throws UserNotFoundException {

        try {
            taskService.addTaskFor(titleTask, taskDescription, username);
        } catch (UserNotFoundException e) {
            System.out.println("Oops. User with username " + username + " not found!");
        }
    }

}
