package com.fujitsu.fs.servlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ruslan on 26.11.2017.
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    Logger homeServletLogger;

    public void init(){
        homeServletLogger = Logger.getLogger(HomeServlet.class);

    }


    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException,IOException {


        if("true".equals(request.getParameter("exit"))) {
            request.getSession().removeAttribute("username");
            request.getSession().setAttribute("anonymous",true);

            response.sendRedirect("/home");
        } else {


            HttpSession session = request.getSession();

            if (session.getAttribute("username") == null) {
                session.setAttribute("anonymous", true);
            }

            response.setCharacterEncoding("utf-8");


            response.sendRedirect(getServletContext().getContextPath() + "/home.ftl");
        }

    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");

        request.getSession().removeAttribute("anonymous");

        String username = request.getParameter("username");

        String ip = null;
        if(request.getSession().getAttribute("username") == null) {
            ip = request.getRemoteAddr();
            homeServletLogger.info("Logged user with IP " + ip);
        }

        request.getSession().setAttribute("username",username);

        response.sendRedirect("/home");
    }
}
