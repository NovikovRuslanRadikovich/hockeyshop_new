package com.fujitsu.fs.rnovikov.dao;

import com.fujitsu.fs.rnovikov.entities.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 07.01.2018.
 */
public interface BasketDao<T,E> {


    public void save(T t, E e);

    public void delete(T t, E e);

    public List<? extends Object> getAll(T t);


}
