package com.es.phoneshop.web.servlet;

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
import java.util.ResourceBundle;

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
        String[] errors = new String[productIds.length];
        boolean hasErrors = false;
        Locale locale = request.getLocale();
        ResourceBundle res = ResourceBundle.getBundle("i18n.messages", locale);
        Product product;
        Cart cart = cartService.getCart(request);
        if (deleteValue != null) {
            int deletedProductId = Integer.valueOf(deleteValue);
            cartService.delete(cart, deletedProductId);
            request.setAttribute("success", true);
            request.setAttribute("successMsg", res.getString("delete"));
            response.sendRedirect(request.getRequestURI());
        } else {
            for (int i = 0; i < productIds.length; i++) {
                product = productDao.getProduct(Long.valueOf(productIds[i]));
                try {
                    int quantity = DecimalFormat.getInstance(locale).parse(quantities[i]).intValue();
                    if (quantity < 0) {
                        throw new NumberFormatException();
                    }
                    if (quantity > product.getStock()) {
                        throw new IllegalArgumentException();
                    }
                    cartService.update(cartService.getCart(request), product, quantity);
                    request.setAttribute("success", true);
                    request.setAttribute("successMsg", res.getString("update"));
                } catch (ParseException e) {
                    errors[i] = res.getString("errorNumberFormat");
                    hasErrors=true;
                } catch (NumberFormatException e) {
                    errors[i] = res.getString("errorNegativeNumber");
                    hasErrors = true;
                } catch (IllegalArgumentException e) {
                    errors[i] = res.getString("errorQuantityStock");
                    hasErrors = true;
                }
            }
            if (hasErrors){
                request.setAttribute("quantities", quantities);
                request.setAttribute("error", true);
                request.setAttribute("errors", errors);
                showCartPage(cart, request, response);
            } else {
                response.sendRedirect(request.getRequestURI() + "?updated");
            }
        }
    }

    private void showCartPage(Cart cart, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
    }
}