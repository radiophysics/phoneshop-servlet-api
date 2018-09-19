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
        String[] productIds = request.getParameterValues("productId");
        String[] quantities = request.getParameterValues("quantity");
        String deleteValue = request.getParameter("delete");
        Locale locale = request.getLocale();
        Product product;
        Cart cart = cartService.getCart(request);
        if (deleteValue != null) {
            int deletedProductId = Integer.valueOf(deleteValue);
            cartService.delete(cart, deletedProductId);
            request.getSession().setAttribute("delete", true);
            response.sendRedirect(request.getRequestURI() + "?deleted");
        } else {
            for (int i = 0; i < productIds.length; i++) {
                product = productDao.getProduct(Long.valueOf(productIds[i]));
                try {
                    int quantity = DecimalFormat.getInstance(locale).parse(quantities[i]).intValue();
                    if (quantity < 0)
                        throw new NumberFormatException();
                    if (quantity > product.getStock())
                        throw new IllegalArgumentException();
                    cartService.update(cartService.getCart(request), product, quantity);
                    request.getSession().setAttribute("update", true);
                    response.sendRedirect(request.getRequestURI() + "?updated");
                } catch (ParseException e) {
                    request.getSession().setAttribute("errorNumberFormat",true);
                    showCartPage(cart, request, response);
                    return;
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("errorNegativeNumber",true);
                    showCartPage(cart, request, response);
                    return;
                } catch (IllegalArgumentException e) {
                    request.getSession().setAttribute("errorQuantityStock",true);
                    showCartPage(cart, request, response);
                    return;
                }
            }
        }
    }

    private void showCartPage(Cart cart, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("cart", cart);
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
    }
}