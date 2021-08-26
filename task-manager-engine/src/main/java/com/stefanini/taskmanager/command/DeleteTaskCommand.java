package com.stefanini.taskmanager.command;

import com.stefanini.taskmanager.command.exceptions.InvalidCommandException;
import com.stefanini.taskmanager.factory.ServiceFactory;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;

import static com.stefanini.taskmanager.command.utils.CommandParameterParser.getTaskTitle;
import static com.stefanini.taskmanager.command.utils.CommandParameterParser.getUsername;


@Slf4j
public class DeleteTaskCommand implements Command {
    private String taskTitle;
    private String username;

    public DeleteTaskCommand(String taskTitle, String username) {
        this.taskTitle = taskTitle;
        this.username = username;
    }


    private final TaskService taskService = ServiceFactory.getInstance().getTaskService();


    @Override
    public void execute() throws UserNotFoundException, InvalidCommandException {
        taskService.deleteTaskByTitleFor(taskTitle, username);
        log.info("Task [" + taskTitle + "] deleted successfully");
    }
}
