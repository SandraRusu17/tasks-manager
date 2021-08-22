package com.stefanini.taskmanager.service;

import java.util.List;
import java.util.Optional;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public interface UserService {
    int saveUser(User user);

    Optional<User> findByUsername(String username);

    //one more logical feature
    int deleteUserById(Long id) throws UserNotFoundException;

    List<User> getAllUsers();
}
