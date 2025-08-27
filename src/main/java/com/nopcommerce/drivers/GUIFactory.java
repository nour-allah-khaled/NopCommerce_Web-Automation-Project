package com.nopcommerce.drivers;

import groovy.beans.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class GUIFactory {
    private final  String browser = PropertyReader.getProperty("browser");
    private ThreadLocal<WebDriver> ThreadLocalWebdriver = new ThreadLocal<>();
  public GUIFactory(String browser) {
      Browser browserEnum = Browser.valueOf(browser.toUpperCase());
      AbstractDriver driverFactory = browserEnum.getDriverFactory();
      WebDriver driver = ThreadGuard.protect(driverFactory.create());
      ThreadLocalWebdriver.set(driver);
  }

    public WebDriver getDriver(String driver) {
      return ThreadLocalWebdriver.get();
    }
    public void quitDriver() {
        ThreadLocalWebdriver.get().quit();
    }
}
