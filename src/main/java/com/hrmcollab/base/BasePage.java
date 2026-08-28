package com.hrmcollab.base;

import com.aventstack.extentreports.Status;
import com.hrmcollab.driver.DriverFactory;
import com.hrmcollab.reporting.ReportManager;
import com.hrmcollab.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public void setUp()
    {
        WebDriver driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("url"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-login-logo")));
        ReportManager.logStep("Open Login Page", ConfigReader.getProperty("url"), Status.PASS, driver);

    }


}
