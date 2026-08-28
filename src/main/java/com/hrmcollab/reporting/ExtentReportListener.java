package com.hrmcollab.reporting;
import org.testng.ITestContext;
import org.testng.ITestListener;

public class ExtentReportListener implements ITestListener {

    public void onStart(ITestContext context)
    {
        ReportManager.getInstance();
    }

    public void onFinish(ITestContext context)
    {
        ReportManager.flush();
    }

}
