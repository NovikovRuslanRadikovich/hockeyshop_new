package com.fujitsu.fs.rnovikov.entities;

public class Comment {

    private int product_id;
    private String comment;
    private String username;


    public Comment(int product_id, String comment, String username) {
        this.product_id = product_id;
        this.comment = comment;
        this.username = username;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getComment() {
        return comment;
    }

    public String getUsername() {
        return username;
    }


    public String toString() {
        return "Пользователь: " + username + "; Комментарий: " + comment;
    }
}
