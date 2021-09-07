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
    public List<Task> findAllTasks() {
        log.info("Entered findAllTasks ");
        return taskRepository.findAllTasks();
    }

    @Override
    public List<Task> getTasksFor(String username) {
        log.info("Entered getTasksFor with username = {}", username);
        return taskRepository.getTasksFor(username);
    }

    @Override
    public Task deleteTaskByTitleFor(String taskTitle, String username) {
        log.info("Entered deleteTaskByTitleFor with taskTitle = {} and username = {}", taskTitle, username);
        taskRepository.deleteTaskByTitleFor(taskTitle, username);
        return null;
    }

    @Override
    public void addTaskFor(String taskTitle, String taskDescription, String username) {
        log.info("Entered addTaskFor with taskTitle = {} , taskDescription = {} and username = {}", taskTitle, taskDescription, username);
        taskRepository.saveTaskFor(new Task(taskTitle, taskDescription), username);
    }

    @Override
    public void saveTask(Task task) {
        log.info("Entered saveTask ");
        taskRepository.saveTask(task);
    }


}
