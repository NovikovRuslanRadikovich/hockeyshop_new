package com.fujitsu.fs.rnovikov.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 08.01.2018.
 */
public interface UserDao<T> {

    void save(T t);
    void delete(T t);
    List<T> getAll();
    T get(int user_id);
    T get(String name);

}
