package tests;

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

@RunWith(Parameterized.class)
public class IntegrationTestWEBLogin {

    private WebDriver webDriver;
    private String baseUrl;

    Properties properties;
    InputStream inputStream;

    String username;
    String password;

    @Before
    public void setUp() throws IOException {
        System.setProperty("webdriver.gecko.driver","D:\\geckodriver.exe");

        properties = new Properties();
        inputStream = getClass().getClassLoader().getResourceAsStream("test.properties");
        properties.load(inputStream);

        String port = properties.getProperty("http.port");
        String context = properties.getProperty("http.context");

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Users\\NovikovR\\AppData\\Local\\Mozilla Firefox\\firefox.exe");

        options.setLogLevel(Level.WARNING);

        System.out.println(options.getBinary().toString());

        webDriver = new FirefoxDriver(options);

        baseUrl = "http://localhost:" + port + "/" + context;
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    IntegrationTestWEBLogin(String username, String password) {
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
    public void testAuthenticationAbility() {

    }

    public void tearDown() {
        webDriver.quit();
    }




}
