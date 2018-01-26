package com.fujitsu.fs.rnovikov.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by User on 19.01.2018.
 */
public class SingleConnection {

    public static Connection getConnection() throws SQLException {

        Connection connection = null;
        try {
            Class.forName(PropertiesJson.getDriver());
            connection = DriverManager.getConnection(PropertiesJson.getConnection_URL(),PropertiesJson.getUsername(),
                    PropertiesJson.getPassword());


        } catch (ClassNotFoundException e) {
            System.out.println("Connection Failure");
        }

        return connection;

    }


}
