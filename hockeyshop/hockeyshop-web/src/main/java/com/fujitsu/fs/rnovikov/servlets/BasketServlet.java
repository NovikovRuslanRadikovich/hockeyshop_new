package com.fujitsu.fs.rnovikov.servlets;

import com.fujitsu.fs.rnovikov.dao.BasketDao;
import com.fujitsu.fs.rnovikov.dao.BasketDaoImpl;
import com.fujitsu.fs.rnovikov.dao.UserDao;
import com.fujitsu.fs.rnovikov.dao.UserDaoImpl;
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
 * This servlet class is used to handle basket queries
 *
 */
@WebServlet("/basket")

public class BasketServlet extends HttpServlet {

    private BasketDao<Integer,Integer> basketDao;
    private UserDao<User> userDao;
    /**
     * Simply initializes Data Access object for BasketServlet entity
     */
    public void init() {
        basketDao = BasketDaoImpl.getInstance();
        userDao = UserDaoImpl.getInstance();
    }

    /**
     *
     * @param request
     * Firstly I retrieve Session Object from a request
     * If session doesn't contain user attribute, it means that user is not authorized
     * If no authority, user is being redirected to login page
     * Else all products from basket of user are being retrieved from cart and added to request as basket attribute
     * rendered on the basket.ftl page
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
        } else {

            User user;

            user = (User) userDao.get((String) request.getSession().getAttribute("user"));
            request.setAttribute("basket", basketDao.getAll( user.getId()));
            getServletConfig().getServletContext().getRequestDispatcher("/basket.ftl").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        super.doPost(request,response);

    }
}
