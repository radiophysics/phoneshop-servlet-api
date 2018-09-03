package com.es.phoneshop.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import static org.junit.Assert.*;

public class CartServiceTest {

    private CartService cartService = CartService.getInstance();

    @Test
    public void getInstance() {
        CartService newCartService = CartService.getInstance();

        assertEquals(cartService, newCartService);
    }

    /*@Test
    public void getCart() {
        Cart cart = new Cart();
        Product product = new Product(1L, "a001", "test product",
                new BigDecimal("1.00"), Currency.getInstance(Locale.US), 10);

        CartService cartService = CartService.getInstance();

        assertNotNull(cartService.getCart(request?));
    }

    @Test
    public void add() {
        int sizeBefore = cartService.getCart(request?).getCartItems().size();
        Cart cart = new Cart();
        Product product = new Product(3L, "a001", "test product",
                new BigDecimal("1.00"), Currency.getInstance(Locale.US), 10);
        CartService cartService = CartService.getInstance();

        cartService.add(cart, product, 5);
        int sizeAfter = cartService.getCart(request?).getCartItems().size();


        assertTrue(sizeAfter == (sizeBefore + 1));
    }*/
}