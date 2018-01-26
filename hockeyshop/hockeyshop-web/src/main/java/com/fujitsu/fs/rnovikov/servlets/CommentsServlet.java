package com.fujitsu.fs.rnovikov.servlets;

import com.fujitsu.fs.rnovikov.dao.CommentDao;
import com.fujitsu.fs.rnovikov.dao.CommentDaoImpl;
import com.fujitsu.fs.rnovikov.entities.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/comments")
public class CommentsServlet extends HttpServlet {

    CommentDao<Comment,Integer> commentDao;

    public void init() {
         commentDao = CommentDaoImpl.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
           super.doGet(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        response.setContentType("text/plain");

        String commentText = request.getParameter("comment");
        String productId = request.getParameter("productId");
        String username = (String) request.getSession().getAttribute("user");



        Comment comment = null;
        comment = new Comment(Integer.parseInt(productId),commentText,username);
        commentDao.save(comment);

        response.getWriter().write(comment.toString());
    }

}
