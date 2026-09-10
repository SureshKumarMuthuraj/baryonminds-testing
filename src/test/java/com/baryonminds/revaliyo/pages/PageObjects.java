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

		By locator = By.xpath("//android.widget.TextView[@text='" + fieldName
				+ "']//following-sibling::android.widget.EditText[1] | " + "//android.widget.TextView[@text='"
				+ fieldName + "']//following::android.widget.EditText[1]");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getButtonElement(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By.xpath("//android.widget.Button[normalize-space(@text)='" + fieldName + "']"
				+ " |  //android.view.View[@clickable='true' and .//android.widget.TextView[@text='" + fieldName
				+ "']] | //android.widget.TextView[@text='" + fieldName + "' and @clickable='true'] | //android.view.View[@clickable='true' and .//android.view.View[@content-desc='"+fieldName+"']]");

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
				.xpath("//android.widget.TextView[@text='" + fieldName + "']//ancestor::*[@pane-title='Dialogue']");

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

		By locator = By.xpath("//android.widget.TextView[@text=\"" + fieldName + "\"]");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getLookuptFieldElement(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By.xpath(
				"//android.widget.TextView[@text='" + fieldName + "']//following-sibling::android.widget.EditText |"
						+ "//android.widget.TextView[@text='" + fieldName + "']//following::android.view.View");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getLookuptFieldOptionElement(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By.xpath("//android.widget.TextView[@text='" + fieldName
				+ "']//following::android.widget.TextView[1][contains(@text,'" + DataReader.get(fieldName) + "')]");

		WebElement element = common.findElement(locator);

		return element;

	}
	
	public WebElement getVideoElement() {

	    By locator = By.className("android.view.TextureView");

		WebElement element = common.findElement(locator);

		return element;

	}

	// Action methods

	public void setText(String fieldName) throws InterruptedException {
		WebElement element = getTextFieldElement(fieldName);

		common.setText(element, DataReader.get(fieldName));
	}

	public void setTextInLookupField(String fieldName) throws InterruptedException {
		WebElement element = getLookuptFieldElement(fieldName);

		common.setText(element, DataReader.get(fieldName));
	}

	public void setCheckbox(String text, String status) {

		WebElement textElement = getCheckboxElement(text);

		common.setCheckbox(textElement, status);
	}

	public void clickButton(String fieldName) throws InterruptedException {
Thread.sleep(2000);
		pageSourceBeforePopup = driver.getPageSource();

		WebElement element = getButtonElement(fieldName);

		common.clickElement(element);
	}

	public void clickIcon(String fieldName) {

		WebElement element = getIconElement(fieldName);

		common.clickElement(element);
	}

	public void closeAccountRequiredPopup(String popup) {

		WebElement element = getPopUpElement(popup);

		common.clickOutsidePopup(element);
	}

	public void clickLookupField(String fieldName) {

		WebElement element = getLookuptFieldElement(fieldName);

		common.clickElement(element);
	}

	public void clickLookupFieldOption(String fieldName) {

		WebElement element = getLookuptFieldOptionElement(fieldName);

		common.clickElement(element);
	}

//	validation methods

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
	
	public void isVideoDisplayedOrNotDisplayed(String status) {
		WebElement element = null;
		try {
			element = getVideoElement();
		} catch (Exception e) {
		}
		Assert.assertTrue(common.isElementDisplayedOrNotDisplayed(element, status),
				"Expected button element '" + element + "' to be " + status);
	}
	
	public void waitTillElementDisappears(String elementType) {
		WebElement element = null;
		boolean status = false;
		if(elementType.equalsIgnoreCase("Video")) {
		try {
			element = getVideoElement();
			common.waitForElementToDisappear(element);
			status = true;
		} catch (TimeoutException e) {
			status = false;
		}
		}
		
		Assert.assertTrue(status,"Element " + element + "has not disappeared after timeout");
	}

	public void verifyAppNotNavigatedToNewPage() {

		String pageSourceAfterPopup = driver.getPageSource();

		Assert.assertEquals(pageSourceBeforePopup, pageSourceAfterPopup,
				"App navigated to a new page after closing the popup");
	}

	public void validateFieldValue(String fieldName) {

		String expectedValue = DataReader.get(fieldName);
		WebElement element = getTextFieldElement(fieldName);
		try {
			common.waitForExpectedValue(element, DataReader.get(fieldName));
		} catch (Exception e) {
			String actualValue = common.getAttributeOfElement(element, "value");
			Assert.fail(fieldName + " field value is not as expected. Expected value: " + expectedValue
					+ " Actual value: " + actualValue);
		}
	}

	public void setNewKey(String newKey, String oldKey) {
		DataReader.set(newKey, DataReader.get(oldKey));
	}
}
