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
    public int deleteUserById(Long id) throws UserNotFoundException{
        return userRepository.deleteUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        logger.debug("Entered getAllUsers");
        return userRepository.findAllUsers();
    }


    @Override
    public Optional<User> findByUsername(String username){
        logger.info("Entered findByUsername with username = {}", username);
       return this.userRepository.findByUsername(username);
    }
}
