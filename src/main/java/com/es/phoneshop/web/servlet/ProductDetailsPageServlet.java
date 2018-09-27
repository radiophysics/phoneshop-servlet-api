package com.es.phoneshop.web.servlet;

import com.es.phoneshop.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

public class ProductDetailsPageServlet extends HttpServlet {
    private ProductDao productDao = ArrayListProductDao.getInstance();
    private CartService cartService = CartService.getInstance();
    private ArrayList<Product> comparisonList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long productId = Long.valueOf(getId(request));
        Product product = productDao.getProduct(productId);

        showProductPage(product, request, response);
    }

    public ArrayList<Product> getComparisonList() {
        return comparisonList;
    }

    public void setComparisonList(ArrayList<Product> comparisonList) {
        this.comparisonList = comparisonList;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long productId = Long.valueOf(getId(request));
        Product product = productDao.getProduct(productId);
        int quantity;
        Locale locale = request.getLocale();
        Cart cart = cartService.getCart(request);

        if (request.getParameter("compare")!=null){
            comparisonList.add(product);
        }

        try {
            quantity = DecimalFormat.getInstance(locale).parse(request.getParameter("quantity")).intValue();
            if (quantity < 0) {
                throw new NumberFormatException();
            }
            cartService.add(cart, product, quantity);
        } catch (ParseException e) {
            request.setAttribute("errorNumberFormat", true);
            showProductPage(product, request, response);
            return;
        } catch (NumberFormatException e) {
            request.setAttribute("errorNegativeNumber", true);
            showProductPage(product, request, response);
            return;
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorQuantityStock", true);
            showProductPage(product, request, response);
            return;
        }

        request.setAttribute("addedQuantity", quantity);
        response.sendRedirect(request.getRequestURI() + "?addedQuantity=" + quantity);
    }

    private void showProductPage(Product product, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("product", product);
        request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
    }

    private String getId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        int index = uri.lastIndexOf("/");
        return uri.substring(index + 1);
    }
}
