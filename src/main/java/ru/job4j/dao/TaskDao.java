package ru.job4j.dao;

import ru.job4j.model.Task;

import java.util.List;

public interface TaskDao extends GenericDao<Task, Long> {
    List<Task> findAll(boolean done);
    List<Task> findByName(String name);
}
