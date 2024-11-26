package ru.kafpin.lr6_bzz.dao;

import java.util.Collection;

public interface Dao<T, ID> {
    Collection<T> findALl();
    T save(T entity);
    T update(T entity);
    void deleteById(ID id);
    T findById(ID id);
}
