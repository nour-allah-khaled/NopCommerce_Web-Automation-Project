package com.nopcommerce.drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ChromeFactory extends AbstractDriver {
    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);

        return chromeOptions;
    }
    @Override
    public WebDriver create() {
        return new ChromeDriver(getChromeOptions());
    }
}
