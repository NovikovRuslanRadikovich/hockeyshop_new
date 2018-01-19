package com.fujitsu.fs.rnovikov.servlets;

import com.fujitsu.fs.rnovikov.dao.ProductDao;
import com.fujitsu.fs.rnovikov.dao.ProductDaoImpl;
import com.fujitsu.fs.rnovikov.dao.UserDao;
import com.fujitsu.fs.rnovikov.dao.UserDaoImpl;
import com.fujitsu.fs.rnovikov.entities.Product;
import com.fujitsu.fs.rnovikov.entities.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * This Servlet Class handle queries for admin page and admin operations
 *
 */
@WebServlet("/admin")

public class Admin extends HttpServlet {
    private final int MaxFileSize = 1024 * 1024 * 3;
    private ProductDao<Product> productDao;
    private UserDao<User> userDao;

     Product product;
     String nameOfProduct;

    @Override
    public void init() throws ServletException {
        productDao = ProductDaoImpl.getInstance();
        userDao = UserDaoImpl.getInstance();

    }

    /**
     *
     * @param request
     * If request doesn't have an admin attribute it means that this link is not allowed for user
     * And he must be redirected to a main page
     * Else if admin attribute is present in session it means that this is an admin
     * Then if request has a delete attribute
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        if (request.getSession().getAttribute("admin") == null) {
//            response.sendRedirect("/");
//            return;
//        }

        if ("delete".equals(request.getParameter("action"))) {

            String id = request.getParameter("id");

            if (id != null) {
                try {
                    UserDaoImpl.getInstance().delete(userDao.get(id));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

//        User[] users = new User[0];
//        try {
//            users = UserDaoImpl.getInstance().getAll().toArray(new User[0]);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        request.setAttribute("Users", users);

        getServletConfig().getServletContext().getRequestDispatcher("/admin/admin.ftl").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        product = new Product();

        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletContext servletContext = this.getServletConfig().getServletContext();

        factory.setSizeThreshold(1024*1024);

        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");

        factory.setRepository(repository);

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setSizeMax(MaxFileSize);

        try {
            List fileItems = upload.parseRequest(request);

            Iterator iterator = fileItems.iterator();
            if (iterator.hasNext()) {
                do {
                    FileItem item = (FileItem) iterator.next();
                    if (item.isFormField()) {
//                        processFormField(item);
                    } else {
                        processUploadedFile(item);
                    }
                }
                while (iterator.hasNext());
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
//        productDao = ProductDaoImpl.getInstance();
//        try {
//            productDao.save(product);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        response.sendRedirect("/admin");
    }

    private void processFormField(FileItem item) {
        String fieldName = item.getFieldName();

        switch(fieldName) {
            case "product_name":
                product.setName(item.getString());
                nameOfProduct = item.getString();
                break;
            case "description":
                product.setDescription(item.getString());
                break;
            case "detailed_description":
                product.setDetailed_description(item.getString());
                break;
            case "price":
                product.setPrice(Integer.parseInt(item.getString()));
                break;
            case "quantity":
                product.setQuantity(Integer.parseInt(item.getString()));
        }

    }

    private String processUploadedFile(FileItem item) throws Exception {


//      String path = System.getProperty("user.dir") +  "\\hockeyshop-web\\src\\main\\webapp\\imagesOfproducts\\" + "Ovi" + ".jpg";

        String path = "D:\\hockeyshop\\hockeyshop-web\\src\\main\\webapp\\imagesOfproducts\\" + "Toews" + ".jpg";

        //   String path = "D:\\hockeyshop\\hockeyshop-web\\src\\main\\webapp\\imagesOfproducts\\" + nameOfProduct + ".jpg";

        File uploadedFile = new File(path);

        uploadedFile.createNewFile();

        item.write(uploadedFile);

        return item.getName();
    }
}
