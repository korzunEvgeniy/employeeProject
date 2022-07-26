package com.mastery.java.task.service;

import java.util.List;

public interface Service<T, K> {
    T get(K key);

    List<T> getAll();

    T create(T entity);

    T update(T entity);

    void delete(K key);
}
