package com.fujitsu.fs.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class CredentialsRetriever {

    private static String username;

    private static final String  FILE_PATH = "src/test/resources/credentials.json";

    private static JsonParser jsonParser = new JsonParser();
    static Object object;
    static JsonObject jsonObject;

    static {
        try {
           object = jsonParser.parse(new FileReader(FILE_PATH));

           jsonObject = (JsonObject) object;
        } catch(FileNotFoundException ex) {
             ex.printStackTrace();
        }
    }

    public static String getUsername() {
        if(username == null) {
            username =  jsonObject.get("username").getAsString();
        }
        return username;
    }



}
