package com.websystique.springmvc.dao;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;

import com.websystique.springmvc.model.Employee;
import com.websystique.springmvc.utils.Utils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    public void persist(T entity) {
        String u = new Utils("Abstract DAO", "persist").toString();
        Employee a = (Employee) entity;
        System.out.println("PERISIT e");
        System.out.println(a.toString());
        getSession().save(entity);
    }

    public void update(T entity) {
        String u = new Utils("Abstract DAO", "update").toString();
        Employee a = (Employee) entity;
        System.out.println(a.toString());
        getSession().update(a);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }



    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }


    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(persistentClass);
    }

}