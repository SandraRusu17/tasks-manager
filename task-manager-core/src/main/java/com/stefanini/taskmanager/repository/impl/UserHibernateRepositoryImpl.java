package com.stefanini.taskmanager.repository.impl;

import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.repository.BaseRepository;
import com.stefanini.taskmanager.repository.UserRepository;
import org.hibernate.HibernateException;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class UserHibernateRepositoryImpl<T, ID extends Serializable> extends BaseRepository<User, Long> implements UserRepository {

    public static UserHibernateRepositoryImpl<User, Long> INSTANCE;

    public static UserHibernateRepositoryImpl<User, Long> getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserHibernateRepositoryImpl<>();
        }
        return INSTANCE;
    }

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TaskHibernateRepositoryImpl.class);

    public UserHibernateRepositoryImpl() {
        super(User.class);
    }

    @Override
    public void saveUser(final User user) {
        create(user);
    }

    @Override
    public User findByUsername(final String username) {
        final EntityTransaction t = beginTransaction();
        final Query query = entityManager.createQuery(
                "select u from " + type.getName() + " u where u.userName = :user_name");
        query.setParameter("user_name", username);
        final User user = (User) query.getSingleResult();
        t.commit();
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return findAll();
    }

    @Override
    public void deleteUserById(Long id) {
    }

    @Override
    public Optional<User> findById(Long id) {
        return getById(id);
    }


}
