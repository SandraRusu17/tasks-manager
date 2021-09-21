package com.stefanini.taskmanager.repository.impl;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.repository.BaseRepository;
import com.stefanini.taskmanager.repository.TaskRepository;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;


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
    public void deleteTaskByTitle(String taskTitle) {
        final EntityTransaction transaction = beginTransaction();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaDelete<Task> deleteQuery = criteriaBuilder.createCriteriaDelete(Task.class);
            Root<Task> rootTask = deleteQuery.from(Task.class);

            deleteQuery.where(criteriaBuilder.equal(rootTask.get("title"), taskTitle));

            Query query = entityManager.createQuery(deleteQuery);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            log.info("Something bad happened during committing the transaction", e);
        }
    }

    @Override
    public List<Task> getTasksFor(String username) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> queryTask = criteriaBuilder.createQuery(Task.class);
        Root<Task> rootTask = queryTask.from(Task.class);

        Join<Task, User> taskUserJoin = rootTask.join("users");

        queryTask.select(rootTask)
                .where(criteriaBuilder.equal(taskUserJoin.get("userName"), username));

        return entityManager.createQuery(queryTask).getResultList();
    }

    @Override
    public void saveTaskFor(Task task, String username) {
    }


}
