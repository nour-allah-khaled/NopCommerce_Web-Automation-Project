package com.nopcommerce.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertActions {
    private final WebDriver driver;
    private final WaitUtil wait;
    public AlertActions(WebDriver driver) {
        this.driver = driver;
        wait = new WaitUtil(driver);
    }
    //accept alert
    public void acceptAlert() {
        wait.fluentWait().until(d-> {
            try {
                d.switchTo().alert().accept();
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to accept alert: " + e.getMessage());
                return false;
            }
        });
    }
    //dismiss alert
    public void dismissAlert() {
        wait.fluentWait().until(d-> {
            try {
                d.switchTo().alert().dismiss();
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to dismiss alert: " + e.getMessage());
                return false;
            }
        });
    }
    //get alert text
    public String getAlertText() {
        return wait.fluentWait().until(d-> {
            try {
                String text = d.switchTo().alert().getText();
                return !text.isEmpty() ? text : null;
            } catch (Exception e) {
                LogsManager.error("Failed to get alert text: " + e.getMessage());
                return null;
            }
        });
    }
    //send text to alert
    public void sendTextToAlert(String text) {
        wait.fluentWait().until(d-> {
            try {
                d.switchTo().alert().sendKeys(text);
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to send text to alert: " + e.getMessage());
                return false;
            }
        });
    }
}
