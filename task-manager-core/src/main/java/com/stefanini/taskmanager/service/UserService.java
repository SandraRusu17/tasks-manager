package com.stefanini.taskmanager.service;

import java.util.List;
import java.util.Optional;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public interface UserService {
    int saveUser(User user);

    Optional<User> findByUsername(String username);

    void addTaskFor(Long id, String taskTitle, String taskDescription, String username) throws UserNotFoundException;

    List<Task> getTasksFor(String username) throws UserNotFoundException;

    //one more logical feature
    void deleteTaskByTitleFor(String taskTitle, String username) throws UserNotFoundException;

    List<User> getAllUsers();
}
