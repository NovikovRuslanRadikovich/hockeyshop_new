package com.fujitsu.fs.rnovikov.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by User on 06.01.2018.
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        if("logout".equals(request.getParameter("action"))) {
            request.getSession().removeAttribute("user");

            for(Cookie cookie : request.getCookies()) {
                if("users".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }

            response.sendRedirect("/login");
        } else {

            for (Cookie cookie : request.getCookies()) {
                if ("users".equals(cookie.getName())) {
                    request.getSession().setAttribute("user", URLDecoder.decode(cookie.getValue(), "UTF-8"));

                }
            }

            getServletConfig().getServletContext().getRequestDispatcher("/home.ftl").forward(request,response);
        }


    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        super.doPost(request,response);
    }
}
