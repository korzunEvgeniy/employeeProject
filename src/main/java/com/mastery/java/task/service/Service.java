package com.mastery.java.task.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Service<T, K> {
    T get(K key);

    List<T> getAll(Pageable pageable);

    T create(T entity);

    T update(T entity);

    void delete(K key);

    K countAll();
}
