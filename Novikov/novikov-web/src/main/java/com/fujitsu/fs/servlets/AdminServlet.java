package com.fujitsu.fs.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ruslan on 03.12.2017.
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException,IOException {
        response.sendRedirect("/admin.ftl");
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request,response);
    }

}
