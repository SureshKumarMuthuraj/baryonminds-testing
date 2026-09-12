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
    private static final Logger logger = LogManager.getLogger(PageObjects.class);


	// Locator definitions

	public WebElement getTextFieldElement(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By.xpath("//android.widget.TextView[@text='" + fieldName
				+ "']//following::android.widget.EditText[1] | " + "//android.widget.TextView[@text='"+ fieldName +"']//parent::android.widget.EditText");

		WebElement element = common.findElement(locator);

		return element;

	}
	
	public WebElement getTextFieldValueElement(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By.xpath("//android.widget.TextView[@text='"+ fieldName +"']//parent::android.widget.EditText");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getButtonElement(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By.xpath("//android.widget.Button[normalize-space(@text)='" + fieldName + "']"
				+ " |  //android.view.View[@clickable='true' and .//android.widget.TextView[@text='" + fieldName
				+ "']] | //android.widget.TextView[@text='" + fieldName
				+ "' and @clickable='true'] | //android.view.View[@clickable='true' and .//android.view.View[@content-desc='"
				+ fieldName + "']] | //android.view.View[@clickable='true' and @content-desc='" + fieldName + "']");

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

	public WebElement getLookupFieldElement(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By.xpath(
				"//android.widget.TextView[@text='" + fieldName + "']//parent::android.view.View | //android.widget.TextView[@text='" + fieldName + "']//following::android.view.View[1]");

		WebElement element = common.findElement(locator);

		return element;

	}
	
	public WebElement getLookupTextFieldElement(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By.xpath(
				"//android.widget.TextView[@text='" + fieldName +"']//following::android.widget.EditText");

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

	public WebElement getProgressBarElement() {

		By locator = By.className("android.widget.ProgressBar");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getDropDownElement(String fieldName) {

		fieldName = fieldName.trim();

		By locator = By.xpath(
				"//android.widget.Spinner//preceding-sibling::android.widget.TextView[@text='" + fieldName + "']//parent::android.widget.EditText");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getDropDownOptionsElement(String fieldName) {

		fieldName = fieldName.trim();
		
		String fieldvalue = DataReader.get(fieldName);

		By locator = By.xpath("//android.widget.ScrollView//android.widget.TextView[@text='" + fieldvalue + "']//parent::android.view.View");

		WebElement element = common.findElement(locator);

		return element;

	}

	// Action methods

	public void setText(String fieldName) throws InterruptedException {
		WebElement element = getTextFieldElement(fieldName);

		common.setText(element, DataReader.get(fieldName));
	}

	public void setTextInLookupField(String fieldName) throws InterruptedException {
		WebElement element = getLookupTextFieldElement(fieldName);
		
		logger.info("class = " + element.getAttribute("className"));
		logger.info("enabled = " + element.isEnabled());
		logger.info("focusable = " + element.getAttribute("focusable"));
		logger.info("focused = " + element.getAttribute("focused"));
		logger.info("clickable = " + element.getAttribute("clickable"));
		logger.info("text = " + element.getText());

		common.setText(element, DataReader.get(fieldName));
	}

	public void setDropDownField(String fieldName) {
		WebElement element = getDropDownElement(fieldName);

		common.clickElement(element);

		WebElement dropDownOptions = getDropDownOptionsElement(fieldName);

		common.clickElement(dropDownOptions);
	}

	public void setCheckbox(String fieldName, String status) throws InterruptedException {
		
		fieldName = fieldName.trim();

		By locator = By.xpath("(//android.widget.CheckBox//following-sibling::android.widget.TextView[@text=\"" + fieldName + "\"]//preceding-sibling::android.widget.CheckBox)[last()]");

		WebElement element = common.findElement(locator);

		common.setCheckbox(element, status);
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

		WebElement element = getLookupFieldElement(fieldName);

		common.clickElement(element);
	}

	public void clickLookupFieldOption(String fieldName) {

		WebElement element = getLookuptFieldOptionElement(fieldName);

		common.clickElement(element);
	}

	public void getFieldValue(String fieldName) {

		WebElement element = getTextFieldValueElement(fieldName);

		DataReader.set(fieldName, common.getFieldValue(element));
		
		logger.info(fieldName + ": " + DataReader.get(fieldName));
	}
	

//	validation methods

	public void isContentDescriptionDisplayedOrNotDisplayed(String fieldName, String status) {
		String fieldValue;
		try {
			fieldValue = DataReader.get(fieldName);
		} catch (Exception e) {

			fieldValue = fieldName;
		}

		WebElement element = null;
		try {
			element = getContentDescriptionElement(fieldValue);
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
		if (elementType.equalsIgnoreCase("Video")) {
			try {
				element = getVideoElement();
				common.waitForElementToDisappear(element);
				status = true;
			} catch (TimeoutException e) {
				status = false;
			}
		}

		if (elementType.equalsIgnoreCase("Progress bar")) {
			try {
				element = getProgressBarElement();
				common.waitForElementToDisappear(element);
				status = true;
			} catch (TimeoutException e) {
				status = false;
			}
		}

		Assert.assertTrue(status, "Element " + element + "has not disappeared after timeout");
	}

	public void verifyAppNotNavigatedToNewPage() {

		String pageSourceAfterPopup = driver.getPageSource();

		Assert.assertEquals(pageSourceBeforePopup, pageSourceAfterPopup,
				"App navigated to a new page after closing the popup");
	}

	public void validateFieldValue(String... params) {

		String fieldName;
		String value = "";

		switch (params.length) {
		case 1:
			fieldName = params[0];
			break;

		case 2:
			fieldName = params[0];
			value = params[1];
			break;

		default:
			throw new IllegalArgumentException("Invalid number of parameters provided.");
		}

		WebElement element;

		try {
			element = getTextFieldElement(fieldName);
		} catch (Exception e) {
			element = getDropDownElement(fieldName);
		}

		String expectedValue = "";

		// Read expected value
		try {
			expectedValue = DataReader.get(fieldName);
		} catch (Exception e) {
			expectedValue = "";
		}

		if (value.equalsIgnoreCase("not empty")) {

			// Verify that some value is populated
			try {
				common.waitForValue(element);
			} catch (Exception e) {
				String actualValue = common.getFieldValue(element);
				Assert.fail("Field value is not as expected. Expected value: " + expectedValue + " Actual value: "
						+ actualValue);
			}

		} else {

			// Verify exact expected value
			try {
				common.waitForExpectedValue(element, expectedValue);
			} catch (Exception e) {
				String actualValue = common.getFieldValue(element);
				Assert.fail("Field value is not as expected. Expected value: " + expectedValue + " Actual value: "
						+ actualValue);
			}

		}
	}

	public void setNewKey(String newKey, String oldKey) {
		DataReader.set(newKey, DataReader.get(oldKey));
	}
}
