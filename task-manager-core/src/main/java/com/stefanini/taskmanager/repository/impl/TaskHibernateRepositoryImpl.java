package com.stefanini.taskmanager.repository.impl;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.repository.BaseRepository;
import com.stefanini.taskmanager.repository.TaskRepository;

import java.io.Serializable;
import java.util.List;

public class TaskHibernateRepositoryImpl<T, ID extends Serializable> extends BaseRepository<Task, Long> implements TaskRepository {


    public static TaskHibernateRepositoryImpl<Task, Long> INSTANCE = new TaskHibernateRepositoryImpl<>();


    public static TaskHibernateRepositoryImpl<Task, Long> getInstance() {
        if (INSTANCE.entityManager == null) {
            INSTANCE.entityManager = getEntityManager();
        }
        return INSTANCE;
    }

    public TaskHibernateRepositoryImpl() {
        super(Task.class);
    }


    @Override
    public void deleteTaskByTitleFor(String taskTitle, String username) {

    }

    @Override
    public List<Task> getTasksFor(String username) {
        return null;
    }

    @Override
    public void saveTaskFor(Task task, String username) {
    }
}
