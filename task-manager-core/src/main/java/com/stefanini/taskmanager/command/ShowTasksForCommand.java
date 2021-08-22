package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.UserService;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public class ShowTasksForCommand implements Command{
    private String userName;

    private TaskService taskService = ServiceFactory.getInstance().getTaskService();

    public ShowTasksForCommand(final String userName){

        this.userName = userName;
    }

    @Override
    public void execute() throws UserNotFoundException {
        taskService.getTasksFor(userName).forEach(System.out::println);
    }


}
