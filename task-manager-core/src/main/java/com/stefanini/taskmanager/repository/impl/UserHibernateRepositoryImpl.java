package com.stefanini.taskmanager.repository.impl;

import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.repository.BaseRepository;
import com.stefanini.taskmanager.repository.UserRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root)
                .where(criteriaBuilder.equal(root.get("userName"), username));

        return entityManager.createQuery(query).getSingleResult();

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
