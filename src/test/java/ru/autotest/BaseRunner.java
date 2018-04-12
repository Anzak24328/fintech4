package ru.autotest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseRunner {

    private static ThreadLocal<WebDriver> tl = new ThreadLocal<>();
    WebDriver driver;
    private String browserName = System.getProperty("browser");
    String baseUrl;


    @BeforeTest
    public void setUp(){
        if (tl.get() != null)  driver = tl.get();
        else {
            driver = getDriver();
            tl.set(driver);
        }
        driver.manage().window().maximize();
        baseUrl = "https://www.google.ru/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            driver.quit();
            driver = null;
        }));
    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    private WebDriver getDriver() {
        try {
            BrowserFactory.valueOf(System.getProperty("browser"));
        } catch (NullPointerException | IllegalArgumentException e) {
            browserName = randomBrowserPicker();
            System.setProperty("browser", browserName);
        }
        return BrowserFactory.valueOf(browserName).create();
    }

    private String randomBrowserPicker() {
        System.out.println("\nThe driver is not set. Running a random browser...");
        int pick = new Random().nextInt(BrowserFactory.values().length);
        return BrowserFactory.values()[pick].toString();
    }
}
