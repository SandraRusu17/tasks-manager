package com.stefanini.taskmanager.service;

import java.util.List;
import java.util.Optional;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.repository.UserRepository;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserServiceImpl implements UserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public static UserServiceImpl INSTANCE;

    private final UserRepository userRepository;

    private UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static UserServiceImpl getInstance(UserRepository userRepository) {
        if(INSTANCE == null) {
            INSTANCE = new UserServiceImpl(userRepository);
        }

        return INSTANCE;
    }


    @Override
    public int saveUser(User user){
        return userRepository.saveUser(user);
    }

    @Override
    public void addTaskFor(Long id,String taskTitle, String taskDescription, String username) throws UserNotFoundException {
        Task task = new Task(id,taskTitle, taskDescription);
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        user.addTask(task);
        userRepository.update(user);
        System.out.println(task + " added successfully!");
    }

    @Override
    public List<Task> getTasksFor(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return user.getTasks();
    }

    //one more logical feature
    @Override
    public void deleteTaskByTitleFor(String taskTitle, String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        user.getTasks().removeIf(t -> t.getTitle().equals(taskTitle));
        userRepository.update(user);
        System.out.println(taskTitle + " deleted successfully!");
    }

    @Override
    public List<User> getAllUsers() {
        logger.debug("Entered getAllUsers");
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username){
        logger.info("Entered findByUsername with username = {}", username);
       return this.userRepository.findByUsername(username);
    }
}
