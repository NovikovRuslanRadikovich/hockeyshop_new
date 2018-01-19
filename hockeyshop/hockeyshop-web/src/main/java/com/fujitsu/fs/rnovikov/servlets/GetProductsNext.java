package com.fujitsu.fs.rnovikov.servlets;

import com.fujitsu.fs.rnovikov.dao.ProductDaoImpl;
import com.fujitsu.fs.rnovikov.dao.ProductDao;
import com.fujitsu.fs.rnovikov.entities.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This servlet handles requests where user want to see any decade from all products
 * number of decade of products is written in request,
 * it is a request attribute after /getproductsnext substring
 */
@WebServlet("/getproductsnext/*")
class GetProductsNext extends HttpServlet {

    private ProductDao<Product> productDao;

    /**
     * This method initializes a productDao Object
     */
    @Override
    public void init() {

        productDao = ProductDaoImpl.getInstance();
    }

    /**
     * I get a numeric attribute from a servlet path and this numeric value means a number of products decades
     * which must be shown on a page
     * Then I get the other products decades that are following after this decade and put them to request
     * as nextProductsDecades attribute
     * @param request contains a numeric value of products dacede
     * @param response is represented as forwarding to products_decade page
     * @throws IOException is thrown if an input or output error is detected
     * @throws ServletException
     */

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int id = Integer.valueOf(request.getPathInfo().substring(1));
        try {
            request.setAttribute("productsDecade",productDao.getDecadeProduct(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Integer> nextProductsDecades = new ArrayList<>();
        int k = 0;
        try {
            for(int i = id + 1; i < productDao.getAll().length / 10; i++) {
                nextProductsDecades.add(i);
                k = i;
            }
        } catch (SQLException e) {

        }

        try {
            if (productDao.getAll().length % 10 != 0) {
                nextProductsDecades.add(k+1);
            }
        }catch(SQLException ex) {

        }
        request.setAttribute("nextProductsDecades",nextProductsDecades);
        getServletConfig().getServletContext().getRequestDispatcher("products_decade.ftl").forward(request,response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.doPost(request,response);

    }


}
