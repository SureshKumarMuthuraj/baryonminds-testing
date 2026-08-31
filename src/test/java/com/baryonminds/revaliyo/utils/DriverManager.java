package com.baryonminds.revaliyo.utils;

import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class DriverManager {

    private static AndroidDriver driver;

    public static void startDriver() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(
                ConfigReader.get("platformName"));

        options.setAutomationName(
                ConfigReader.get("automationName"));

        options.setDeviceName(
                ConfigReader.get("deviceName"));

        options.setAppPackage(
                ConfigReader.get("appPackage"));

        options.setAppActivity(
                ConfigReader.get("appActivity"));

        options.setNoReset(
                ConfigReader.getBoolean("noReset"));
        
        options.setUiautomator2ServerLaunchTimeout(
                Duration.ofSeconds(Integer.parseInt(ConfigReader.get("uiautomator2ServerLaunchTimeout"))));

options.setUiautomator2ServerInstallTimeout(
                Duration.ofSeconds(Integer.parseInt(ConfigReader.get("uiautomator2ServerInstallTimeout"))));

options.setAdbExecTimeout(
                Duration.ofSeconds(Integer.parseInt(ConfigReader.get("adbExecTimeout"))));
        
        driver = new AndroidDriver(
                URI.create(ConfigReader.get("appiumServerUrl")).toURL(),
                options);
    }

    public static AndroidDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
