package com.es.phoneshop.model;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import static org.junit.Assert.*;

public class CartServiceTest {

    private Cart cart = new Cart();
    private Product product = new Product(1L, "a001", "test product",
            new BigDecimal("1.00"), Currency.getInstance(Locale.US), 10);
    private int quantity = product.getStock() - 1;
    private CartService cartService = CartService.getInstance();

    @Test
    public void getInstance() {
        CartService newCartService = CartService.getInstance();

        assertEquals(cartService, newCartService);
    }

    @Test
    public void getCart() {
        HttpServletRequest requestMock = Mockito.mock(HttpServletRequest.class);
        HttpSession sessionMock = Mockito.mock(HttpSession.class);

        Mockito.when(requestMock.getSession()).thenReturn(sessionMock);
        Mockito.when(sessionMock.getAttribute("cart")).thenReturn(requestMock);

        Mockito.verify(requestMock.getSession(), Mockito.times(1));
    }

    @Test
    public void add() {
        cartService.add(cart, product, quantity);

        assertFalse(cart.getCartItems().isEmpty());
    }
}