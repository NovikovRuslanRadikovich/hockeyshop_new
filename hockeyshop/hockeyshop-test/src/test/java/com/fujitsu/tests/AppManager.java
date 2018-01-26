package com.fujitsu.tests;

import com.fujitsu.tests.helpers.LoginHelper;
import com.fujitsu.tests.helpers.NavigationHelper;
import com.fujitsu.tests.utils.CredentialsRetriever;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Created by User on 21.01.2018.
 */
public class AppManager {

    String baseUrl;
    public WebDriver driver;

    LoginHelper loginHelper;
    NavigationHelper navigationHelper;

    static AppManager appManager = new AppManager();

    private AppManager() {

        System.setProperty("webdriver.gecko.driver", "D:\\geckodriver-0.19.1.exe");

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Users\\NovikovR\\AppData\\Local\\Mozilla Firefox\\firefox.exe");

        driver = new FirefoxDriver(options);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        baseUrl = CredentialsRetriever.getBaseUrl();

        loginHelper = new LoginHelper(this);
        navigationHelper = new NavigationHelper(this);

    }

    public static AppManager getInstance() {
        if (appManager == null) {
            appManager = new AppManager();
        }

        return appManager;
    }

    public static void destruct() {
        if (appManager != null) {
            appManager = null;
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public LoginHelper getLoginHelper() {
        return loginHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

}
