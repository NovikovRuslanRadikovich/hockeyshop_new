package com.fujitsu.fs.rnovikov.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fujitsu.fs.rnovikov.dao.ProductDao;
import com.fujitsu.fs.rnovikov.dao.UserDao;
import com.fujitsu.fs.rnovikov.dao.UserDaoImpl;
import com.fujitsu.fs.rnovikov.entities.User;
import com.fujitsu.fs.rnovikov.utils.FormDataCheck;
import org.apache.log4j.Logger;

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
    private final Logger logger = Logger.getLogger(Registration.class);
    /**
     * This method initializes Data Access Object for User Entity
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        dao = UserDaoImpl.getInstance();
    }

    /**
     * If session contains user attribute it means that user is also registered, so there is no ability for him
     * to be registered again
     * Else registration page is being shown to a user
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
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


    /**
     * I get request parameters for registration process
     * When I make validation of parameters
     * If any of parameters is not valid I show error message on the page
     * If all parameters are valid it means that I form a User Entity from sent parameters and save it in DataBase
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
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
            logger.error("Problems were happened when program tried to find user with name " + username + " in DB");
        }

        PrintWriter pw = response.getWriter();

        if (message != null) {
            if (message.isEmpty()) {
                User user = new User(username, password, phone);
                try {

                    dao.save(user);

                    request.getSession().setAttribute("user", username);

                    pw.write("success");

                } catch (SQLException e) {

                    pw.write("SQL exception!");
                    pw.write(e.getMessage());
                }

            } else {

                ObjectMapper mapper = new ObjectMapper();

                String jsonString = mapper.writeValueAsString(message);

                pw.write(jsonString);
            }
        }

        pw.close();
    }

}
