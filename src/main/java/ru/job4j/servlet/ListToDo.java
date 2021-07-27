package ru.job4j.servlet;

import com.google.gson.Gson;
import ru.job4j.model.Task;
import ru.job4j.service.TaskTracker;
import ru.job4j.service.TaskTrackerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListToDo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String  allParam = req.getParameter("all");
        Boolean all = new Gson().fromJson(allParam, Boolean.class);
        TaskTracker tracker = new TaskTrackerImpl();
        List<Task> tasks;
        if (all) {
            tasks = tracker.getTasks();
        } else {
            tasks = tracker.getTasks(false);
        }
        resp.getWriter().print(tasks);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String taskParam = req.getParameter("task");
        TaskTracker tracker = new TaskTrackerImpl();
        Task t = new Gson().fromJson(taskParam, Task.class);
        tracker.addTask(t);
    }
}