package com.fujitsu.fs.rnovikov.dao;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao<T,E> {

    public List<T> get(E Tid);

    void save(T t);


}
