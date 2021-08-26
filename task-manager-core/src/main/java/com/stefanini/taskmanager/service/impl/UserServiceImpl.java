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
    public int saveUser(User user) {
        log.info("Entered saveUser with user = {}", user);
        return userRepository.saveUser(user);
    }


    @Override
    public int deleteUserById(Long id) throws UserNotFoundException {
        log.info("Entered deleteUserById with id = {}", id);
        return userRepository.deleteUserById(id);
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
