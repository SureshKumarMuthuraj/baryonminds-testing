package com.baryonminds.revaliyo.stepdefinitions;

import java.net.MalformedURLException;
import java.time.Instant;
import java.util.HashMap;

import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.baryonminds.revaliyo.pages.PageObjects;
import com.baryonminds.revaliyo.utils.DataReader;
import com.baryonminds.revaliyo.utils.DriverManager;
import com.baryonminds.revaliyo.utils.GmailService;
import com.fasterxml.jackson.databind.JsonNode;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class StepDefinitions {

	private PageObjects pageObjects;
	private JsonNode testData;
	AppiumDriver driver;
	private String email;
	private String otp;
	private Instant otpRequestedTime;

	@Before
	public void setUp() throws MalformedURLException {
		System.out.println("========== STARTING DRIVER ==========");
		DriverManager.startDriver();
		System.out.println("========== DRIVER STARTED ==========");
		driver = DriverManager.getDriver();
		pageObjects = new PageObjects();

	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {

			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

			scenario.attach(screenshot, "image/png", scenario.getName());
		}
		// DriverManager.quitDriver();
	}

	@Given("user load test data from {string}")
	public void loadTestData(String jsonFile) throws Exception {
		testData = DataReader.getTestData(jsonFile);
	}

	@Given("user enters value {string}")
	public void user_enters_value(String string) throws InterruptedException {
		pageObjects.setText(string);
	}

	@Given("user clicks on {string} button")
	public void user_clicks_on_button(String string) {
		pageObjects.clickButton(string);
	}

	@Given("user clicks on {string} icon")
	public void user_clicks_on_icon(String string) {
		pageObjects.clickIcon(string);
	}

	@Then("user validates that content description {string} is {string}")
	public void validateContentDescription(String contentDescription, String expectedState) {
		pageObjects.isContentDescriptionDisplayedOrNotDisplayed(contentDescription, expectedState);
	}

	@Then("user validates that button {string} is {string}")
	public void validateButton(String button, String expectedState) {
		pageObjects.isButtonDisplayedOrNotDisplayed(button, expectedState);
	}

	@Then("user validates that text {string} is {string}")
	public void validateTextDisplayed(String text, String expectedState) {
		pageObjects.isTextDisplayedOrNotDisplayed(text, expectedState);
	}

	@Then("user validates that text field {string} is {string}")
	public void validateTextFieldDisplayed(String textField, String expectedState) {
		pageObjects.isTextFieldDisplayedOrNotDisplayed(textField, expectedState);
	}

	@Then("user validates that icon {string} is {string}")
	public void validateIconFieldDisplayed(String textField, String expectedState) {
		pageObjects.isIconDisplayedOrNotDisplayed(textField, expectedState);
	}

	@Then("user validates that popup {string} is {string}")
	public void validatePopUpFieldDisplayed(String textField, String expectedState) {
		pageObjects.isPopUpDisplayedOrNotDisplayed(textField, expectedState);
	}

	@Then("user sets the checkbox {string} to {string}")
	public void setcheckbox(String textField, String expectedState) {
		pageObjects.setCheckbox(textField, expectedState);
	}

	@Then("user validates that checkbox {string} is {string}")
	public void validateCheckboxFieldDisplayed(String textField, String expectedState) {
		pageObjects.isCheckboxDisplayedOrNotDisplayed(textField, expectedState);
	}

	@Then("user taps outside the {string} popup")
	public void the_user_taps_outside_the_popup(String string) {
		pageObjects.closeAccountRequiredPopup(string);
	}

	@Then("user validates the app not navigated to a new page")
	public void theAppShouldNotNavigateToANewPage() {
		pageObjects.verifyAppNotNavigatedToNewPage();
	}

	@Then("user gets the {string} from the email")
	public void user_gets_the_from_the_email(String string) throws Exception {
		otp = GmailService.getOTP(DataReader.get("Email"));
	}

}
