package com.fujitsu.fs.rnovikov.servlets;

import com.fujitsu.fs.rnovikov.dao.ProductDao;
import com.fujitsu.fs.rnovikov.dao.ProductDaoImpl;
import com.fujitsu.fs.rnovikov.entities.Product;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/**
 * This Servlet Class is used for products editing
 * The request path contains a numeric attribute, which means an id of a product which will be edited or already edited
 */
@WebServlet("/edit_product/*")
public class EditProduct extends HttpServlet {

    private ProductDao<Product> productDao;
    private final Logger logger = Logger.getLogger(EditProduct.class);

    @Override
    public void init() {
        productDao = ProductDaoImpl.getInstance();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int id = Integer.valueOf(request.getPathInfo().substring(1));
        try {
            Product product = productDao.get(id);
            request.setAttribute("product",product);
        } catch (SQLException e) {
            logger.error("Couldn't get product with id " + id);
        }

        getServletConfig().getServletContext().getRequestDispatcher("/edit_product.ftl").forward(request,response);

    }

    /**
     * This method get's detailed_description and price of a product that must be changed
     * I get an id of user from request path, then edit product via productDao
     * @param request
     * @param response
     * @throws ServletException
     * @throws UnsupportedEncodingException
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String detailed_description = request.getParameter("detailed_description");
        int price = Integer.parseInt(request.getParameter("price"));
        int id = Integer.valueOf(request.getPathInfo().substring(1));
        try{
            productDao.editProduct(id,price,detailed_description);
            response.sendRedirect("/products");
        }   catch (SQLException e) {
            logger.error("Couldn't edit product with id " + id);
        }
    }

}
