package com.fujitsu.fs.rnovikov.entities;

/**
 * Created by User on 06.01.2018.
 */
public class User {

    private String name;
    public String password;
    private int id;
    private String phoneNumber;


    public User(String name, String password, String phoneNumber){
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;

    }


    public boolean confirmPassword(String password){
        return this.password.equals(password);
    }

    public String getName() {
        return name;
    }

    public String getPasswordHash(){
        return password;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setName(String login) {
        this.name = login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Имя: " + name + "; id: " + Integer.toString(id) +
                "; number: " + getPhoneNumber();
    }
    @Override
    public boolean equals(Object user) {
        return true;
    }
}
