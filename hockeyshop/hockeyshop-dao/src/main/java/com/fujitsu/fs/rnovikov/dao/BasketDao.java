package com.fujitsu.fs.rnovikov.dao;

import com.fujitsu.fs.rnovikov.entities.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 07.01.2018.
 */
public interface BasketDao<T,E> {


    public void save(T t, E e) throws SQLException;

    public void delete(T t, E e) throws SQLException;

    public void delete(E e) throws SQLException;

    public List<Product> getAll(T t) throws SQLException;

}
