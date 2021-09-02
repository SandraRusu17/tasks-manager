package com.stefanini.taskmanager.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface AbstractRepository<T, ID> {
    public List<T> findAll();

    Optional<T> getById(ID id);

    void delete(T entity);

    void update(T entity);

    void create(T entity);

}
