package com.hrmcollab.base;

import com.hrmcollab.driver.DriverFactory;
import com.hrmcollab.utils.ConfigReader;

import static com.hrmcollab.driver.DriverFactory.driver;

public class BasePage {

    public void setUp()
    {
        DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("url"));
    }


}
