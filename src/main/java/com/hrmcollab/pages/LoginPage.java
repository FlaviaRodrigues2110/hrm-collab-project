package com.hrmcollab.pages;
import org.openqa.selenium.By;

import static com.hrmcollab.driver.DriverFactory.driver;

public class LoginPage {

    private By usernameTextBox = By.name("username");
    private By passwordTextBox = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    public void enterUserName()
    {
        driver.findElement(usernameTextBox).sendKeys("Admin");
    }

    public void enterPassword()
    {
        driver.findElement(passwordTextBox).sendKeys("admin123");
    }

    public void clickOnLogin()
    {
        driver.findElement(loginButton).click();
    }
}
