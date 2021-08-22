package com.stefanini.taskmanager.repository;


import java.util.List;

import com.stefanini.taskmanager.entity.Task;

public interface TaskRepository {

//    void deleteTaskByTitle(String taskTitle);
    void deleteTaskByTitleFor(String taskTitle, String username);

    List<Task> getTasksFor(String username);

    int addTaskFor(String taskTitle, String taskDescription, String username);

}
