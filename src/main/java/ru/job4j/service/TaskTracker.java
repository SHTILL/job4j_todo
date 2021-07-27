package ru.job4j.service;

import ru.job4j.model.Task;

import java.util.List;

public interface TaskTracker {
    List<Task> getTasks();
    List<Task> getTasks(boolean done);
    List<Task> getTasks(String description);
    void addTask(Task t);
}
