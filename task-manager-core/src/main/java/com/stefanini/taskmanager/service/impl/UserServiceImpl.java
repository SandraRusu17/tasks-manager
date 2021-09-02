package com.stefanini.taskmanager.service.impl;

import java.util.List;
import java.util.Optional;

import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.repository.UserRepository;
import com.stefanini.taskmanager.service.UserService;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserService {
    public static UserServiceImpl INSTANCE;

    private final UserRepository userRepository;

    private UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static UserServiceImpl getInstance(UserRepository userRepository) {
        if (INSTANCE == null) {
            INSTANCE = new UserServiceImpl(userRepository);
        }

        return INSTANCE;
    }


    @Override
    public void saveUser(User user) {
        log.info("Entered saveUser with user = {}", user);
        userRepository.saveUser(user);
    }


    @Override
    public void deleteUserByUsername(String username) throws UserNotFoundException {
        log.info("Entered deleteUserById with username = {}", username);
        userRepository.deleteUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        log.debug("Entered getAllUsers");
        return userRepository.findAllUsers();
    }


    @Override
    public Optional<User> findByUsername(String username) {
        log.info("Entered findByUsername with username = {}", username);
        return this.userRepository.findByUsername(username);
    }
}
