package com.baryonminds.revaliyo.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.baryonminds.revaliyo.utils.Common;
import com.baryonminds.revaliyo.utils.DataReader;
import com.baryonminds.revaliyo.utils.DriverManager;
import com.fasterxml.jackson.databind.JsonNode;

import io.appium.java_client.android.AndroidDriver;

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
		common.setText(locator, DataReader.get(fieldName));
	}

	public void clickButton(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By.xpath("//android.widget.Button[normalize-space(@text)='" + fieldName + "']"
				+ " | //android.widget.TextView[normalize-space(@text)='" + fieldName + "']");

		common.clickElement(locator);
	}
	
	public void isContentDescriptionDisplayed(String fieldName, String status) {

		By locator = By.xpath("//android.view.View[@content-desc='"+fieldName+"']");

		common.isElementDisplayed(locator, status);
	}
}
