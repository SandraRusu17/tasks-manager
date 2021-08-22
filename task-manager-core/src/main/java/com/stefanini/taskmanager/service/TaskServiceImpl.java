package com.stefanini.taskmanager.service;

import java.util.List;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.repository.TaskRepository;
import com.stefanini.taskmanager.service.exceptions.UserNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TaskServiceImpl implements TaskService {
    private static Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    public static TaskServiceImpl INSTANCE;

    private final TaskRepository taskRepository;

    private TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public static TaskServiceImpl getInstance(TaskRepository taskRepository) {
        if (INSTANCE == null) {
            INSTANCE = new TaskServiceImpl(taskRepository);
        }

        return INSTANCE;
    }

    /**
     * Finds tasks for the specified username
     *
     * @param username the username given in order to get the tasks for
     * @return all the tasks for the given username
     */
    @Override
    public List<Task> getTasksFor(String username) {
        return taskRepository.getTasksFor(username);
    }


    /**
     * Deletes tasks, by title, for the specified username
     *
     * @param taskTitle the title of the task which will be deleted
     * @param username  the username given in order to get the tasks for
     */

    //one more logical feature
    @Override
    public void deleteTaskByTitleFor(String taskTitle, String username) {
        taskRepository.deleteTaskByTitleFor(taskTitle, username);
    }

    /**
     * Adds tasks to a specific user, by giving his username
     *
     * @param taskTitle the title of the task which will be added
     * @param taskDescription the description of the task which will be added
     * @param username the username of the user, for whom the task will be added
     * @return the added task
     */

    @Override
    public int addTaskFor(String taskTitle, String taskDescription, String username) throws UserNotFoundException {
        return taskRepository.addTaskFor(taskTitle, taskDescription, username);
    }


}
