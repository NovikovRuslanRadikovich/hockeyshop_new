package com.fujitsu.fs.rnovikov.filters;



import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by User on 08.01.2018.
 */

public class UserAuthFilter implements Filter {

    private List<String> urlList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String initParameter = filterConfig.getInitParameter("allow-urls");
        urlList = new ArrayList<String>();
        Collections.addAll(urlList, initParameter.split(","));
    }

    @Override

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String servletPath = request.getServletPath();
        boolean allowedRequest = false;

        HttpSession session = request.getSession(false);

        for (String s : urlList) {

            if (servletPath.startsWith(s)) {
                allowedRequest = true;
                break;
            }
        }
        if (session == null || session.getAttribute("user") == null) {

            if (!allowedRequest) {
                response.sendRedirect("/login");
                return;
            }

        }


        filterChain.doFilter(request, response);

    }


    @Override
    public void destroy() {
        urlList = null;
    }
}
