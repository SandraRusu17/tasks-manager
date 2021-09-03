package com.stefanini.taskmanager.repository;

import com.stefanini.taskmanager.entity.User;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class BaseRepository<T, ID> implements AbstractRepository<T, ID> {

    protected EntityManager entityManager;

    private final Class<T> type;

    protected BaseRepository(Class<T> type) {
        this.type = type;
    }

    public static EntityManager getEntityManager() {
        String persistenceUnitName = "local-mysql";
        return Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
    }

    protected void checkTransaction() {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
    }

    @Override
    public void create(T entity) {
        checkTransaction();
        try {
            this.entityManager.persist(entity);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.info("Something bad happened during fetching an entity");
        }
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = builder.createQuery(type);
        Root<T> root = cq.from(type);
        cq.select(root);
        return this.entityManager.createQuery(cq).getResultList();
    }

    @Override
    public void delete(T entity) {
        checkTransaction();
        try {
            this.entityManager.remove(this.entityManager.merge(entity));
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.info("Something bad happened during fetching an entity");
        }
    }

    @Override
    public void update(T entity) {
        checkTransaction();
        try {
            this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.info("Something bad happened during fetching an entity");
        }
    }

    @Override
    public Optional<T> getById(ID id) {
        checkTransaction();
        final T entity = entityManager.find(type, id);
        this.entityManager.getTransaction().commit();
        return Optional.ofNullable(entity);
    }

}

