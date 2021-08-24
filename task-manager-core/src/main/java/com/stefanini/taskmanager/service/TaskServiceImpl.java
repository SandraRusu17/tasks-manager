package com.stefanini.taskmanager.service;

import java.util.List;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.repository.TaskRepository;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TaskServiceImpl implements TaskService {
    private static Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    public static TaskServiceImpl INSTANCE;

    private final TaskRepository taskRepository;

    private TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public static TaskServiceImpl getInstance(TaskRepository taskRepository) {
        if (INSTANCE == null) {
            INSTANCE = new TaskServiceImpl(taskRepository);
        }

        return INSTANCE;
    }


    @Override
    public List<Task> getTasksFor(String username) {
        return taskRepository.getTasksFor(username);
    }


    //one more logical feature
    @Override
    public void deleteTaskByTitleFor(String taskTitle, String username) {
        taskRepository.deleteTaskByTitleFor(taskTitle, username);
    }

    @Override
    public int addTaskFor(String taskTitle, String taskDescription, String username) throws UserNotFoundException {
        return taskRepository.saveTaskFor(new Task(taskTitle, taskDescription), username);
    }


}
