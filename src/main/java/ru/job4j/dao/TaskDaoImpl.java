package ru.job4j.dao;

import org.hibernate.Session;
import ru.job4j.model.Task;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class TaskDaoImpl extends GenericDaoImpl<Task, Long>
        implements TaskDao {

    public TaskDaoImpl(Session session) {
        super(session, Task.class);
    }

    @Override
    public List<Task> findAll(boolean done) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = cb.createQuery(Task.class);
        Root<Task> t = criteria.from(Task.class);
        criteria.select(t);
        ParameterExpression<Boolean> doneParam = cb.parameter(Boolean.class);
        criteria.where(cb.equal(t.get("done"), doneParam));
        TypedQuery<Task> query = session.createQuery(criteria);
        query.setParameter(doneParam, done);
        return query.getResultList();
    }

    @Override
    public List<Task> findByName(String name) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = cb.createQuery(Task.class);
        Root<Task> t = criteria.from(Task.class);
        criteria.select(t);
        ParameterExpression<String> nameParam = cb.parameter(String.class);
        criteria.where(cb.equal(t.get("desc"), nameParam));
        TypedQuery<Task> query = session.createQuery(criteria);
        query.setParameter(nameParam, name);
        return query.getResultList();
    }
}
