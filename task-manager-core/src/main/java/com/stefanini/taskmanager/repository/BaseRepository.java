package com.stefanini.taskmanager.repository;



import com.stefanini.taskmanager.repository.impl.TaskHibernateRepositoryImpl;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;


public abstract class BaseRepository<T, ID> implements AbstractRepository<T, ID> {

    protected final EntityManager entityManager;
    protected final Class<T> type;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BaseRepository.class);

    protected BaseRepository(Class<T> type) {
        entityManager = Persistence.createEntityManagerFactory(
                "local-mysql").createEntityManager();
        this.type = type;
    }


    protected EntityTransaction beginTransaction() {
        final EntityTransaction transaction = entityManager.getTransaction();
        if (!transaction.isActive()) {
            transaction.begin();
        }
        return transaction;
    }

    @Override
    public void create(T entity) {
        final EntityTransaction transaction = beginTransaction();
        try {
            entityManager.persist(entity);
            transaction.commit();
            log.info("Saved {}", entity.toString());
        } catch (IllegalStateException | HibernateException e) {
            transaction.rollback();
            log.info("Something bad happened during fetching an entity");
        }
    }

    @Override
    public List<T> findAll() {
        final EntityTransaction transaction = beginTransaction();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = builder.createQuery(type);
        Root<T> root = cq.from(type);
        cq.select(root);
        final List<T> result = entityManager.createQuery(cq).getResultList();
        transaction.commit();
        return result;
    }

    @Override
    public void delete(T entity) {
        final EntityTransaction transaction = beginTransaction();
        try {
            entityManager.remove(entityManager.merge(entity));
            transaction.commit();
        } catch (IllegalStateException | HibernateException e) {
            transaction.rollback();
            log.info("Something bad happened during fetching an entity");
        }
    }

    @Override
    public void deleteById(T entity, ID id){
        final Optional<T> byId = getById(id);
        byId.ifPresent(this::delete);
    }

    @Override
    public Optional<T> getById(ID id) {
        final EntityTransaction transaction = beginTransaction();
        final T entity = entityManager.find(type, id);
        transaction.commit();
        return Optional.ofNullable(entity);
    }

    @Override
    public void update(T entity) {
        final EntityTransaction transaction = beginTransaction();
        try {
            entityManager.merge(entity);
            transaction.commit();
        } catch (IllegalStateException | HibernateException e) {
            transaction.rollback();
            log.info("Something bad happened during fetching an entity");
        }
    }
}

