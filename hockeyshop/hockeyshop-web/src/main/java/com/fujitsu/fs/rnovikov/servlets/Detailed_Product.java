package com.fujitsu.fs.rnovikov.servlets;

import com.fujitsu.fs.rnovikov.dao.CommentDao;
import com.fujitsu.fs.rnovikov.dao.CommentDaoImpl;
import com.fujitsu.fs.rnovikov.dao.ProductDao;
import com.fujitsu.fs.rnovikov.dao.ProductDaoImpl;
import com.fujitsu.fs.rnovikov.entities.Comment;
import com.fujitsu.fs.rnovikov.entities.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/product_detail/*")
public class Detailed_Product extends HttpServlet {

    private ProductDao<Product> productDao;
    private CommentDao<Comment,Integer> commentDao;

    /**
     * This method simply initializes productDao Object
     */
    @Override
    public void init() {
        productDao = ProductDaoImpl.getInstance();
        commentDao = CommentDaoImpl.getInstance();
    }

    /**
     * This method handles requests for querying detailed products descriptions
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int id = Integer.valueOf(request.getPathInfo().substring(1));

        Product product = null;
        product = productDao.get(id);
        request.setAttribute("product",product);

        List<Comment> comments = null;

        comments = commentDao.get(id);
        request.setAttribute("comments",comments);

        request.setAttribute("username",request.getSession().getAttribute("user"));

        getServletConfig().getServletContext().getRequestDispatcher("/detailed_product.ftl").forward(request,response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request,response);
    }
}
