package com.es.phoneshop.model;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import static org.junit.Assert.*;

public class ArrayListProductDaoTest {
    private ProductDao productDao = ArrayListProductDao.getInstance();

    @Test
    public void getInstance() {
        Long productId = 1L;
        productDao.save(new Product(productId, "a001", "first (null) product",
                new BigDecimal("1.00"), Currency.getInstance(Locale.US), 0));

        Product product = productDao.getProduct(productId);

        assertEquals(productId, product.getId());
    }

    @Test
    public void save() {
        long productId = 1L;
        productDao.save(new Product(productId, "a001", "first (null) product",
                new BigDecimal("1.00"), Currency.getInstance(Locale.US), 0));

        Product product = productDao.getProduct(productId);

        assertNotNull(product.getId());
    }

    @Test
    public void getProduct() {
        long productId = 2L;
        productDao.save(new Product(productId, "a002", "second product",
                new BigDecimal("6.95"), Currency.getInstance(Locale.US), 100));

        Product product = productDao.getProduct(productId);

        assertNotNull(product.getId());
    }

    @Test
    public void findProducts() {
        int sizeBefore = productDao.findProducts().size();
        productDao.save(new Product(3L, "a003", "third product",
                new BigDecimal("6.95"), Currency.getInstance(Locale.US), 100));

        int sizeAfter = productDao.findProducts().size();

        assertEquals(sizeAfter, (sizeBefore + 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void remove() {
        long productId = 3L;
        productDao.remove(productId);

        Product product = productDao.getProduct(productId);

        assertNull(product.getId());
    }
}