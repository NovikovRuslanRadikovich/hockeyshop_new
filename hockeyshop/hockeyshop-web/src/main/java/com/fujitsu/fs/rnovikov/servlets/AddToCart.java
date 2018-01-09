package com.fujitsu.fs.rnovikov.servlets;

import com.fujitsu.fs.rnovikov.dao.*;
import com.fujitsu.fs.rnovikov.entities.Product;
import com.fujitsu.fs.rnovikov.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by User on 07.01.2018.
 */
@WebServlet("/addToCart/*")
public class AddToCart extends HttpServlet {
    BasketDao basketDao;
    ProductDao<Product> productDao;
    UserDao<User> userDao;
    public void init() throws ServletException {
        basketDao = BasketDaoImpl.getInstance();
        productDao = ProductDaoImpl.getInstance();
        userDao = UserDaoImpl.getInstance();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int id = Integer.valueOf(request.getPathInfo().substring(1));

        String username = (String) request.getSession().getAttribute("user");

        User user = null;
        try {
            user = userDao.get(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            BasketDaoImpl.getInstance().save(user != null ? user.getId() : 0,id);
        } catch(SQLException e){
            System.out.println("SQL error");
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        super.doPost(request,response);
    }
}
