package com.es.phoneshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListProductDao implements ProductDao {

    private static volatile ArrayListProductDao instance;
    private List<Product> products;

    public static ArrayListProductDao getInstance() {
        if (instance == null){
            synchronized (ArrayListProductDao.class) {
                if (instance == null){
                    instance = new ArrayListProductDao();
                }
            }
        }
        return instance;
    }

    private ArrayListProductDao() {
        products = new ArrayList<>();
    }

    public synchronized void save(Product product) {
        products.add(product);
    }

    public synchronized Product getProduct(long id) {
        return products.stream()
                .filter((p) -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No products with such id."));

    }

    public synchronized List<Product> findProducts() {
        return products.stream()
                .filter(p -> p.getPrice() != null && p.getStock() > 0)
                .collect(Collectors.toList());
    }

    public synchronized void remove(long id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                break;
            }
        }
    }
}