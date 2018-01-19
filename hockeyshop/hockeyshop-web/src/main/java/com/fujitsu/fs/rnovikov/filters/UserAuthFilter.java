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
 * This Filter Class caches all requests before all specified servlets,
 * Retrieves Session Object from users request,
 * if session doesn't have user attribute then users is being redirected to login page,
 * else filter gives the request handling to a defined servlet for this request
 */

public class UserAuthFilter implements Filter {

    private List<String> urlList;

    /**
     * @param filterConfig
     * @throws ServletException
     * firstly this method gets allow-urls parameter, which is written in web configuration file for UserAuthFilter
     * allow-urls String is compound as all servlet urls splited with commas,
     * urlList list is initialized as collection of all urls from allow-urls string
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String initParameter = filterConfig.getInitParameter("allow-urls-users");
        urlList = new ArrayList<String>();
        Collections.addAll(urlList, initParameter.split(","));
    }

    /**
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     * I from ServletPath object from a request
     * Then if ServletPath Object starts with one of allowed requests it means that request is allowed
     * If session doesn't have user attribute and request is not allowed user is redirected to login page
     */
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
