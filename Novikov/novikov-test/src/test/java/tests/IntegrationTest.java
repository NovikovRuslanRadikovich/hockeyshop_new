package tests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;




/**
 * Created by ruslan on 26.11.2017.
 */
public class IntegrationTest {

    private WebDriver webDriver;
    private String baseUrl;


    Properties properties;
    InputStream inputStream;

    @Before
    public void setUp() throws IOException {
        System.setProperty("webdriver.gecko.driver", "D:\\geckodriver.exe");

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
    public void testPage() throws Exception {
        webDriver.get(baseUrl + "home");

        Thread.sleep(10000);
        webDriver.findElement(By.name("username")).sendKeys("Ruslan");
        Thread.sleep(500);
        webDriver.findElement(By.id("submit")).click();
        Thread.sleep(2000);


        String bodyText = webDriver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(!bodyText.contains("Hello anonymous !!!"));

    }

    @After
    public void tearDown() {
        webDriver.quit();
    }


}
