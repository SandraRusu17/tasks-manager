package com.stefanini.taskmanager.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface AbstractRepository<T, ID> {
    List<T> findAll();

    Optional<T> getById(ID id);

    void delete(T entity);

    void update(T entity);

    void create(T entity);

    void deleteById(T entity, ID id);

    Stream<T> streamAll();
}
