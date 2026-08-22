package com.hrmcollab.tests;

import com.hrmcollab.base.BasePage;
import com.hrmcollab.driver.DriverFactory;
import com.hrmcollab.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest  {
    BasePage basePage;
    LoginPage loginPage;
    @Test
    public void login() throws InterruptedException {
       basePage = new BasePage();
       basePage.setUp();
       System.out.println("Application Launched Successfully");
       Thread.sleep(5000);
       loginPage=new LoginPage();
       loginPage.enterUserName();
       loginPage.enterPassword();
       loginPage.clickOnLogin();
       Thread.sleep(5000);
       System.out.println("Logged in Successfully");
       DriverFactory.quitDriver();
    }
}
