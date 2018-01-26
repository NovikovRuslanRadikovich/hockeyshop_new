package com.fujitsu.tests.helpers;

import com.fujitsu.tests.AppManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * Created by User on 21.01.2018.
 */
public class HelperBase {

    AppManager appManager;

    static WebDriver driver;
    static String baseUrl;


    HelperBase(AppManager appManager) {

        this.appManager = appManager;
        driver = appManager.getDriver();
        baseUrl = appManager.getBaseUrl();


    }

    protected static boolean isElementPresent(By by) {
        boolean present;
        try {
            driver.findElement(by);
            present = true;
        } catch (NoSuchElementException e) {
            present = false;
        }

        return present;
    }

}
