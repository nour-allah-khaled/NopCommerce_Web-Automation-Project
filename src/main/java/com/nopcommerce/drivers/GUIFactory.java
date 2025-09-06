package com.nopcommerce.drivers;

import com.nopcommerce.assertion.HardAssertion;
import com.nopcommerce.assertion.SoftAssertion;
import com.nopcommerce.datareader.PropertyReader;
import com.nopcommerce.utils.action.AlertActions;
import com.nopcommerce.utils.action.BrowserAction;
import com.nopcommerce.utils.action.ElementAction;
import com.nopcommerce.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class GUIFactory {
    private final  String browser = PropertyReader.getProperty("browser");

    private ThreadLocal<WebDriver> ThreadLocalWebdriver = new ThreadLocal<>();

    public GUIFactory(String browser) {
      Browser browserEnum = Browser.valueOf(browser.toUpperCase());
      LogsManager.info("Starting driver for browser: " + browserEnum);
      AbstractDriver driverFactory = browserEnum.getDriverFactory();
      WebDriver driver = ThreadGuard.protect(driverFactory.create());
      ThreadLocalWebdriver.set(driver);
  }
  public ElementAction getElementAction() {
      return new ElementAction(getDriver());
  }
  public BrowserAction getBrowserAction() {
      return new BrowserAction(getDriver());
  }
  public AlertActions getAlertAction() {
      return new AlertActions(getDriver());
  }
  public HardAssertion getHardAssertion() {
      return new HardAssertion(getDriver());
  }
  public SoftAssertion getSoftAssertion() {
      return new SoftAssertion(getDriver());
  }
    public WebDriver getDriver() {
      return ThreadLocalWebdriver.get();
    }
    public void quitDriver() {
        ThreadLocalWebdriver.get().quit();
    }
}
