package com.stefanini.taskmanager.repository.impl;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.repository.BaseRepository;
import com.stefanini.taskmanager.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class UserHibernateRepositoryImpl<T, ID extends Serializable> extends BaseRepository<User, Long> implements UserRepository {

    public static UserHibernateRepositoryImpl<User, Long> INSTANCE = new UserHibernateRepositoryImpl<>();

    public static UserHibernateRepositoryImpl<User, Long> getInstance() {
        if (INSTANCE.entityManager == null) {
            INSTANCE.entityManager = getEntityManager();
        }
        return INSTANCE;
    }

    public UserHibernateRepositoryImpl() {
        super(User.class);
    }

    @Override
    public void saveUser(final User user) {
        create(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public void deleteUserByUsername(String username) {

    }
}
