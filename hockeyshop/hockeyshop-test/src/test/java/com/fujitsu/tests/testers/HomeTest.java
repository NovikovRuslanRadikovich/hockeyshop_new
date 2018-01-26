package com.fujitsu.tests.testers;

import com.fujitsu.tests.bases.AuthBase;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 21.01.2018.
 */
@RunWith(DataProviderRunner.class)
public class HomeTest extends AuthBase {

    private static final String FILE_PATH = "D:\\hockeyshop\\hockeyshop-test\\src\\main\\resources\\homepageelements.json";

    private static JsonParser parser = new JsonParser();
    static Object object;
    static JsonObject obj;

    static {

        try {
            object = parser.parse(new FileReader(FILE_PATH));

            obj = (JsonObject) object;

        } catch(Exception ex) {
            ex.printStackTrace();
        }


    }

    @DataProvider()
    public static List<String> elementsIds() throws FileNotFoundException {
        object = parser.parse(new FileReader(FILE_PATH));

        obj = (JsonObject) object;

        List<String> notes = new ArrayList<>();

        JsonArray array = obj.get("home_elements").getAsJsonArray();

        for (int i = 0; i < array.size(); i++) {
            JsonObject obj = array.get(i).getAsJsonObject();
            String linktext = obj.get("id").getAsString();
            notes.add(linktext);
        }

        return notes;
    }

    @Test
    @UseDataProvider("elementsIds")
    public void testElementsExistence(String id) {
        Assert.assertEquals(true, appManager.getDriver().findElement(By.id(id)) != null);
    }



}
