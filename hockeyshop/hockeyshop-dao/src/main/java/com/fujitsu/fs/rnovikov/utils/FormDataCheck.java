package com.fujitsu.fs.rnovikov.utils;


import com.fujitsu.fs.rnovikov.dao.UserDao;
import com.fujitsu.fs.rnovikov.dao.UserDaoImpl;
import com.fujitsu.fs.rnovikov.entities.User;

import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FormDataCheck {
    private FormDataCheck(){
    }
    public static TreeMap<String, String> checkAllFieldsAndGetErrorMessageIfFieldsAreInvalid(String fullName, String phoneNumber, String DOB, String password1, String password2, String sex, String city) throws SQLException {
        TreeMap<String, String> errorMessage = new TreeMap<>();

        if(exists(fullName)) {
            errorMessage.put("error","\nИмя занято другим пользователем");
        }

        if(fullName == null){
            errorMessage.put("error", "\nНе верное имя");
        }

        if(!checkDOB(DOB)){
            errorMessage.put("error", "\nНе верная дата");
        }

        if (!checkPhoneNumber(phoneNumber)){
            errorMessage.put("error", "\nНе верный номер телефона");
        }
        if (!checkPassword(password1)){
            errorMessage.put("error", "\nНе верный пароль");
        }
        else{
            if (!password1.equals(password2)){
                errorMessage.put("error", "\nНе верный повтор пароля");
            }
        }
        if (!checkGender(sex)){
            errorMessage.put("error", "\nПол не указан");
        }
        if (!checkCity(city)){
            errorMessage.put("error", "\nГород не указан");
        }

        return errorMessage;
    }

    public static boolean exists(String fullName) throws SQLException {
        UserDao<User> userdao = UserDaoImpl.getInstance();
        List<User> allUsers = userdao.getAll();
        for(User user : allUsers) {
            if(user.getName().equals(fullName)) {
                return true;
            }
        }
        return false;
    }
    public static boolean checkDOB(String DOB){
        if (DOB != null){
            Pattern p = Pattern.compile("^\\d\\d\\d\\d-\\d\\d-\\d\\d$");
            Matcher m = p.matcher(DOB);
            return m.matches();
        }
        else{
            return false;
        }
    }

    public static boolean checkPassword(String password){
        if (password != null){
            Pattern p = Pattern.compile("^[a-zA-Z0-9]{4,}$");
            Matcher m = p.matcher(password);
            return m.matches();
        }
        else{
            return false;
        }
    }
    public static boolean checkPhoneNumber(String phoneNumber){
        if (phoneNumber != null){
            Pattern p = Pattern.compile("^[0-9]+$");
            Matcher m = p.matcher(phoneNumber);
            return m.matches();
        }
        else{
            return false;
        }
    }
    public static boolean checkGender(String sex){
        if (sex != null){
            if (sex.equals("male") || sex.equals("female")){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    public static boolean checkCity(String city) {
        return "Kazan".equals(city) || "Moscow".equals(city) || "Spb".equals(city);
    }
}
