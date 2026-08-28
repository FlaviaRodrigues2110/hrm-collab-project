package com.hrmcollab.tests;

import com.hrmcollab.base.BasePage;
import com.hrmcollab.driver.DriverFactory;
import com.hrmcollab.pages.LoginPage;
import com.hrmcollab.reporting.ExtentReportListener;
import com.hrmcollab.reporting.ReportManager;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentReportListener.class)
public class LoginTest  {
    BasePage basePage;
    LoginPage loginPage;

    @Test
    public void login() throws InterruptedException {

        ReportManager.startModule("Login Scenarios");
        ReportManager.startTestCase("TC001", "Valid Login");
        basePage = new BasePage();
        basePage.setUp();
        System.out.println("Application Launched Successfully");
        loginPage = new LoginPage();
        loginPage.enterUserName();
        loginPage.enterPassword();
        loginPage.clickOnLogin();

        System.out.println("Logged in Successfully");

    }
}
