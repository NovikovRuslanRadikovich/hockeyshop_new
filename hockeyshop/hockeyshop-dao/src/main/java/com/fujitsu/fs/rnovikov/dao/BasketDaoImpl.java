package com.fujitsu.fs.rnovikov.dao;

import com.fujitsu.fs.rnovikov.entities.Product;
import com.fujitsu.fs.rnovikov.utils.ConnectionPool;
import com.fujitsu.fs.rnovikov.utils.SingleConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 07.01.2018.
 */
public class BasketDaoImpl implements BasketDao<Integer, Integer> {

    private static BasketDao<Integer, Integer> basketDao;

    public static BasketDao<Integer, Integer> getInstance() {
        if (basketDao == null) {
            basketDao = new BasketDaoImpl();
        }

        return basketDao;

    }


    public void save(Integer userId, Integer productId) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO basket (user_id, product_id) VALUES (?,?)");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {

        }


    }

    public void delete(Integer userId, Integer productId) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM basket WHERE user_id = ? AND product_id = ?");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);

            preparedStatement.execute();

        } catch (SQLException e) {

        }
    }

    public List<Product> getAll(Integer userId) {

        List<Product> usersProducts = new ArrayList<Product>();

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM basket WHERE user_id = ?");

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt(2);
                Product product = ProductDaoImpl.getInstance().get(productId);
                usersProducts.add(product);
            }

        } catch (SQLException e) {

        }

        return usersProducts;
    }
}
