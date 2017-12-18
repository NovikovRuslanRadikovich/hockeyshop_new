package com.fujitsu.fs.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ruslan on 03.12.2017.
 */

@WebFilter("/admin")
public class AdminFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
         HttpServletRequest request = (HttpServletRequest) servletRequest;

         HttpSession session = request.getSession();

         if(session.getAttribute("username") != null ) {
             filterChain.doFilter(servletRequest,servletResponse);

         } else {
             servletRequest.setAttribute("not_logged",true);
             servletRequest.getRequestDispatcher("home.ftl").forward(servletRequest,servletResponse);
         }
    }

    public void destroy() {

    }
}
