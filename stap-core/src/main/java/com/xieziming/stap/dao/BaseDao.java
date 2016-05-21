package com.xieziming.stap.dao;

import java.util.List;

/**
 * Created by Suny on 5/10/16.
 */
public interface BaseDao<T> {
    /**
     * Generic add
     * @param obj
     */
    public void add(T obj);

    /**
     * Generic update
     * @param obj
     */
    public void update(T obj);

    /**
     * Generic delete
     * @param obj
     */
    public void delete(T obj);

    /**
     * Generic find by id
     * @param id
     * @return
     */
    public T findById(int id);

    /**
     * Generic list all
     * @return
     */
    public List<T> findAll();
}