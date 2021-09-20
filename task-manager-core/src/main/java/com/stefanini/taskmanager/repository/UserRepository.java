package com.stefanini.taskmanager.repository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;

public interface UserRepository {

    /**
     * Saves a user in database
     *
     * @param user -(User) the user with firstName, lastName and username
     */
    void saveUser(User user);

    /**
     * Returns a user, by his username
     *
     * @param username -(String) the username given in order to find the user
     * @return
     */
    User findByUsername(String username);


    /**
     * Returns a user, by his username
     *
     * @param id -(Long) the id given in order to find the user
     */
    Optional<User> findById(Long id);


    /**
     * Gets all the users from database
     */
    List<User> findAllUsers();

    /**
     * Updates an user
     *
     * @param user - (User) the user given in order to be updated
     */
    void update(User user);

    /**
     * Deletes an user by his id
     *
     * @param id -(String) the user's id
     * @return
     */
    void deleteUserById(Long id);


    Stream<User> streamAll();

}
