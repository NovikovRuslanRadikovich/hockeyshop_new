package com.fujitsu.tests;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.Assert.assertEquals;

public class ShopSmokeTest {

    @BeforeClass
    public static void initDriverOptions() {
        System.setProperty("webdriver.gecko.driver", "D:\\geckodriver-0.19.1.exe");
    }

    @Test
    public void testHomepage() {
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Users\\NovikovR\\AppData\\Local\\Mozilla Firefox\\firefox.exe");

        WebDriver driver = new FirefoxDriver(options);
        driver.get("http://localhost:8081");
        assertEquals("Главная", driver.getTitle());
        driver.quit();
    }

}
