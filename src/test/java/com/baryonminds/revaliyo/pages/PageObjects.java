package com.baryonminds.revaliyo.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.baryonminds.revaliyo.utils.Common;
import com.baryonminds.revaliyo.utils.DataReader;
import com.baryonminds.revaliyo.utils.DriverManager;
import com.fasterxml.jackson.databind.JsonNode;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class PageObjects {

	private AndroidDriver driver;
	Common common;

	// Constructor
	public PageObjects() {
		driver = DriverManager.getDriver();
		common = new Common(driver);
	}

	// Actions
	public void setText(String fieldName) throws InterruptedException {
		By locator = By.xpath(
				"//android.widget.TextView[@text='" + fieldName + "']//following-sibling::android.widget.EditText[1]");
		System.out.println("fieldName: " + fieldName);
		System.out.println("DataReader.get(fieldName): " + DataReader.get(fieldName));

		common.setText(locator, DataReader.get(fieldName));	
	}

	public void clickButton(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By.xpath("//android.widget.Button[normalize-space(@text)='" + fieldName + "']"
				+ " | //android.widget.TextView[normalize-space(@text)='" + fieldName + "']");

		common.clickElement(locator); 
	}
	
	public void isContentDescriptionDisplayedOrNotDisplayed(String fieldName, String status) {

	    By locator = By.xpath("//android.view.View[@content-desc='" + fieldName + "']");

	    boolean isDisplayedOrNotDisplayed = common.isElementDisplayedOrNotDisplayed(locator, status);

	    Assert.assertTrue(isDisplayedOrNotDisplayed,"Expected element with content-description '" + fieldName + "' to be " + status);
	}	
}
