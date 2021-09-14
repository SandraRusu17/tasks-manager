package com.stefanini.taskmanager.repository.impl;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.repository.BaseRepository;
import com.stefanini.taskmanager.repository.TaskRepository;
import org.hibernate.HibernateException;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;


public class TaskHibernateRepositoryImpl<T, ID extends Serializable> extends BaseRepository<Task, Long> implements TaskRepository {


    public static TaskHibernateRepositoryImpl<Task, Long> INSTANCE;


    public static TaskHibernateRepositoryImpl<Task, Long> getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TaskHibernateRepositoryImpl<>();
        }
        return INSTANCE;
    }

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TaskHibernateRepositoryImpl.class);

    public TaskHibernateRepositoryImpl() {
        super(Task.class);
    }

    @Override
    public List<Task> findAllTasks() {
        return findAll();
    }

    @Override
    public void saveTask(final Task task) {
        create(task);
    }

    @Override
    public void deleteTaskByTitleFor(String taskTitle, String username) {
        final EntityTransaction t = beginTransaction();
        try {
            final Query query = entityManager.createQuery(
                    "DELETE FROM users_tasks t WHERE t.title = :title and t.id in (select t.id from Task t join t.users u where u.userName = :username)");
            query.setParameter("username", username);
            query.setParameter("title", taskTitle);
            query.executeUpdate();
            t.commit();
        } catch (IllegalStateException | HibernateException e) {
            t.rollback();
            log.info("Something bad happened during committing the transaction", e);
        }
    }


    @Override
    public List<Task> getTasksFor(String username) {
        final TypedQuery<Task> query = entityManager.createQuery(
                "select t from Task t join t.users u where u.userName = :username", Task.class);
        query.setParameter("username", username);
        return query.getResultList();
    }

    @Override
    public void saveTaskFor(Task task, String username) {
    }


}
