package com.fujitsu.fs.rnovikov.entities;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

/**
 * Created by User on 06.01.2018.
 */
public class User {

    private String name;
    public String password;
    private int user_id;
    private String phoneNumber;


    public User(String name, String password, String phoneNumber){
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;

    }


    public String getName() {
        return name;
    }

    public String getPasswordHash(){ return DigestUtils.md5Hex(password) ;}

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public int getId() {
        return user_id;
    }

    public void setName(String login) {
        this.name = login;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Имя: " + name + "; id: " + Integer.toString(user_id) +
                "; number: " + getPhoneNumber();
    }
    @Override
    public boolean equals(Object user) {
        return user != null && user instanceof User && ((User) user).getName().equals(name) && ((User) user).getPasswordHash().equals(DigestUtils.md5Hex(password)) && ((User) user).getPhoneNumber().equals(phoneNumber);

    }

}
