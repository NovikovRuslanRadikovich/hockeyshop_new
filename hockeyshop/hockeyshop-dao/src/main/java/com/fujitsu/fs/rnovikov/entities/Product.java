package com.fujitsu.fs.rnovikov.entities;

/**
 * Created by User on 06.01.2018.
 */
public class Product {

    private int id;
    public String name;
    private int price;
    public String description;
    public String detailed_description;
    private int quantity;
    private String imagePath;

    public Product() {

    }

    public Product(String name, int price, String image, String description, String detailed_description, int quantity) {
        this.name = name;
        this.price = price;
        this.imagePath = image;
        this.description = description;
        this.detailed_description = detailed_description;
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getId() {
        return id;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {this.imagePath = imagePath;}

}
