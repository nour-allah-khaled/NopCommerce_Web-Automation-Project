package com.nopcommerce.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;

public class ActionUtil {
    private final WebDriver driver;
    private WaitUtil wait;

    public ActionUtil(WebDriver driver) {
        this.driver = driver;
        wait = new WaitUtil(driver);
    }
    //clicking
    public void clicking(By locator) {
        wait.fluentWait().until(
                d-> {
                   try {
                       WebElement element = driver.findElement(locator);
                       scrollToElement(locator);
                       element.click();
                       return true;
                   } catch (Exception e) {
                          return false;
                     }
                }
        );
    }
    //typing
    public void sendkeys(By locator, String text) {
        wait.fluentWait().until(
                d-> {
                    try {
                        WebElement element = driver.findElement(locator);
                        scrollToElement(locator);
                        element.clear();
                        element.sendKeys(text);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
    }
    //getting text
    public String getText(By locator) {
        return wait.fluentWait().until(
                d-> {
                    try {
                        WebElement element = driver.findElement(locator);
                        scrollToElement(locator);
                        String text = element.getText();
                        return !text.isEmpty() ? text : null;
                    } catch (Exception e) {
                        return null;
                    }
                }
        );
    }
    //find element
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }
    //scroll to element using js
    public void scrollToElement(By locator){
        ((org.openqa.selenium.JavascriptExecutor) driver).
                executeScript("""
                  arguments[0].scrollIntoView({behavior: "auto", block: "center", inline: "center" });""",findElement(locator));
    }
    //upload file
    public void uploadFile(By locator, String filePath) {
        String fileAbsolutePath = System.getProperty("user.dir") + File.separator +filePath;
        wait.fluentWait().until(
                d-> {
                    try {
                        WebElement element = driver.findElement(locator);
                        scrollToElement(locator);
                        element.sendKeys(fileAbsolutePath);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
    }
}
