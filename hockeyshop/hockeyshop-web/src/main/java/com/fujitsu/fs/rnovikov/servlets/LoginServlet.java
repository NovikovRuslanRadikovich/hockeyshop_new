package com.fujitsu.fs.rnovikov.servlets;

import com.fujitsu.fs.rnovikov.dao.UserDao;
import com.fujitsu.fs.rnovikov.dao.UserDaoImpl;
import com.fujitsu.fs.rnovikov.entities.User;
import com.fujitsu.fs.rnovikov.services.Service;
import com.fujitsu.fs.rnovikov.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;

/**
 * Created by User on 06.01.2018.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

     UserDao<User> dao;
     Service<User> userService;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = UserDaoImpl.getInstance();
        userService = new UserService(dao);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            response.sendRedirect("/");
            return;
        }

        getServletConfig().getServletContext().getRequestDispatcher("/unregistered/login.ftl").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        request.setCharacterEncoding("UTF-8");

        request.setAttribute("name", request.getParameter("name"));
        request.setAttribute("password", request.getParameter("password"));

        if(login(request, response)) {
            response.sendRedirect("/");
        } else {
            request.getRequestDispatcher("/unregistered/login.ftl").forward(request, response);
        }
    }

    public boolean login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getAttribute("name");
        String password = (String) request.getAttribute("password");

        dao = UserDaoImpl.getInstance();
        userService = new UserService(dao);

        if ("admin".equals(username) && "admin".equals(password)) {
            request.getSession().setAttribute("admin", "admin");
            return true;
        } else {

            try {
                if (userService.isRegistred(username)) {

                    if (dao.get(username).getPasswordHash().equals(password)) {

                        request.getSession().setAttribute("user", username);
                        addCookies(response, username);
                        return true;

                    } else {
                        request.setAttribute("error", "Не верный пароль");
                        return false;
                    }
                } else {
                    request.setAttribute("error", "Пользователь не найден");
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public  void addCookies(HttpServletResponse response, String key) throws UnsupportedEncodingException {
        // Create cookies for first and last names.
        Cookie name = new Cookie("users",
                URLEncoder.encode(key, "UTF-8"));

        name.setMaxAge(60 * 60 * 24 * 25);

        response.addCookie(name);

    }

}
