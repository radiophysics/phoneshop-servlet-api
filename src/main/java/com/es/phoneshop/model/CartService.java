package com.es.phoneshop.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

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

        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(ci -> ci.getProduct().equals(product))
                .findAny();

        if (!existingItem.isPresent()){
            if (product.getStock() >= quantity){
                cart.getCartItems().add(new CartItem(product, quantity));
                return;
            }
        }

        existingItem
                .filter(ci -> ci.getQuantity() + quantity <= product.getStock())
                .ifPresent(ci -> ci.setQuantity(ci.getQuantity() + quantity));
    }
}
