package com.nopcommerce.drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxFactory extends AbstractDriver {
   private FirefoxOptions getFirefoxOptions() {
       FirefoxOptions firefoxOptions = new FirefoxOptions();
       // Add Firefox-specific options here
       firefoxOptions.addArguments("--start-maximized");
       firefoxOptions.addArguments("--disable-notifications");
       firefoxOptions.addArguments("--disable-infobars");
       firefoxOptions.addArguments("--disable-extensions");
       firefoxOptions.addArguments("--disable-popup-blocking");
       firefoxOptions.addArguments("--disable-gpu");
       firefoxOptions.addArguments("--no-sandbox");
       firefoxOptions.addArguments("--remote-allow-origins=*");
       firefoxOptions.addArguments("--disable-dev-shm-usage");
       firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
       return firefoxOptions;
   }
    @Override
    public WebDriver create() {
        return new FirefoxDriver(getFirefoxOptions());
    }
}
