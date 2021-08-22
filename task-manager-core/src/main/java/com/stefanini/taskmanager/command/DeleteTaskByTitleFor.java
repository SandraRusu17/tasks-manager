package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public class DeleteTaskByTitleFor implements Command {
    private String userName;
    private String taskTitle;

    private TaskService taskService = ServiceFactory.getInstance().getTaskService();


    public DeleteTaskByTitleFor(String userName, String taskTitle) {
        this.userName = userName;
        this.taskTitle = taskTitle;
    }



    @Override
    public void execute() throws UserNotFoundException {
        taskService.deleteTaskByTitleFor(taskTitle,userName);
    }
}
