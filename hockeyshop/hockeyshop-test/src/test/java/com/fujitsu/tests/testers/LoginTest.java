package com.fujitsu.tests.testers;

import com.fujitsu.tests.bases.TestBase;
import com.fujitsu.tests.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static org.junit.Assert.assertEquals;

/**
 * Created by User on 21.01.2018.
 */
@RunWith(Parameterized.class)
public class LoginTest extends TestBase {

        String username;
        String password;

        private LoginTest() {

        }

        public LoginTest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Parameterized.Parameters
        public static Collection testCredentials() {
            return Arrays.asList(new Object[][] {
                    {"ruslan","novikov"},
                    {"vasiliy","vasin"},
                    {"petr","petrov"}

            });
        }

        @Test
        public void testAuthenticationDisAbility() throws Exception {
            appManager.getLoginHelper().logout();
            appManager.getLoginHelper().login(new User(username,password));
            assertEquals(false,appManager.getLoginHelper().isLoggedIn());
        }


}
