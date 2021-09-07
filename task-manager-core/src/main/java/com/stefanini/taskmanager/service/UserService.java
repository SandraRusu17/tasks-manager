package com.stefanini.taskmanager.service;

import java.util.List;
import java.util.Optional;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public interface UserService {

    /**
     * Saves a user in database
     *
     * @param user - a <code>User</code>  representing the user with firstName, lastName and username
     * @return the saved user
     */
    void saveUser(User user);

    /**
     * Returns a user, by his username
     *
     * @param username  - a <code>String</code> representing the username given in order to find the user
     * @return the user, with his id, firstName, lastName and userName
     */
    User findByUsername(String username) throws UserNotFoundException;


    /**
     * Returns a user, by his username
     *
     * @param id  - a <code>Long</code> representing the id given in order to find the user
     * @return the user, with his id, firstName, lastName and userName
     */
    Optional<User> getById(Long id) throws UserNotFoundException;


    /**
     * Deletes an user by his id
     *
     * @param id- a <code>Long</code> representing the user's id
     * @return int the operation is executed
     */
    //one more logical feature
    void deleteUserById(Long id) throws UserNotFoundException;


    /**
     * Gets all the users from database
     *
     * @return all the users from database
     */
    List<User> getAllUsers();
}
