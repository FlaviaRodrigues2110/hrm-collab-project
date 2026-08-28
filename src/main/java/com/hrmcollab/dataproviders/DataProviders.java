package com.hrmcollab.dataproviders;

import com.hrmcollab.models.LoginData;
import com.hrmcollab.utils.TestDataReader;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "loginData")
    public static Object[][] loginData() {
        return TestDataReader.readAsDataProvider("testdata/login.json", LoginData[].class);
    }
}
