package com.fujitsu.fs.rnovikov.dao;

import com.fujitsu.fs.rnovikov.entities.Comment;
import com.fujitsu.fs.rnovikov.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao<Comment, Integer> {

    private static CommentDao<Comment, Integer> commentDao;


    private CommentDaoImpl() {

    }

    public static CommentDao<Comment, Integer> getInstance() {
        if (commentDao == null) {
            commentDao = new CommentDaoImpl();
        }
        return commentDao;
    }

    /**
     * @param Tid represents identifier of a product
     * @return
     * @throws SQLException
     */

    @Override
    public List<Comment> get(Integer Tid) {

        Comment comment = null;
        List<Comment> comments = new ArrayList<>();
        try(Connection connection = ConnectionPool.getConnection()) {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM comment WHERE product_id = ?");
        preparedStatement.setInt(1, Tid);



            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {

                comment = new Comment(
                        resultSet.getInt("product_id"),
                        resultSet.getString("comment"),
                        resultSet.getString("username")
                );

                comments.add(comment);
            }

        }  catch (SQLException e) {

        }


        return comments;
    }

    @Override
    public void save(Comment comment) {
        try(Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO comment (product_id, comment, username) VALUES (?, ?, ?);");

            preparedStatement.setInt(1, comment.getProduct_id());
            preparedStatement.setString(2, comment.getComment());
            preparedStatement.setString(3, comment.getUsername());

            preparedStatement.execute();
        } catch(SQLException e) {

        }

    }


}
