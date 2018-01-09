package com.fujitsu.fs.rnovikov.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by User on 06.01.2018.
 */
public class ConnectionPool {

    private static HikariConfig hikariConfig = new HikariConfig();
    private static HikariDataSource hikariDataSource;

    static {
        hikariConfig.setJdbcUrl(Properties.getConnection_URL());
        hikariConfig.setUsername(Properties.getUsername());
        hikariConfig.setPassword(Properties.getPassword());
        hikariConfig.addDataSourceProperty("cachePrepStmts","true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize","250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit","2048");
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    private ConnectionPool() {

    }

    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
