package com.stefanini.taskmanager.repository;


import java.util.List;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

public interface TaskRepository {


    /**
     * Finds all tasks
     *
     */
    public List<Task> findAllTasks();

    /**
     * Deletes tasks
     *
     * @param task - a <code>Task</code> representing the task which will be deleted
     */
    void saveTask(final Task task);


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
     * @param task -a <code>Task</code> representing the task which will be added
     * @param username -a <code>String</code> representing the username of the user, for whom the task will be added
     */
    void saveTaskFor(Task task, String username);
}
