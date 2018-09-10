package com.es.phoneshop.web;

import com.es.phoneshop.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

public class ProductDetailsPageServlet extends HttpServlet {
    private ProductDao productDao = ArrayListProductDao.getInstance();
    private CartService cartService = CartService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = Long.valueOf(getId(request));
        Product product = productDao.getProduct(productId);

        showProductPage(product, request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long productId = Long.valueOf(getId(request));
        Product product = productDao.getProduct(productId);
        Integer quantity = null;
        try {
            Locale locale = request.getLocale();
            quantity = DecimalFormat.getInstance(locale).parse(request.getParameter("quantity")).intValue();
        } catch (ParseException e){
            request.setAttribute("error", "Not a number");
            showProductPage(product, request, response);
        }

        Cart cart = cartService.getCart(request);
        cartService.add(cart, product, quantity);

        response.sendRedirect(request.getContextPath()+request.getServletPath() +"/"+productId
                + "?addedQuantity="+quantity);
    }

    private void showProductPage(Product product, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            request.setAttribute("product", product);
            request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);

    }

    private String getId(HttpServletRequest request){
        String uri = request.getRequestURI();
        int index = uri.lastIndexOf("/");
        return uri.substring(index + 1);
    }
}
