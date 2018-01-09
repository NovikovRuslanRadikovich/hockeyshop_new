package com.fujitsu.fs.rnovikov.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fujitsu.fs.rnovikov.dao.ProductDao;
import com.fujitsu.fs.rnovikov.dao.UserDao;
import com.fujitsu.fs.rnovikov.dao.UserDaoImpl;
import com.fujitsu.fs.rnovikov.entities.User;
import com.fujitsu.fs.rnovikov.utils.FormDataCheck;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.TreeMap;

/**
 * Created by User on 07.01.2018.
 */
@WebServlet("/register")

public class Registration extends HttpServlet {

    UserDao<User> dao;

    @Override
    public void init() throws ServletException {
        dao = UserDaoImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            response.sendRedirect("/");
            return;
        }

        request.getRequestDispatcher("/unregistered/registration.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("name");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String phone = request.getParameter("phone");

        TreeMap message = null;
        try {
            message = FormDataCheck.checkAllFieldsAndGetErrorMessageIfFieldsAreInvalid(username, phone, password, password2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter pw = response.getWriter();

        if (message.isEmpty()) {
            User user = new User(username, password, phone);
            try {

                dao.save(user);

                request.getSession().setAttribute("user", username);


                pw.write("success");



            } catch (SQLException e) {

                e.printStackTrace();
                pw.write("SQL exception!");
                pw.write(e.getMessage());
            }


        } else {

            ObjectMapper mapper = new ObjectMapper();

            String jsonString = mapper.writeValueAsString(message);

            pw.write(jsonString);
        }

        pw.close();
    }

}
