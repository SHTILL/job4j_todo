package ru.job4j.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.job4j.dao.TaskDao;
import ru.job4j.dao.TaskDaoImpl;
import ru.job4j.model.Task;
import ru.job4j.store.Sf;

import java.util.List;

public class TaskTrackerImpl implements TaskTracker {
    protected TaskDao taskDao = new TaskDaoImpl(Sf.getSession());

    @Override
    public List<Task> getTasks() {
        return taskDao.findAll();
    }

    @Override
    public List<Task> getTasks(boolean done) {
        return taskDao.findAll(done);
    }

    @Override
    public List<Task> getTasks(String description) {
        return taskDao.findByName(description);
    }

    @Override
    public void addTask(Task t) {
        Session s = Sf.getSession();
        Transaction tx = s.getTransaction();
        try {
            tx.begin();
            taskDao.makePersistent(t);
            tx.commit();
            s.close();
        }
        catch (Exception e) {
            try {
                if (tx.getStatus().canRollback()) {
                    tx.rollback();
                }
            } catch (Exception rbEx) {
                System.err.println("Can't rollback transaction");
                rbEx.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            if (s.isOpen())
                s.close();
        }
    }
}
