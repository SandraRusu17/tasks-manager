package com.stefanini.taskmanager.service.impl;

import java.util.List;
import java.util.Optional;

import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.repository.UserRepository;
import com.stefanini.taskmanager.service.UserService;

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
        log.info("Creating {}", user);
        userRepository.saveUser(user);
    }


    @Override
    public void deleteUserById(Long id) {
        log.info("Entered deleteUserById with id = {}", id);
        userRepository.deleteUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        log.debug("Entered getAllUsers");
        return userRepository.findAllUsers();
    }


    @Override
    public Optional<User> getById(final Long id) {
        log.info("Entered findById with id = {}", id);
        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        log.info("Entered findByUsername with username = {}", username);
        return userRepository.findByUsername(username);
    }
}
