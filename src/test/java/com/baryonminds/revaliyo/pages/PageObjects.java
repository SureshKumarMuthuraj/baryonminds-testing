package com.baryonminds.revaliyo.pages;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TimeoutException;
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

	private String pageSourceBeforePopup;
	private static final Logger logger = LogManager.getLogger();

	// Locator definitions

	public WebElement getTextFieldElement(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By.xpath(
				"//android.widget.TextView[@text='" + fieldName + "']//following-sibling::android.widget.EditText[1]");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getButtonElement(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By.xpath("//android.widget.Button[normalize-space(@text)='" + fieldName + "']"
				+ " |  //android.view.View[@clickable='true']/android.widget.TextView[@text='" + fieldName
				+ "'] | //android.widget.TextView[@text='"+ fieldName +"' and @clickable='true']");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getContentDescriptionElement(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By.xpath("//android.view.View[@content-desc='" + fieldName + "']");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getTextElement(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By
				.xpath("//android.widget.TextView[normalize-space(@text)='" + fieldName + "' and @clickable='false']");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getPopUpElement(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By
				.xpath("//android.widget.TextView[@text='" + fieldName + "']//ancestor::*[@pane-title='Dialog']");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getIconElement(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By.xpath("//android.view.View[@content-desc='" + fieldName + "']");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getCheckboxElement(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By.xpath(
				"//android.widget.TextView[@text=\"" + fieldName + "\"]");

		WebElement element = common.findElement(locator);

		return element;

	}

	// Action methods

	public void setText(String fieldName) throws InterruptedException {
		WebElement element = getTextFieldElement(fieldName);

		common.setText(element, DataReader.get(fieldName));
	}

	public void setCheckbox(String text, String status) {

	    WebElement textElement = getCheckboxElement(text);

	    common.setCheckbox(textElement, status);
	}

	public void clickButton(String fieldName) {

		pageSourceBeforePopup = driver.getPageSource();

		WebElement element = getButtonElement(fieldName);

		common.clickElement(element);
	}

	public void clickIcon(String fieldName) {

		WebElement element = getIconElement(fieldName);

		common.clickElement(element);
	}

	public void isContentDescriptionDisplayedOrNotDisplayed(String fieldName, String status) {

		WebElement element = null;
		try {
			element = getContentDescriptionElement(fieldName);
		} catch (Exception e) {
		}
		Assert.assertTrue(common.isElementDisplayedOrNotDisplayed(element, status),
				"Expected Content Description element '" + fieldName + "' to be " + status);
	}

	public void isButtonDisplayedOrNotDisplayed(String fieldName, String status) {
		WebElement element = null;
		try {
			element = getButtonElement(fieldName);
		} catch (Exception e) {
		}
		Assert.assertTrue(common.isElementDisplayedOrNotDisplayed(element, status),
				"Expected button element '" + fieldName + "' to be " + status);
	}

	public void isTextDisplayedOrNotDisplayed(String fieldName, String status) {

		WebElement element = null;
		try {
			element = getTextElement(fieldName);
		} catch (Exception e) {
		}
		Assert.assertTrue(common.isElementDisplayedOrNotDisplayed(element, status),
				"Expected text element '" + fieldName + "' to be " + status);
	}

	public void isTextFieldDisplayedOrNotDisplayed(String fieldName, String status) {

		WebElement element = null;
		try {
			element = getTextFieldElement(fieldName);
		} catch (Exception e) {
		}
		Assert.assertTrue(common.isElementDisplayedOrNotDisplayed(element, status),
				"Expected text field element '" + fieldName + "' to be " + status);
	}

	public void isIconDisplayedOrNotDisplayed(String fieldName, String status) {

		WebElement element = null;
		try {
			element = getIconElement(fieldName);
		} catch (Exception e) {
		}
		Assert.assertTrue(common.isElementDisplayedOrNotDisplayed(element, status),
				"Expected icon element '" + fieldName + "' to be " + status);
	}

	public void isPopUpDisplayedOrNotDisplayed(String fieldName, String status) {

		WebElement element = null;
		try {
			element = getPopUpElement(fieldName);
		} catch (Exception e) {
		}
		Assert.assertTrue(common.isElementDisplayedOrNotDisplayed(element, status),
				"Expected popup '" + fieldName + "' to be " + status);
	}

	public void isCheckboxDisplayedOrNotDisplayed(String fieldName, String status) {

		WebElement element = null;
		try {
			element = getCheckboxElement(fieldName);
		} catch (Exception e) {
		}
		Assert.assertTrue(common.isElementDisplayedOrNotDisplayed(element, status),
				"Expected checkbox element '" + fieldName + "' to be " + status);
	}

	public void closeAccountRequiredPopup(String popup) {

		WebElement element = getPopUpElement(popup);

		common.clickOutsidePopup(element);
	}

	public void verifyAppNotNavigatedToNewPage() {

		String pageSourceAfterPopup = driver.getPageSource();

		logger.info("pageSourceBeforePopup: " + pageSourceBeforePopup);
		logger.info("pageSourceAfterPopup: " + pageSourceAfterPopup);

		Assert.assertEquals(pageSourceBeforePopup, pageSourceAfterPopup,
				"App navigated to a new page after closing the popup");
	}

}
