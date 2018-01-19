package com.fujitsu.fs.rnovikov.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.hsqldb.jdbc.JDBCDriver;

/**
 * Created by User on 06.01.2018.
 */
public class ConnectionPool {

    private static HikariConfig hikariConfig = new HikariConfig();
    private static HikariDataSource hikariDataSource;

    static {

        Properties properties = new Properties();
        try (InputStream fileInput = ConnectionPool.class.getClassLoader().getResourceAsStream("database.properties")) {
            properties.load(fileInput);
        } catch (Exception e) {
            System.out.println("NNNNNNNO SUCCCHHHHHHH FFFILLLLLLLLLLEEEEEEE");
        }


        hikariConfig.setDriverClassName(properties.getProperty("DRIVER"));
        hikariConfig.setJdbcUrl(properties.getProperty("CONNECTION_URL"));
        hikariConfig.setUsername(properties.getProperty("USERNAME"));
        hikariConfig.setPassword(properties.getProperty("PASSWORD"));


//        hikariConfig.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
//        hikariConfig.setJdbcUrl("jdbc:hsqldb:hsql://localhost/mydb");
//        hikariConfig.setUsername("SA");
//        hikariConfig.setPassword("");

          hikariConfig.setMaximumPoolSize(1);

//        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
//        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
//        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    private ConnectionPool() {

    }

    public static Connection getConnection() throws SQLException {

        return hikariDataSource.getConnection();
    }
}
