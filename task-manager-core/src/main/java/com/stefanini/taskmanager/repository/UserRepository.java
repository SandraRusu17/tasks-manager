package com.stefanini.taskmanager.repository;


import java.util.List;
import java.util.Optional;

import com.stefanini.taskmanager.entity.User;

public interface UserRepository {

    /**
     * Saves a user in database
     *
     * @param user the user with firstName, lastName and username
     */
    int saveUser(User user);

    /**
     * Returns a user, by his username
     *
     * @param username the username given in order to find the user
     */
    Optional<User> findByUsername(String username);

    /**
     * Gets all the users from database
     */
    List<User> findAllUsers();

    void update(User user);

    /**
     * Deletes an user by his id
     *
     * @param id the user's id
     */
    int deleteUserById(Long id);




}
