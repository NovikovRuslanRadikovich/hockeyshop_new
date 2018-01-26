package com.fujitsu.tests.helpers;

import com.fujitsu.tests.AppManager;
import com.fujitsu.tests.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static java.lang.Thread.sleep;

/**
 * Created by User on 21.01.2018.
 */
public class LoginHelper extends HelperBase {


    public LoginHelper(AppManager appManager) {
        super(appManager);
    }

    public static void login(User user) throws Exception {

        if(!isLoggedIn()) {

            NavigationHelper.getLoginPage();
            sleep(500);
            driver.findElement(By.id("name")).clear();
            sleep(500);
            driver.findElement(By.id("password")).clear();
            sleep(500);
            driver.findElement(By.id("name")).sendKeys(user.getName());
            sleep(500);
            driver.findElement(By.id("password")).sendKeys(user.getPassword());
            driver.findElement(By.id("login")).click();

            sleep(500);
        }
    }

    public static void logout() throws Exception {

        if(isLoggedIn()) {

            Thread.sleep(500);
            driver.findElement(By.id("ex")).click();

        }
    }

    public static boolean isLoggedIn() {
        return isElementPresent(By.id("ex"));


    }

}
