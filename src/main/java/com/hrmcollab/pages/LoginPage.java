package com.hrmcollab.pages;
import com.aventstack.extentreports.Status;
import com.hrmcollab.reporting.ReportManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.hrmcollab.driver.DriverFactory.driver;

public class LoginPage {

    private By usernameTextBox = By.name("username");
    private By passwordTextBox = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    public void enterUserName()
    {
        try
        {
            driver.findElement(usernameTextBox).sendKeys("Admin");
            ReportManager.logStep("Enter Username", "Admin", Status.PASS, driver);
        } catch (Exception e) {
            ReportManager.logStep("Enter Username", "Unable to enter username" +
                    ": " + e.getMessage(), Status.FAIL, driver);
            throw e;
        }



    }

    public void enterPassword()
    {
        try
        {
            driver.findElement(passwordTextBox).sendKeys("admin123");
            ReportManager.logStep("Enter Password", "admin123", Status.PASS, driver);
        } catch (Exception e) {
            ReportManager.logStep("Enter Password", "Unable to enter password" +
                    ": " + e.getMessage(), Status.FAIL, driver);
            throw e;
        }


    }

    public void clickOnLogin() {
        driver.findElement(loginButton).click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[contains(., 'Dashboard')]")
            ));
            ReportManager.logStep("Click Login", null, Status.PASS, driver);
        } catch (Exception e) {
            ReportManager.logStep("Click Login", "Dashboard did not load: " + e.getMessage(), Status.FAIL, driver);
            throw e;
        }
    }
}
