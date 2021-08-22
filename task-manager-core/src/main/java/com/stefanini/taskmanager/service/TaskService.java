package com.stefanini.taskmanager.service;

import java.util.List;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public interface TaskService {

    List<Task> getTasksFor(String username) throws UserNotFoundException;

    //one more logical feature
    void deleteTaskByTitleFor(String taskTitle, String username)  throws UserNotFoundException;

    int addTaskFor(String taskTitle, String taskDescription, String username) throws UserNotFoundException;


}
