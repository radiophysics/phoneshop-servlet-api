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
        String[] errors = new String[productIds.length];
        boolean hasErrors = false;
        Product product;
        for (int i = 0; i < productIds.length; i++) {
            product = productDao.getProduct(Long.valueOf(productIds[i]));
            Locale locale = request.getLocale();
            try {
                int quantity = DecimalFormat.getInstance(locale).parse(quantities[i]).intValue();
                if (quantity < 0)
                    throw new NumberFormatException();
                if (quantity > product.getStock())
                    throw new IllegalArgumentException();
                cartService.update(cartService.getCart(request), product, quantity);
                request.setAttribute("update", true);
            }catch (ParseException e) {
                errors[i] = "errorNumberFormat";
                hasErrors = true;
            }catch (NumberFormatException e) {
                errors[i] = "errorNegativeNumber";
                hasErrors = true;
            }catch (IllegalArgumentException e) {
                errors[i] = "errorQuantityStock";
                hasErrors = true;
            }
        }
        if(hasErrors) {
            request.setAttribute("errors", errors);
            request.setAttribute("quantities", quantities);
            doGet(request, response);
        }
        else {
            response.sendRedirect("cart?updated");
        }
    }
}
