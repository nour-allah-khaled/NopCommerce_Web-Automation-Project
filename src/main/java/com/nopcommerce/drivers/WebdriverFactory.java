package com.nopcommerce.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class WebdriverFactory {
    private static ThreadLocal<WebDriver> ThreadLocalWebdriver = new ThreadLocal<>();
    private AbstractDriver Driver(String browser) {
      return switch (browser) {
            case "chrome" -> new ChromeFactory();
            case "edge" -> new EdgeFactory();
            default -> throw   new IllegalArgumentException("Browser not supported" + browser);
        };
    }
    public static WebDriver getDriver(String driver) {
        WebDriver driver1 = ThreadGuard.protect(ThreadLocalWebdriver.get());
        ThreadLocalWebdriver.set(driver1);
        return ThreadLocalWebdriver.get();
    }
    public static void quitDriver() {
        ThreadLocalWebdriver.get().quit();
    }
}
