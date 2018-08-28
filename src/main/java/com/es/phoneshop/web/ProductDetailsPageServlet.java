package com.es.phoneshop.web;

import com.es.phoneshop.model.ArrayListProductDao;
import com.es.phoneshop.model.Product;
import com.es.phoneshop.model.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductDetailsPageServlet extends HttpServlet {
    private ProductDao productDao = ArrayListProductDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setAttribute("product", getId(request));
            request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
        } catch (NumberFormatException e){
            response.setStatus(500);
        } catch (IllegalArgumentException e){
            response.setStatus(404);
        }
    }

    private Product getId(HttpServletRequest request){
        String uri = request.getRequestURI();
        int index = uri.lastIndexOf("/");
        return productDao.getProduct(Long.valueOf(uri.substring(index + 1)));
    }
}
