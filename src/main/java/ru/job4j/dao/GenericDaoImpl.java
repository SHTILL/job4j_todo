package ru.job4j.dao;

import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public abstract class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {
    protected Session session;
    protected Class<T> entityClass;

    public GenericDaoImpl(Session session, Class<T> entityClass) {
        this.session = session;
        this.entityClass = entityClass;
    }

    @Override
    public T findById(ID id) {
        return session.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = cb.createQuery(entityClass);
        Root<T> root = criteria.from(entityClass);
        criteria.select(root);
        TypedQuery<T> query = session.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public Long getCount() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        Root<T> root = criteria.from(entityClass);
        criteria.select(cb.count(root));
        return session.createQuery(criteria).getSingleResult();
    }

    @Override
    public void makePersistent(T entity) {
        session.saveOrUpdate(entity);
    }
}
