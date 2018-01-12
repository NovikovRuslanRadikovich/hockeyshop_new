package com.fujitsu.fs.rnovikov.servlets;

import com.fujitsu.fs.rnovikov.dao.BasketDao;
import com.fujitsu.fs.rnovikov.dao.BasketDaoImpl;
import com.fujitsu.fs.rnovikov.entities.Product;

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
@WebServlet("/basket")

public class Basket extends HttpServlet {

    private BasketDao basketDao;
    public void init() {
        basketDao = BasketDaoImpl.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
        } else {
            try {
                request.setAttribute("basket", basketDao.getAll( request.getSession().getAttribute("user")));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getServletConfig().getServletContext().getRequestDispatcher("/basket.ftl").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        super.doPost(request,response);

    }
}
