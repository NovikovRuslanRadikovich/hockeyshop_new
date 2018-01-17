package com.fujitsu.fs.rnovikov.dao;

import com.fujitsu.fs.rnovikov.entities.User;
import com.fujitsu.fs.rnovikov.utils.ConnectionPool;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by User on 06.01.2018.
 */
public class UserDaoImpl implements UserDao<User> {

    private static UserDao<User> userDao;

    private UserDaoImpl() {

    }

    public static UserDao<User> getInstance() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    public void save(User o) throws SQLException {
        Connection connection = ConnectionPool.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, password, phoneNumber) VALUES (?, ?, ?);");

        preparedStatement.setString(1, o.getName());
        preparedStatement.setString(2, o.getPasswordHash());
        preparedStatement.setString(3, o.getPhoneNumber());


        preparedStatement.execute();

        connection.close();
    }

    public void delete(User o) throws SQLException {
        Connection connection = ConnectionPool.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE user_id = ?");
        preparedStatement.setInt(1, o.getId());

    }

    public List getAll() throws SQLException {
        User user = null;
        List<User> users = new ArrayList<User>();

        Connection connection = ConnectionPool.getConnection();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Users");
            while (resultSet.next()) {

                user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getString("phoneNumber")
                );

                user.setUser_id(resultSet.getInt("user_id"));

                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }


        return users;
    }


    public User get(int id) throws SQLException {

        Connection connection = ConnectionPool.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?");
        preparedStatement.setInt(1, id);

        User user = null;
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

             user = new User(
                    resultSet.getString("name"),
                    resultSet.getString("password"),
                    resultSet.getString("phoneNumber")
            );
            user.setUser_id(resultSet.getInt("user_id"));

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return user;
    }

    public User get(String name) throws SQLException {

        Connection connection = ConnectionPool.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE name = ?");
        preparedStatement.setString(1, name);

        User user = null;
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            user = new User(
                    resultSet.getString("name"),
                    resultSet.getString("password"),
                    resultSet.getString("phoneNumber")
            );
            user.setUser_id(resultSet.getInt("user_id"));

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return user;
    }


}
