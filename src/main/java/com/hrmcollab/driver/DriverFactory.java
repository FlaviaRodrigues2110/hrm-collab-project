package com.hrmcollab.driver;


import com.hrmcollab.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    public static WebDriver driver;

    public static WebDriver getDriver()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public static void quitDriver()
    {
        driver.quit();
    }
}
