package com.nopcommerce.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.ArrayList;

public class WaitUtil {
    private final WebDriver driver;

    public WaitUtil(WebDriver driver) {
        this.driver = driver;
    }
    public FluentWait<WebDriver> fluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoreAll(Exceptionn());
    }
    private ArrayList<Class<? extends Exception>> Exceptionn() {
        ArrayList<Class<? extends Exception>> exceptions = new ArrayList<>();
        exceptions.add(org.openqa.selenium.NoSuchElementException.class);
        exceptions.add(org.openqa.selenium.StaleElementReferenceException.class);
        exceptions.add(org.openqa.selenium.ElementNotInteractableException.class);
        exceptions.add(org.openqa.selenium.ElementClickInterceptedException.class);
        return exceptions;
    }
}
