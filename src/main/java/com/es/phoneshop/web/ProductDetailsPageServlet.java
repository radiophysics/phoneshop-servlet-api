package com.es.phoneshop.web;

import com.es.phoneshop.model.ArrayListProductDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductDetailsPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        int index = uri.lastIndexOf("/");
        String idString = uri.substring(index + 1);

        try {
            request.setAttribute("products", ArrayListProductDao.getInstance().getProduct(Long.valueOf(idString)));
            request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
        } catch (IllegalArgumentException e){
            response.setStatus(404);
        }
    }
}