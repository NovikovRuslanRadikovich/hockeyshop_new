package com.fujitsu.fs.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class DbWrapper {


    private static ConnectionPool connectionPool = new ConnectionPool(PropertiesRetriever.getConnection_URL(),
            PropertiesRetriever.getDriver(),PropertiesRetriever.getUsername(),
            PropertiesRetriever.getPassword(),
            10);



    private static Connection connection;


    public static Connection getConnection() throws SQLException, ClassNotFoundException {

      connection = connectionPool.retrieve();


        return connection;


    }

     public static ConnectionPool getConnectionPool() {
        return connectionPool;
     }


    public static void putback(Connection connection) {
        connectionPool.putback(connection);
    }
}
