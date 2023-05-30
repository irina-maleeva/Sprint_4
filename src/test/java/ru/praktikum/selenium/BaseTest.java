package ru.praktikum.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    WebDriver webDriver;

    @Before
    public void setUp()  {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().browserVersion("113").setup();
        webDriver = new ChromeDriver(options);
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @After
    public void tearDown()  {
        webDriver.quit();
    }
}
