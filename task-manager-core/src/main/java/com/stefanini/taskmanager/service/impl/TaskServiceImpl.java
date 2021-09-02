package com.stefanini.taskmanager.service.impl;

import java.util.List;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.repository.TaskRepository;
import com.stefanini.taskmanager.service.TaskService;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskServiceImpl implements TaskService {

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
        log.info("Entered getTasksFor with username = {}", username);
        return taskRepository.getTasksFor(username);
    }


    //one more logical feature
    @Override
    public void deleteTaskByTitleFor(String taskTitle, String username) {
        log.info("Entered deleteTaskByTitleFor with taskTitle = {} and username = {}", taskTitle, username);
        taskRepository.deleteTaskByTitleFor(taskTitle, username);
    }

    @Override
    public void addTaskFor(String taskTitle, String taskDescription, String username) throws UserNotFoundException {
        log.info("Entered addTaskFor with taskTitle = {} , taskDescription = {} and username = {}", taskTitle, taskDescription, username);
        taskRepository.saveTaskFor(new Task(taskTitle, taskDescription), username);
    }


}
