package com.nopcommerce.media;

import com.nopcommerce.utils.TimeManager;
import com.nopcommerce.utils.logs.LogsManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShotMedia {
    private static final String SCREENSHOT_PATH = "Test_out/ScreenShots/";

    // create method to take full page screenshot
    public static void takeFullPageScreenshot(WebDriver driver, String screenshotName) {
        // code to take full page screenshot
        try {
            File screenshotScr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // save screenshot to a file
            File screensotFile = new File (SCREENSHOT_PATH + screenshotName + "-" + TimeManager.getTimestamp() + ".png");
            FileUtils.copyFile(screenshotScr, screensotFile);
            LogsManager.info("Screenshot taken successfully and saved in : ", screensotFile.getAbsolutePath());
        } catch (Exception e) {
            LogsManager.error("Error taking screenshot: ", e.getMessage());
        }
    }
}
