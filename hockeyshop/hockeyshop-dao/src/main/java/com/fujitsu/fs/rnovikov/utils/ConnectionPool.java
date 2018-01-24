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


        hikariConfig.setDriverClassName(PropertiesJson.getDriver());
        hikariConfig.setJdbcUrl(PropertiesJson.getConnection_URL());
        hikariConfig.setUsername(PropertiesJson.getUsername());
        hikariConfig.setPassword(PropertiesJson.getPassword());

        hikariConfig.setMaximumPoolSize(10);

        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    private ConnectionPool() {

    }

    public static Connection getConnection() throws SQLException {

        return hikariDataSource.getConnection();
    }
}
