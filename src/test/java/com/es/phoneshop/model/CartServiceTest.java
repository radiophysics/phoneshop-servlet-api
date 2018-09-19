package com.es.phoneshop.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Optional;

import static org.junit.Assert.*;

public class CartServiceTest {

    private Cart cart = new Cart();
    private Product product = new Product(1L, "a001", "test product",
            new BigDecimal("1.00"), Currency.getInstance(Locale.US), 10);
    private int quantity = product.getStock() - 1;
    private CartService cartService = CartService.getInstance();

    @Before
    public void clean(){
        for (int i = 0; i < cart.getCartItems().size(); i++) {
            cartService.delete(cart, i);
        }
    }

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
        Mockito.when(sessionMock.getAttribute("cart")).thenReturn(cart);
        cartService.add(cart, product, quantity);

        assertEquals(cart, cartService.getCart(requestMock));
        Mockito.verify(requestMock).getSession();
    }

    @Test
    public void add() {
        cartService.add(cart, product, quantity);

        assertFalse(cart.getCartItems().isEmpty());
    }

    @Test
    public void update() {
        int newQuantity = quantity-1;
        cartService.add(cart, product, quantity);

        cartService.update(cart, product, newQuantity);
        Optional<CartItem> result = cart.getCartItems().stream()
                .filter(p -> p.getProduct().equals(product)).findFirst();

        assertTrue(result.isPresent());
        assertEquals(newQuantity, result.get().getQuantity());
    }
}