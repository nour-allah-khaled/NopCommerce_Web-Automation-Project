package com.nopcommerce.listeners;

import com.nopcommerce.assertion.SoftAssertion;
import com.nopcommerce.datareader.PropertyReader;
import com.nopcommerce.drivers.WebDriverProvider;
import com.nopcommerce.media.ScreenShotMedia;
import com.nopcommerce.report.AllureAttachmentManager;
import com.nopcommerce.report.AllureConstants;
import com.nopcommerce.report.AllureEnviromentManager;
import com.nopcommerce.report.AllureReportGenerator;
import com.nopcommerce.utils.FileUtils;
import com.nopcommerce.utils.logs.LogsManager;

import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.asserts.SoftAssert;

import java.io.File;

import static java.sql.DriverManager.getDriver;

public class Listeners implements IExecutionListener, IInvokedMethodListener, ITestListener {
    public void onExecutionStart() {
        LogsManager.info("Starting execution");
        cleanTestoutput();
        LogsManager.info("Deleted old test output");
        createTestoutput();
        LogsManager.info("Created new test output");
        PropertyReader.loadProperties();
        LogsManager.info("Loaded properties");
        AllureEnviromentManager.setEnvironmentVariables();
        LogsManager.info("Loaded environment variables");
    }

    public void onExecutionFinish() {
        AllureReportGenerator.copyHistory();
        AllureReportGenerator.generateReports(false);
        AllureReportGenerator.generateReports(true);
        AllureReportGenerator.openReport(AllureReportGenerator.renameReport());
        LogsManager.info("Test Execution Finished");
    }
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        WebDriver driver = null;
        if (method.isTestMethod()) {
            if (testResult.getInstance() instanceof WebDriverProvider provider) {
                driver = provider.getWebDriver();
                SoftAssertion.assertAll();
                switch (testResult.getStatus()) {
                    case ITestResult.SUCCESS -> LogsManager.info("Test " + testResult.getName() + " passed.");
                    case ITestResult.FAILURE ->
                            ScreenShotMedia.takeFullPageScreenshot(driver, "failed-" + testResult.getName());
                    case ITestResult.SKIP -> LogsManager.warn("Test " + testResult.getName() + " skipped.");
                }
                AllureAttachmentManager.attachLogs();
            }
        }
    }
    public void onTestStart(ITestResult result) {
    }

    public void onTestSuccess(ITestResult result) {
        LogsManager.info("Test Success" + result.getName() + " passed.");
    }

    public void onTestFailure(ITestResult result) {


    }

    public void onTestSkipped(ITestResult result) {
    }
    public void cleanTestoutput(){
        FileUtils.cleanDirectory(AllureConstants.RESULTS_FOLDER.toFile());
        FileUtils.cleanDirectory(new File(ScreenShotMedia.SCREENSHOT_PATH));
        FileUtils.cleanDirectory(new File(LogsManager.LOGS_PATH));
        LogsManager.info("Cleaned test output directories");
    }
    public void createTestoutput(){
        FileUtils.createDirectory(ScreenShotMedia.SCREENSHOT_PATH);
        LogsManager.info("Created test output directories");
    }

}
