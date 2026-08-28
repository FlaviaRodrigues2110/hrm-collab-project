package com.hrmcollab.tests;

import com.aventstack.extentreports.Status;
import com.hrmcollab.base.BasePage;
import com.hrmcollab.dataproviders.DataProviders;
import com.hrmcollab.driver.DriverFactory;
import com.hrmcollab.models.LoginData;
import com.hrmcollab.pages.LoginPage;
import com.hrmcollab.reporting.ExtentReportListener;
import com.hrmcollab.reporting.ReportManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.hrmcollab.driver.DriverFactory.getCurrentDriver;

@Listeners(ExtentReportListener.class)
public class LoginTest {

    @BeforeClass
    public void startModule()
    {
        ReportManager.startModule("Login Scenarios");
    }

    @Test(dataProvider = "loginData", dataProviderClass = DataProviders.class)
    public void login(LoginData data) {

        ReportManager.startTestCase(data.getTestCaseId(), data.getDescription());

        BasePage basePage = new BasePage();
        basePage.setUp();

        LoginPage loginPage = new LoginPage();
        loginPage.enterUserName(data.getUsername());
        loginPage.enterPassword(data.getPassword());
        loginPage.clickOnLogin();

        String actual = loginPage.getLoginResult();

        if (data.expectsSuccess()) {
            boolean passed = actual.equals("SUCCESS");
            ReportManager.logStep("Verify Login Success",
                    "Expected: SUCCESS | Actual: " + actual,
                    passed ? Status.PASS : Status.FAIL, getCurrentDriver());
            Assert.assertEquals(actual, "SUCCESS",
                    "Login was expected to succeed but got: " + actual);
        } else {
            boolean passed = actual.contains(data.getExpectedMessage());
            ReportManager.logStep("Verify Login Failure",
                    "Expected: " + data.getExpectedMessage() + " | Actual: " + actual,
                    passed ? Status.PASS : Status.FAIL, getCurrentDriver());
            Assert.assertTrue(passed,
                    "Expected failure message [" + data.getExpectedMessage() + "] but got [" + actual + "]");
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}