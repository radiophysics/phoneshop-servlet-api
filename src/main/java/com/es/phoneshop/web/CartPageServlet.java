package com.es.phoneshop.web;

import com.es.phoneshop.model.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

public class CartPageServlet extends HttpServlet{
    private CartService cartService;
    private ArrayListProductDao productDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        cartService = CartService.getInstance();
        productDao = ArrayListProductDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cart", cartService.getCart(request));
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = Long.valueOf(getId(request));
        Product product = productDao.getProduct(productId);
        int quantity;
        Locale locale = request.getLocale();
        Cart cart = cartService.getCart(request);

        try {
            quantity = DecimalFormat.getInstance(locale).parse(request.getParameter("quantity")).intValue();
            if (quantity < 0) {
                throw new NumberFormatException();
            }
            cartService.add(cart, product, quantity);
        } catch (ParseException e) {
            request.setAttribute("errorNumberFormat", true);
            showCartPage(cart, request, response);
            return;
        } catch (NumberFormatException e) {
            request.setAttribute("errorNegativeNumber", true);
            showCartPage(cart, request, response);
            return;
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorQuantityStock", true);
            showCartPage(cart, request, response);
            return;
        }

        request.setAttribute("update", true);
        request.setAttribute("updatedQuantity", quantity);
        response.sendRedirect(request.getRequestURI() + "?updatedQuantity=" + quantity);
    }

    private String getId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        int index = uri.lastIndexOf("/");
        return uri.substring(index + 1);
    }

    private void showCartPage(Cart cart, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
    }
}
