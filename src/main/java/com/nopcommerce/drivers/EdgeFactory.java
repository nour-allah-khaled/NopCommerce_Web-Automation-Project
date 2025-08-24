package com.nopcommerce.drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.HashMap;
import java.util.Map;

public class EdgeFactory extends AbstractDriver {

    private EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        Map<String, Object> edgepref = new HashMap<>();
        edgepref.put("credentials_enable_service", false);
        edgepref.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", edgepref);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        return options;
    }
    @Override
    public WebDriver create() {
        return new EdgeDriver(getEdgeOptions());
    }
}
