package com.fujitsu.fs.rnovikov.dao;

import com.fujitsu.fs.rnovikov.entities.Product;

import java.sql.SQLException;

/**
 * Created by User on 06.01.2018.
 */
public interface ProductDao<T> {

    void save(T t);
    void delete(int t);
    Product[] getAll();
    T get(int id);
    T get(String name);


    T[] getDecadeProduct(int id);

    void editProduct(int id, int price, String detailed_description);
}
