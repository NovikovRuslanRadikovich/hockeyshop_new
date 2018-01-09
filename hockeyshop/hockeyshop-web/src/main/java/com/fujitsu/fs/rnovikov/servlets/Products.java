package com.fujitsu.fs.rnovikov.servlets;

import com.fujitsu.fs.rnovikov.dao.ProductDao;
import com.fujitsu.fs.rnovikov.dao.ProductDaoImpl;
import com.fujitsu.fs.rnovikov.entities.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 07.01.2018.
 */
@WebServlet("/products")
public class Products extends HttpServlet {
    ProductDao<Product> productDao;

    @Override
    public void init() throws ServletException {
        productDao = ProductDaoImpl.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if("get_products".equals(request.getParameter("action"))){
            if (request.getSession().getAttribute("admin") != null) {
                request.setAttribute("admin","admin");
            }
            Product[] allproducts = null;

            try {
                allproducts = productDao.getAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (allproducts.length > 10 ) {
                ArrayList<Product> productsDecade = new ArrayList();

                for(int i = 0 ; i < 10;i++) {
                    productsDecade.add(allproducts[i]);
                }
                request.setAttribute("productsDecade",productsDecade);
                ArrayList<Product> nextProducts = new ArrayList();

                for(int j = 10 ; j < allproducts.length;j++) {
                    nextProducts.add(allproducts[j]);
                }

                request.setAttribute("nextProducts",nextProducts);
            } else {
                request.setAttribute("productsDecade",allproducts);
            }

            getServletConfig().getServletContext().getRequestDispatcher("/get_products_ajax.ftl").forward(request, response);
            return;
        }

        if("delete".equals(request.getParameter("action"))){
            int id = Integer.parseInt(request.getParameter("id"));

            try {
                productDao.delete(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return;
        }

        getServletConfig().getServletContext().getRequestDispatcher("/tov.ftl").forward(request, response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        super.doPost(request,response);
    }
}
