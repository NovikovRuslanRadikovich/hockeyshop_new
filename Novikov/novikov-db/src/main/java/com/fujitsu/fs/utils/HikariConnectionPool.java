package com.fujitsu.fs.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnectionPool {

    private static HikariConfig hikariConfig = new HikariConfig();
    private static HikariDataSource dataSource;

    static{
        hikariConfig.setJdbcUrl(PropertiesRetriever.getConnection_URL());
        hikariConfig.setUsername(PropertiesRetriever.getUSERNAME());
        hikariConfig.setPassword(PropertiesRetriever.getPassword());
        hikariConfig.addDataSourceProperty("cachePrepStmts","true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize","250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit","2048");
        dataSource = new HikariDataSource(hikariConfig);
    }

    private HikariConnectionPool() {

    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }



}
