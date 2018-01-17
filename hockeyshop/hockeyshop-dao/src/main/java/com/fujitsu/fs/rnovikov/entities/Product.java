package com.fujitsu.fs.rnovikov.entities;

/**
 * Created by User on 06.01.2018.
 */
public class Product {

    private int product_id;
    public String name;
    private int price;
    public String description;
    public String detailed_description;
    private int quantity;

    public Product() {

    }

    public Product(String name, int price,String description, String detailed_description, int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.detailed_description = detailed_description;
        this.quantity = quantity;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDetailed_description(String detailed_description) {
        this.detailed_description = detailed_description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getDetailed_description() {
        return detailed_description;
    }

    public int getQuantity() {
        return quantity;
    }

}
