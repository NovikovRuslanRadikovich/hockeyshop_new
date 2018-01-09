package com.fujitsu.fs.rnovikov.services;

import java.sql.SQLException;

/**
 * Created by User on 06.01.2018.
 */
public interface Service<T> {

    boolean isRegistred(String username) throws SQLException;

}
