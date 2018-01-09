package com.fujitsu.fs.rnovikov.dao;

import com.fujitsu.fs.rnovikov.entities.Product;

import java.sql.SQLException;

/**
 * Created by User on 06.01.2018.
 */
public interface ProductDao<T> {

    void save(T t) throws SQLException;
    void delete(int t) throws SQLException;
    Product[] getAll() throws SQLException;
    T get(int id) throws SQLException;
    T get(String name) throws SQLException;


    T[] getDecadeProduct(int id) throws SQLException;
}
