package com.stefanini.taskmanager.repository;


import java.util.List;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public interface TaskRepository {

    /**
     * Deletes tasks, by title, for the specified username
     *
     * @param taskTitle - a <code>String</code> representing the title of the task which will be deleted
     * @param username  - a <code>String</code> representing the username given in order to get the tasks for
     */
    void deleteTaskByTitleFor(String taskTitle, String username);

    /**
     * Finds tasks for the specified username
     *
     * @param username - a <code>String</code> representing the username given in order to get the tasks for
     */
    List<Task> getTasksFor(String username);

    /**
     * Adds tasks to a specific user, by giving his username
     *
     * @param taskTitle -a <code>String</code> representing the title of the task which will be added
     * @param taskDescription -a <code>String</code> representing the description of the task which will be added
     * @param username -a <code>String</code> representing the username of the user, for whom the task will be added
     */
    int addTaskFor(String taskTitle, String taskDescription, String username);

}
