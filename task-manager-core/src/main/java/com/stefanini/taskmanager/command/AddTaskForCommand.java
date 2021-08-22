package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public class AddTaskForCommand implements Command {
    public String titleTask;
    public String taskDescription;
    public String username;

    private TaskService taskService = ServiceFactory.getInstance().getTaskService();



    public AddTaskForCommand(String titleTask, String taskDescription, String username) {
        this.titleTask = titleTask;
        this.taskDescription = taskDescription;
        this.username = username;
    }

    @Override
    public void execute() throws UserNotFoundException {

        taskService.addTaskFor(titleTask,taskDescription,username);
    }

}
