package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.service.ServiceFactory;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public class GetTasksCommand implements Command, Runnable {
    private String username;

    public GetTasksCommand(String username) {
        this.username = username;
    }

    private final TaskService taskService = ServiceFactory.getInstance().getTaskService();

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AddUserCommand.class);


    @Override
    public void execute() throws UserNotFoundException, InvalidCommandException {

        System.out.println("All tasks for [" + username + "] :");
        taskService.getTasksFor(username).forEach(System.out::println);
    }

    @Override
    public void run() {
        try {
            execute();
        } catch (InvalidCommandException | UserNotFoundException e) {
            System.out.println("Something bad happened during getting tasks : " + e.getMessage());
            log.trace(e.getMessage(), e);
        }
    }
}
