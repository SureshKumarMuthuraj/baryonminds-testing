package com.baryonminds.revaliyo.pages;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.baryonminds.revaliyo.utils.Common;
import com.baryonminds.revaliyo.utils.DataReader;
import com.baryonminds.revaliyo.utils.DriverManager;
import com.fasterxml.jackson.databind.JsonNode;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Then;
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

		fieldName = fieldName.split("_")[0].trim();

		By locator = By
				.xpath("//android.widget.TextView[@text='" + fieldName + "']//following::android.widget.EditText[1] | "
						+ "//android.widget.TextView[@text='" + fieldName + "']//parent::android.widget.EditText");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getTextFieldElementWithValue(String fieldName) {

		fieldName = fieldName.split("_")[0].trim();

//		By locator = By.xpath("//android.widget.TextView[@text='" + fieldName
//				+ "']//following::android.widget.EditText[1] | //android.widget.TextView[@text='" + fieldName + "']//following-sibling::android.widget.TextView");
//
//		WebElement element = common.findElement(locator);

		List<String> xpathOptions = Arrays.asList(
				"//android.widget.TextView[@text='" + fieldName + "']//following::android.widget.EditText[1]",
				"//android.widget.TextView[@text='" + fieldName + "']//following-sibling::android.widget.TextView");

//		By editTextLocator = By.xpath("//android.widget.TextView[@text='" + fieldName + "']//following::android.widget.EditText[1]");
//
//		By textViewLocator = By.xpath("//android.widget.TextView[@text='" + fieldName + "']//following-sibling::android.widget.TextView");

		String xpath = common.selectLocator(xpathOptions);

		WebElement element = driver.findElement(By.xpath(xpath));

		return element;

	}

	public WebElement getTextFieldValueElement(String fieldName) {

		fieldName = fieldName.split("_")[0].trim();

		By locator = By.xpath("//android.widget.TextView[@text='" + fieldName
				+ "']//parent::android.widget.EditText | //android.widget.TextView[@text='" + fieldName
				+ "']//following::android.widget.EditText[1]");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getFieldValueElement(String fieldName) {

		fieldName = fieldName.split("_")[0].trim();

		By locator = By.xpath(
				"//android.widget.TextView[@text='" + fieldName + "']//following-sibling::android.widget.TextView");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getButtonElement(String fieldName) {

		By locator = By.xpath("//android.widget.Button[normalize-space(@text)='" + fieldName + "']"
				+ " |  //android.view.View[@clickable='true' and .//android.widget.TextView[normalize-space(@text)='"
				+ fieldName + "']] | //android.widget.TextView[@text='" + fieldName
				+ "' and @clickable='true'] | //android.view.View[@clickable='true' and .//android.view.View[@content-desc='"
				+ fieldName + "']] | //android.view.View[@clickable='true' and @content-desc='" + fieldName + "']");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getButtonElementState(String fieldName) {

		fieldName = fieldName.split("_")[0].trim();

		By locator = By.xpath("//android.widget.TextView[normalize-space(@text)='" + fieldName + "']");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getContentDescriptionElement(String fieldName) {
		String finalValue = DataReader.get(fieldName);

		if (finalValue == null) {
			finalValue = fieldName;
		}

		By locator = By
				.xpath("//android.view.View[@content-desc=\"" + finalValue + "\"] | //android.widget.TextView[@text=\""
						+ finalValue + "\"]//parent::android.view.View | //android.view.View[contains(@content-desc,\""
						+ finalValue + "\")]");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getTextElement(String fieldName) {

//		fieldName = fieldName.split("_")[0].trim();

		String finalValue = DataReader.get(fieldName);

		if (finalValue == null) {
			finalValue = fieldName;
		}

		By locator = By.xpath(
				"//android.widget.TextView[normalize-space(@text)=\"" + finalValue + "\" and @clickable='false']");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getPopUpElement(String fieldName) {

		fieldName = fieldName.split("_")[0].trim();

		By locator = By
				.xpath("//android.widget.TextView[@text='" + fieldName + "']//ancestor::*[@pane-title='Dialogue']");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getIconElement(String fieldName) {

		fieldName = fieldName.split("_")[0].trim();

		By locator = By.xpath("//android.view.View[@content-desc='" + fieldName
				+ "'] | //android.widget.ScrollView/android.widget.ImageView[@content-desc='" + fieldName + "']");
		
		if(fieldName.equals("Profile photo")) {
			
			locator = By.xpath("(//android.widget.TextView[@text='My Profile']//following::android.view.View//android.view.View)[1]");
		}

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getIconElement(String iconElement, String fieldName) {

		fieldName = fieldName.split("_")[0].trim();

		By locator = By.xpath("//android.widget.TextView[@text='" + fieldName
				+ "']//following-sibling::android.widget.ImageView[@content-desc='" + iconElement + "'][1]");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getCheckboxElement(String fieldName) {

		fieldName = fieldName.split("_")[0].trim();

		By locator = By.xpath("//android.widget.TextView[@text=\"" + fieldName + "\"]");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getLookupFieldElement(String fieldName) {

		fieldName = fieldName.split("_")[0].trim();

		By locator = By.xpath("//android.widget.TextView[@text='" + fieldName
				+ "']//parent::android.view.View | //android.widget.TextView[@text='" + fieldName
				+ "']//following::android.view.View[1]");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getLookupTextFieldElement(String fieldName) {

		fieldName = fieldName.split("_")[0].trim();

		By locator = By
				.xpath("//android.widget.TextView[@text='" + fieldName + "']//following::android.widget.EditText");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getLookuptFieldOptionElement(String fieldName) {

		String finalValue = DataReader.get(fieldName);

		fieldName = fieldName.split("_")[0].trim();

		By locator = By.xpath("//android.widget.TextView[@text='" + fieldName
				+ "']//following::android.widget.TextView[1][contains(@text,'" + finalValue + "')]");

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

		fieldName = fieldName.split("_")[0].trim();

		By locator = By.xpath("//android.widget.Spinner//preceding-sibling::android.widget.TextView[@text='" + fieldName
				+ "']//parent::android.widget.EditText");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getDropDownOptionsElement(String fieldName) {

		fieldName = fieldName.split("_")[0].trim();

		String fieldvalue = DataReader.get(fieldName);

		By locator = By.xpath("//android.widget.ScrollView//android.widget.TextView[@text='" + fieldvalue
				+ "']//parent::android.view.View");

		WebElement element = common.findElement(locator);

		return element;

	}

	public WebElement getProductListingElement(int index) {

		By locator = By.xpath(
				"(//android.view.View[@clickable='true' and .//android.widget.TextView[@text='User Expected Value']]/android.widget.TextView[1])["
						+ index + "]");

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

		fieldName = fieldName.split("_")[0].trim();

		By locator = By.xpath("(//android.widget.CheckBox//following-sibling::android.widget.TextView[@text=\""
				+ fieldName + "\"]//preceding-sibling::android.widget.CheckBox)[last()]");

		WebElement element = common.findElement(locator);

		common.setCheckbox(element, status);
	}

	public void clickButton(String fieldName) throws InterruptedException {

		Thread.sleep(2000);

		pageSourceBeforePopup = driver.getPageSource();

		WebElement element = getButtonElement(fieldName);

		common.clickElement(element);

	}

	public void clickButtonOptional(String fieldName) throws InterruptedException {
		try {
			WebElement element = getButtonElement(fieldName);
			common.clickElement(element);
		} catch (Exception e) {

		}
	}

	public void clickButtonBasedOnProduct(String fieldName1, String fieldName2) {

		String fieldvalue = DataReader.get(fieldName2);

		By locator = By.xpath("//android.view.View[@content-desc='" + fieldvalue
				+ "']/ancestor::android.view.View[@clickable='true']//following-sibling::android.view.View//android.widget.TextView[@text='"
				+ fieldName1 + "']");

		WebElement element = common.findElement(locator);

		common.clickElement(element);

	}

	public void clickIcon(String fieldName) {

		WebElement element = getIconElement(fieldName);

		common.clickElement(element);
	}

	public void clickIconOfField(String iconElement, String fieldName) {

		WebElement element = getIconElement(iconElement, fieldName);

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

	public void clickContentDescription(String fieldName) {

		WebElement element = getContentDescriptionElement(fieldName);
		try {
			common.clickElement(element);
		} catch (TimeoutException e) {

			Throwable cause = e;

			boolean stale = false;

			while (cause != null) {
				if (cause instanceof StaleElementReferenceException
						|| cause.getClass().getName().contains("StaleObjectException")) {
					stale = true;
					break;
				}
				cause = cause.getCause();
			}

			if (stale) {
				element = getContentDescriptionElement(fieldName);
				common.clickElement(element);
			} else {
				throw e;
			}
		}
	}

	public void clickLookupFieldOption(String fieldName) {

		WebElement element = getLookuptFieldOptionElement(fieldName);

		common.clickElement(element);
	}

	public void getTextFieldValue(String fieldName) {

		WebElement element = getTextFieldValueElement(fieldName);

		DataReader.set(fieldName, common.getFieldValue(element));

		logger.info(fieldName + ": " + DataReader.get(fieldName));
	}

	public void getFieldValue(String fieldName) {

		WebElement element = getFieldValueElement(fieldName);

		DataReader.set(fieldName, common.getFieldValue(element));

		logger.info(fieldName + ": " + DataReader.get(fieldName));
	}

	public String getProductListingName(String index) {

		WebElement element = getProductListingElement(Integer.parseInt(index));

		String value = common.getText(element);

		return value;

	}

	public void waitForFile(Path filePath, int timeoutSeconds) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

		wait.until(driver -> {
			try {
				return Files.exists(filePath) && Files.size(filePath) > 0;
			} catch (IOException e) {
				return false;
			}
		});
	}

	public void takeScreenshotOfTheContentDescriptionElement(String fieldName, String fileName) throws Exception {
		WebElement element = getContentDescriptionElement(fieldName);

		File screenshot = element.getScreenshotAs(OutputType.FILE);

		Path screenshotsDir = Paths.get(System.getProperty("user.dir"), "src", "test", "resources",
				"images_screenshots");

		Files.createDirectories(screenshotsDir);

		Path destination = screenshotsDir.resolve(fileName);

		System.out.println("destination: " + destination);

		common.fileDelete(destination);

		screenshotsDir = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "images_screenshots");

		Files.createDirectories(screenshotsDir);

		destination = screenshotsDir.resolve(fileName);

		Files.copy(screenshot.toPath(), destination);

		waitForFile(destination, 30);

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

	public void isButtonEnabledOrDisabled(String fieldName, String status) {
		WebElement element = null;
		try {
			element = getButtonElementState(fieldName);
		} catch (Exception e) {
		}
		Assert.assertTrue(common.isElementEnabledOrDisabled(element, status),
				"Expected button element '" + fieldName + "' to be " + status);
	}

	public void isTextDisplayedOrNotDisplayed(String fieldName, String status) {

		WebElement element = null;
		try {
			element = getTextElement(fieldName);
		} catch (Exception e) {
			logger.info("Exception: " + e);
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

		if (params == null || params.length < 1 || params.length > 2) {
			throw new IllegalArgumentException(
					"Expected 1 or 2 parameters, but received: " + (params == null ? 0 : params.length));
		}

		String fieldName = params[0];
		String value = params.length == 2 ? params[1] : "";

		WebElement element;

		try {
			element = getTextFieldElementWithValue(fieldName);
		} catch (NoSuchElementException | TimeoutException e) {
			element = getDropDownElement(fieldName);
		}

		// Handle "not empty" separately
		if ("not empty".equalsIgnoreCase(value)) {

			try {
				common.waitForValue(element);
			} catch (Exception e) {

				String actualValue = common.getFieldValue(element);

				Assert.fail("Expected field '" + fieldName + "' to contain a value, but actual value was: '"
						+ actualValue + "'");
			}

			return;
		}

		String expectedValue;

		try {

			if (value.isEmpty()) {
				logger.info(fieldName + ": " + DataReader.get(fieldName));

				expectedValue = DataReader.get(fieldName);
			} else {
				logger.info(fieldName + ": " + DataReader.get(value));
				expectedValue = DataReader.get(value);
			}

		} catch (Exception e) {
			logger.info("Exception: " + e);

			expectedValue = value.isEmpty() ? fieldName : value;
		}

		try {

			common.waitForExpectedValue(element, expectedValue);

		} catch (Exception e) {

			String actualValue = common.getFieldValue(element);

			Assert.fail("Field '" + fieldName + "' value is not as expected. Expected: '" + expectedValue
					+ "' Actual: '" + actualValue + "'");
		}
	}

	public void validateDropDownFieldValue(String... params) {

		if (params == null || params.length < 1 || params.length > 2) {
			throw new IllegalArgumentException(
					"Expected 1 or 2 parameters, but received: " + (params == null ? 0 : params.length));
		}

		String fieldName = params[0];
		String value = params.length == 2 ? params[1] : "";

		WebElement element;

		element = getDropDownElement(fieldName);

		// Handle "not empty" separately
		if ("not empty".equalsIgnoreCase(value)) {

			try {
				common.waitForValue(element);
			} catch (Exception e) {

				String actualValue = common.getFieldValue(element);

				Assert.fail("Expected field '" + fieldName + "' to contain a value, but actual value was: '"
						+ actualValue + "'");
			}

			return;
		}

		String expectedValue;

		try {

			if (value.isEmpty()) {
				logger.info(fieldName + ": " + DataReader.get(fieldName));

				expectedValue = DataReader.get(fieldName);
			} else {
				logger.info(fieldName + ": " + DataReader.get(value));
				expectedValue = DataReader.get(value);
			}

		} catch (Exception e) {
			logger.info("Exception: " + e);

			expectedValue = value.isEmpty() ? fieldName : value;
		}

		try {

			common.waitForExpectedValue(element, expectedValue);

		} catch (Exception e) {

			String actualValue = common.getFieldValue(element);

			Assert.fail("Field '" + fieldName + "' value is not as expected. Expected: '" + expectedValue
					+ "' Actual: '" + actualValue + "'");
		}
	}

	public void validateFieldValueContains(String... params) {

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
			element = getTextFieldElementWithValue(fieldName);
		} catch (Exception e) {
			element = getDropDownElement(fieldName);
		}

		String expectedValue = "";

		// Read expected value
		try {
			expectedValue = DataReader.get(value);
		} catch (Exception e) {
			expectedValue = "";
		}

		String actualValue = null;
		try {
			actualValue = common.getFieldValue(element);

			if (!actualValue.contains(expectedValue)) {

				Assert.fail("Field value is not as expected. Expected value: " + expectedValue + " Actual value: "
						+ actualValue);
			}
		} catch (Exception e) {
			Assert.fail("Field value is not as expected. Expected value: " + expectedValue + " Actual value: "
					+ actualValue);
		}

	}

	public void validateStatusOfListing(String status, String fieldName) {

		String fieldvalue = DataReader.get(fieldName);

		By locator = By.xpath("//android.view.View[@content-desc='" + fieldvalue
				+ "']/../../../following-sibling::android.widget.TextView[1]");

		WebElement element = common.findElement(locator);

		Assert.assertEquals(common.getFieldValue(element), status,
				"Expected status is: " + status + " and Actual status is: " + common.getFieldValue(element));
	}

	public void validateImages(String expectedImage, String actualImage, String expectedResult) throws Exception {

		boolean imagesAreSame = common.compareImages(expectedImage, actualImage);

		boolean expectedSame = expectedResult.equalsIgnoreCase("same");

		Assert.assertEquals(imagesAreSame, expectedSame, "Image comparison result is not as expected");
	}

	public void setNewKey(String newKey, String oldKey) {
		DataReader.set(newKey, DataReader.get(oldKey));
	}

	public void selectStars(String question, int stars) {

		if (stars < 1 || stars > 5) {
			throw new IllegalArgumentException("Stars must be between 1 and 5. Actual: " + stars);
		}

		By locator = By.xpath("//android.widget.TextView[@text=\"" + question + "\"]"
				+ "/following-sibling::android.widget.ImageView[@content-desc=\"" + stars + " stars\"]");

		common.clickElement(common.findElement(locator));
	}
}
