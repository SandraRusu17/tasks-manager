package com.stefanini.taskmanager.service;

import java.util.List;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public interface TaskService {

    /**
     * Finds tasks for the specified username
     *
     * @param username the username given in order to get the tasks for
     */
    List<Task> getTasksFor(String username) throws UserNotFoundException;


    /**
     * Deletes tasks, by title, for the specified username
     *
     * @param taskTitle the title of the task which will be deleted
     * @param username  the username given in order to get the tasks for
     * @throws UserNotFoundException when the user is not found, this exception is thrown
     */
    //one more logical feature
    void deleteTaskByTitleFor(String taskTitle, String username)  throws UserNotFoundException;


    /**
     * Adds tasks to a specific user, by giving his username
     *
     * @param taskTitle the title of the task which will be added
     * @param taskDescription the description of the task which will be added
     * @param username the username of the user, for whom the task will be added
     * @throws UserNotFoundException when the user is not found, this exception is thrown
     */
    int addTaskFor(String taskTitle, String taskDescription, String username) throws UserNotFoundException;


}
