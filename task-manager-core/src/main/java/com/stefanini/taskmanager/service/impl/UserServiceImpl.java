package com.stefanini.taskmanager.service.impl;

import com.stefanini.taskmanager.annotations.ActionEmailConfirmation;
import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.repository.UserRepository;
import com.stefanini.taskmanager.service.UserService;

import java.util.List;
import java.util.Optional;


public class UserServiceImpl implements UserService {
    public static UserServiceImpl INSTANCE;

    private final UserRepository userRepository;


    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

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
    @ActionEmailConfirmation(email = {"sandra.rusu17@gmail.com", "sandra.rusu@stefanini.com"})
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

    @Override
    public void createAndAssign(User user, Task task) {
        log.info("Creating {} with asigned {}", user, task);
        user.addTask(task);
        userRepository.saveUser(user);
    }

    @Override
    public void assignDefaultTask() {
        userRepository.streamAll().filter(u -> u.getTasks().isEmpty()).forEach(u -> {
            u.addTask(new Task("To do", "Empty"));
            userRepository.saveUser(u);
        });
    }


}
