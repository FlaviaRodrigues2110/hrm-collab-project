package com.hrmcollab.pages;

import com.aventstack.extentreports.Status;
import com.hrmcollab.driver.DriverFactory;
import com.hrmcollab.reporting.ReportManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage {

    private final WebDriver driver = DriverFactory.getCurrentDriver();

    private By usernameTextBox = By.name("username");
    private By passwordTextBox = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By dashboardHeading = By.xpath("//h6[contains(., 'Dashboard')]");
    private By errorMessage = By.xpath("//p[contains(@class,'alert-content-text')]");
    private By usernameRequiredError = By.xpath("//input[@name='username']/parent::div/following-sibling::span[text()='Required']");
    private By passwordRequiredError = By.xpath("//input[@name='password']/parent::div/following-sibling::span[text()='Required']");

    public void enterUserName(String username) {
        try {
            driver.findElement(usernameTextBox).sendKeys(username);
            ReportManager.logStep("Enter Username", username, Status.PASS, driver);
        } catch (Exception e) {
            ReportManager.logStep("Enter Username", "Unable to enter username: " + e.getMessage(), Status.FAIL, driver);
            throw e; // genuine technical failure — element issue, not a login-outcome issue
        }
    }

    public void enterPassword(String password) {
        try {
            driver.findElement(passwordTextBox).sendKeys(password);
            ReportManager.logStep("Enter Password", password, Status.PASS, driver);
        } catch (Exception e) {
            ReportManager.logStep("Enter Password", "Unable to enter password: " + e.getMessage(), Status.FAIL, driver);
            throw e;
        }
    }

    public void clickOnLogin() {
        try {
            driver.findElement(loginButton).click();
            ReportManager.logStep("Click Login", null, Status.PASS, driver);
        } catch (Exception e) {
            ReportManager.logStep("Click Login", "Unable to click login button: " + e.getMessage(), Status.FAIL, driver);
            throw e; // couldn't even click — technical failure
        }
    }

    /**
     * Checks the RESULT of the login attempt after clicking — this is business outcome,
     * not a technical step, so it never throws and never logs PASS/FAIL itself.
     * The test method compares this against expected data and decides PASS/FAIL.
     */
    public String getLoginResult() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Success
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeading));
            return "SUCCESS";
        } catch (Exception ignored) {
            // fall through
        }

        // 2. Wrong credentials — banner alert
        try {
            WebElement alert = driver.findElement(errorMessage);
            if (alert.isDisplayed()) {
                return alert.getText().trim();
            }
        } catch (Exception ignored) {
            // fall through to field-level checks
        }

        // 3. Blank field(s) — check each locator individually, label based on WHICH one matched
        boolean usernameBlank = !driver.findElements(usernameRequiredError).isEmpty();
        boolean passwordBlank = !driver.findElements(passwordRequiredError).isEmpty();

        if (usernameBlank && passwordBlank) {
            return "Username Required | Password Required";
        } else if (usernameBlank) {
            return "Username Required";
        } else if (passwordBlank) {
            return "Password Required";
        }

        return "UNKNOWN_STATE";
    }
}