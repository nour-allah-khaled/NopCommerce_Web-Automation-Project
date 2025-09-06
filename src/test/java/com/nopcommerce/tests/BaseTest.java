package com.nopcommerce.tests;

import com.nopcommerce.drivers.GUIFactory;
import com.nopcommerce.drivers.WebDriverProvider;
import org.openqa.selenium.WebDriver;

public class BaseTest implements WebDriverProvider {
    protected GUIFactory driver;
    @Override
    public WebDriver getWebDriver() {
        return driver.getDriver();
    }
}
