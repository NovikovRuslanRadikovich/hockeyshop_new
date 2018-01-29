package com.fujitsu.fs.rnovikov.dao;

import com.fujitsu.fs.rnovikov.entities.Product;

import java.sql.SQLException;

/**
 * Created by User on 06.01.2018.
 */
public interface ProductDao<T,E,F> {

    void save(T t);
    void delete(E t);
    Product[] getAll();
    T getById(E id);
    T getByName(F name);


    T[] getDecadeProduct(E id);

    void editProduct(E id, E price, F detailed_description);

    public void deleteConcreteProductInBaskets(E productId);
}
