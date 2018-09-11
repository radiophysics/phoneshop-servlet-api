package com.es.phoneshop.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CartService {
    private static final String CART_ATTRIBUTE_NAME = "cart";
    private static volatile CartService instance;

    private CartService() {
    }

    public static CartService getInstance() {
        if (instance == null) {
            synchronized (CartService.class) {
                if (instance == null) {
                    instance = new CartService();
                }
            }
        }
        return instance;
    }

    public synchronized Cart getCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(CART_ATTRIBUTE_NAME);
        if (cart == null) {
            cart = new Cart();
            session.setAttribute(CART_ATTRIBUTE_NAME, cart);
        }
        return cart;
    }

    public synchronized void add(Cart cart, Product product, Integer quantity) {
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getProduct().equals(product)) {
                if (quantity <= (product.getStock() - cartItem.getQuantity())) {
                    cartItem.setQuantity(cartItem.getQuantity() + quantity);
                    return;
                }
            }
        }
        if (quantity <= product.getStock()) {
            cart.getCartItems().add(new CartItem(product, quantity));
        } else {
            throw new IllegalArgumentException("Not enough products in stock.");
        }
    }
}
