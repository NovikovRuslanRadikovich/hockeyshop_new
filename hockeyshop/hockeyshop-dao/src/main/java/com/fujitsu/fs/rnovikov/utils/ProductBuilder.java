package com.fujitsu.fs.rnovikov.utils;

import com.fujitsu.fs.rnovikov.entities.Product;

/**
 * Created by User on 08.01.2018.
 */
public class ProductBuilder {

    private Product product;

    public void reset() {
        product = new Product();
    }

    public Product setName(String name) {
        product.setName(name);
        return product;
    }

    public Product setPrice(int price) {
        product.setPrice(price);
        return product;
    }

    public Product setDescription(String description) {
        product.setDescription(description);
        return product;
    }

    public Product setDetailed_Description(String detailed_description) {
        product.setDetailed_description(detailed_description);
        return product;
    }


    public Product setQuantity(int quantity) {
        product.setQuantity(quantity);
        return product;
    }
}
