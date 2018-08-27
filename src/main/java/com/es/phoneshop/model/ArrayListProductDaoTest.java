package com.es.phoneshop.model;

import org.junit.Before;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import static org.junit.Assert.*;

public class ArrayListProductDaoTest {
    private ProductDao productDao;

    @Before
    public void init() {
        productDao = ArrayListProductDao.getInstance();
        productDao.save(new Product(1L, "a000", "first (null) product",
                null, Currency.getInstance(Locale.US), 0));
        productDao.save(new Product(2L, "a002", "second product",
                new BigDecimal("6.95"), Currency.getInstance(Locale.US), 100));
        productDao.save(new Product(3L, "a003", "third product",
                new BigDecimal("4.50"), Currency.getInstance(Locale.US), 7));
        productDao.save(new Product(4L, "a004", "fourth product",
                new BigDecimal("9.99"), Currency.getInstance(Locale.US), 54));
    }

    @org.junit.Test
    public void testGetInstance() {
        assertEquals(1L,productDao.getProduct(1).getId().longValue());
    }

    @org.junit.Test
    public void testSave() {
        assertNotNull(productDao.getProduct(1));
    }

    @org.junit.Test
    public void testGetProduct() {
        assertNotNull(productDao.getProduct(2));
    }

    @org.junit.Test
    public void testFindProducts() {
        assertTrue(!productDao.findProducts().isEmpty());
    }

    @org.junit.Test
    public void testRemove() {
        productDao.remove(1);
        try{
            assertNull(productDao.getProduct(1));
        } catch (IllegalArgumentException e){
            System.out.println("Exception caught.");
        }
    }
}
