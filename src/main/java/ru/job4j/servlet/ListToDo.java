package ru.job4j.servlet;

import com.google.gson.Gson;
import ru.job4j.store.TaskRepository;
import ru.job4j.dto.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class ListToDo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String doneParam = req.getParameter("done");
        Boolean done = new Gson().fromJson(doneParam, Boolean.class);
        TaskRepository r = new TaskRepository();
        Collection<Task> t = r.query(done);
        String tasks = new Gson().toJson(t);
        resp.getWriter().print(tasks);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskRepository r = new TaskRepository();
        String addParam = req.getParameter("add");
        String doneParam = req.getParameter("done");
        if (addParam != null) {
            Task t = new Gson().fromJson(addParam, Task.class);
            r.addTask(t);
        }
        if (doneParam != null) {
            Task t = new Gson().fromJson(doneParam, Task.class);
            r.updateTask(t);
        }
    }
}