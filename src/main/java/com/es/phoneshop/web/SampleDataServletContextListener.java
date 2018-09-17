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

        productDao.save(new Product(1L, "A001", "Nokia 3310",
                new BigDecimal("110.00"), Currency.getInstance(Locale.US), 1000));
        productDao.save(new Product(2L, "B113", "Motorola E398",
                new BigDecimal("319.95"), Currency.getInstance(Locale.US), 120));
        productDao.save(new Product(3L, "B119", "Sony Ericsson K750i",
                new BigDecimal("250.00"), Currency.getInstance(Locale.US), 260));
        productDao.save(new Product(4L, "A999", "iPhone XL",
                new BigDecimal("5.95"), Currency.getInstance(Locale.US), 5));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
