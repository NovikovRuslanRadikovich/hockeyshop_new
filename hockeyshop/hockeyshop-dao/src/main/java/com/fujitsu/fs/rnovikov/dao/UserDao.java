package com.fujitsu.fs.rnovikov.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 08.01.2018.
 */
public interface UserDao<T> {

    void save(T t) throws SQLException;
    void delete(T t) throws SQLException;
    List<T> getAll() throws SQLException;
    T get(int id) throws SQLException;
    T get(String name) throws SQLException;

}
