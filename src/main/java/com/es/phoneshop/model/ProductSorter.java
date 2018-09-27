package com.es.phoneshop.model;

import java.util.ArrayList;

public class ProductSorter {
    private ProductDao productDao = ArrayListProductDao.getInstance();
    private ArrayList<Product> productSorted = new ArrayList<>();

    public void sort(String str){
        for (Product product : productDao.findProducts()){
            if ((product.getCode().matches("(.*)"+str+"(.*)")) &&
                    (product.getDescription().matches("(.*)"+str+"(.*)"))){
                productSorted.add(product);
            }
        }
    }

    public ArrayList<Product> getProductSorted() {
        return productSorted;
    }
}
