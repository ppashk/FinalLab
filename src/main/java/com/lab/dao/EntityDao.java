package com.lab.dao;

import java.util.List;

public interface EntityDao<T> {
    T getById(int id, boolean full);

    List<T> getAll();
    List<T> getAll(boolean full);

    boolean create(T entity);

    boolean update(T entity);

    boolean remove(T entity);
}
