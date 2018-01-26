package com.fujitsu.tests.helpers;

import com.fujitsu.tests.AppManager;

import static java.lang.Thread.sleep;

/**
 * Created by User on 21.01.2018.
 */
public class NavigationHelper extends HelperBase{

    public NavigationHelper(AppManager appManager) {
        super(appManager);
    }

    public static void getHomePage() throws InterruptedException {
        sleep(500);
        driver.get(baseUrl + "/");
    }

    public static void getLoginPage() throws InterruptedException {
        sleep(500);
        driver.get(baseUrl + "/login");
    }



}
