package com.hrmcollab.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ScreenshotUtil {

    private static final Map<String, AtomicInteger> stepCounters = new ConcurrentHashMap<>();

    public static String capture(WebDriver driver,String testCaseId, String stepName)
    {
        try
        {
            int stepNum = stepCounters.
                    computeIfAbsent(testCaseId,
                            k->new AtomicInteger(0)).incrementAndGet();
            String folder = "reports/screenshots/"+ testCaseId +"/";
            new File(folder).mkdirs();

            String fullPath = folder + "step" + stepNum + ".png";
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src,new File(fullPath));
            return fullPath;
        }catch(Exception e)
        {
            System.err.println("Screenshot failed for "+ stepName+e.getMessage());
            return null;
        }
    }
}
