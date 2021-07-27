package ru.job4j.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {
    T findById(ID id);
    List<T> findAll();
    Long getCount();
    void makePersistent(T entity);
}
