package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.model.TaskEntity;
import ru.job4j.dto.Task;

import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;

public class TaskRepository {
    private final SessionFactory sf;

    public TaskRepository() {
        sf = Store.getInstance().getSf();
    }

    public boolean addTask(Task task) {
        boolean result = true;
        try (Session s = sf.openSession() ) {
            Transaction t = s.beginTransaction();
            TaskEntity e = new TaskEntity();
            e.setId(task.getId());
            e.setDesc(task.getDesc());
            e.setCreated(task.getCreated());
            s.save(task);
            t.commit();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public boolean updateTask(Task task) {
        boolean result = true;
        try (Session s = sf.openSession() ) {
            Transaction t = s.beginTransaction();
            TaskEntity entity = s.get(TaskEntity.class, task.getId());
            entity.setDone(new Date());
            s.save(entity);
            t.commit();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public Collection<Task> query(Boolean isNotDone) {
        List<Task> result;
        try (Session s = sf.openSession() ) {
            CriteriaBuilder builder = s.getCriteriaBuilder();
            CriteriaQuery<TaskEntity> query = builder.createQuery(TaskEntity.class);
            Root<TaskEntity> root = query.from(TaskEntity.class);
            query.select(root);
            if (isNotDone) {
                query.where(root.get("done").isNull());
            }
            result = s.createQuery(query).getResultStream().map(t -> new Task(t.getId(), t.getDesc(), t.getCreated(), t.getDone() != null)).collect(Collectors.toList());
        } catch (Exception e) {
            result = new ArrayList<>();
        }
        return result;
    }
}
