package com.es.phoneshop.web;

import com.es.phoneshop.model.ArrayListProductDao;
import com.es.phoneshop.model.Product;
import com.es.phoneshop.model.ProductDao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class SampleDataServletContextListener implements ServletContextListener {
    private ProductDao productDao = ArrayListProductDao.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String insertSampleDataString = sce.getServletContext().getInitParameter("insertSampleData");
        if (!Boolean.valueOf(insertSampleDataString))
            return;

        productDao.save(new Product(1L, "a000", "first (null) product",
                null, Currency.getInstance(Locale.US), 0));
        productDao.save(new Product(2L, "a002", "second product",
                new BigDecimal("6.95"), Currency.getInstance(Locale.US), 100));
        productDao.save(new Product(3L, "a003", "third product",
                new BigDecimal("4.50"), Currency.getInstance(Locale.US), 7));
        productDao.save(new Product(4L, "a004", "fourth product",
                new BigDecimal("9.99"), Currency.getInstance(Locale.US), 54));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
