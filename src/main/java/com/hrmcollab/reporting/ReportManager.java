package com.hrmcollab.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.hrmcollab.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;

public class ReportManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> moduleNode = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> testCaseNode = new ThreadLocal<>();
    private static final ThreadLocal<String> currentTestCaseId = new ThreadLocal<>();

    public static synchronized ExtentReports getInstance()
    {
        if(extent==null)
        {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/HRM_Report.html");
            spark.config().setDocumentTitle("HRM Suite Execution Report");
            spark.config().setReportName("HRM Automation");
            extent=new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

    public static void startModule(String moduleName)
    {
        ExtentTest node = getInstance().createTest(moduleName);
        moduleNode.set(node);
    }

    public static void startTestCase(String tcId, String tcName)
    {
        if(moduleNode.get()==null)
        {
            throw new IllegalStateException("startModule () must be called before start testCase()");
        }

        ExtentTest node = moduleNode.get().createNode(tcId+ " "+ tcName);
        testCaseNode.set(node);
        currentTestCaseId.set(tcId);
    }

    public static void logStep(String action, String inputData, Status status, WebDriver driver)
    {
       if(testCaseNode.get()==null)
       {
           throw new IllegalStateException("startTestCase() must be called before logStep()");
       }

       String details = "Input: " + (inputData==null?"N/A":inputData);
       String screenshotPath = ScreenshotUtil.capture(driver,currentTestCaseId.get(),action);

       try
       {
           if(screenshotPath!=null)
           {
               String reportRelativePath = screenshotPath.replace("reports/", "").replace("\\", "/");
               testCaseNode.get().log(status,action+" | " +details,
                       MediaEntityBuilder.createScreenCaptureFromPath(reportRelativePath).build());
           }
           else
           {
               testCaseNode.get().log(status,action+" | " +details);
           }
       } catch (Exception e) {
           testCaseNode.get().log(status,action+" | " +details + " screenshot attached failed");
       }
    }

    public static void flush()
    {
        if(extent!=null)extent.flush();
    }
}
