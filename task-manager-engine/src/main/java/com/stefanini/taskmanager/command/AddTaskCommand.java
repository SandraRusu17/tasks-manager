package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public class AddTaskCommand implements Command, Runnable {

    private String taskTitle;
    private String taskDescription;
    private String username;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AddTaskCommand.class);

    public AddTaskCommand(String taskTitle, String taskDescription, String username) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.username = username;
    }


    private final TaskService taskService = ServiceFactory.getInstance().getTaskService();


    @Override
    public void execute() throws UserNotFoundException, InvalidCommandException {

        taskService.addTaskFor(taskTitle, taskDescription, username);
        log.info("Task [" + taskTitle + "] created successfully");
    }

    @Override
    public void run() {
        try {
            execute();
        } catch (InvalidCommandException | UserNotFoundException e) {
            System.out.println("Something bad happened during adding tasks : " + e.getMessage());
            log.trace(e.getMessage(), e);
        }
    }
}

