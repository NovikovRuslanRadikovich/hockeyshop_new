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
 * This servlet class is used to handle request's for main page of application
 * It doesn't support post queries, only get methods are allowed
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    /**
     * If request object contains a logout parameter, it means that I remove user attribute from users session
     * and set maximum age 0 for cookie named users
     * Finally I redirect user to a login page
     * In opposite case I make additionally checking if request contains cookie named users I add user attribute to session object
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
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

            if(request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if ("users".equals(cookie.getName())) {
                        request.getSession().setAttribute("user", URLDecoder.decode(cookie.getValue(), "UTF-8"));

                    }
                }
            }

            getServletConfig().getServletContext().getRequestDispatcher("/home.ftl").forward(request,response);
        }


    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        super.doPost(request,response);
    }
}
