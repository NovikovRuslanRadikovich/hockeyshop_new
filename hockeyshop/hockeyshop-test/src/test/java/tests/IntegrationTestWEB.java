package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class IntegrationTestWEB {

    private WebDriver webDriver;
    private String baseUrl;

    Properties properties;
    InputStream inputStream;

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

    @Test
    public void testStartPage() throws Exception {

    }

    @After
    public void tearDown() {
        //closes all opened windows in browser
        webDriver.quit();

        // if we want to close only current window, we should use close() method
    }


}
