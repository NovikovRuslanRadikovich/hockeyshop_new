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
import java.util.List;

/**
 * This Servlet Class provide functionality for operations with products for users and also for admin
 */
@WebServlet("/products")
public class ProductsServlet extends HttpServlet {
    ProductDao<Product,Integer,String> productDao;

    /**
     * This method initializes Data Access Object for Product Entity
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        productDao = ProductDaoImpl.getInstance();
    }

    /**
     * @param request  If request has delete parameter it means that admin deletes a product from a DataBase and id of product
     *                 to be deleted is also sent as parameter to this method
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        if ("delete".equals(request.getParameter("action"))) {
            int id = Integer.parseInt(request.getParameter("id"));

            productDao.delete(id);

        }  else {

            if (request.getSession().getAttribute("admin") != null) {
                request.setAttribute("admin", "admin");
            }
            Product[] allproducts = null;

            allproducts = productDao.getAll();
            if (allproducts.length > 10) {
                List<Product> productsDecade = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                    productsDecade.add(allproducts[i]);
                }
                request.setAttribute("productsDecade", productsDecade);

                List<Integer> nextProductsDecades = new ArrayList<>();
                int k = 0;
                for(int i = 1; i < productDao.getAll().length / 10; i++) {
                    nextProductsDecades.add(i);
                    k = i;
                }
                if (productDao.getAll().length % 10 != 0) {
                    nextProductsDecades.add(k+1);
                }

                request.setAttribute("nextProductsDecades", nextProductsDecades);
            } else {
                request.setAttribute("productsDecade", allproducts);
            }

            getServletConfig().getServletContext().getRequestDispatcher("/products_decade.ftl").forward(request, response);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
