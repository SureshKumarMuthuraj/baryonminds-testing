package com.baryonminds.revaliyo.stepdefinitions;

import java.net.MalformedURLException;
import java.util.HashMap;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.baryonminds.revaliyo.pages.PageObjects;
import com.baryonminds.revaliyo.utils.DataReader;
import com.baryonminds.revaliyo.utils.DriverManager;
import com.fasterxml.jackson.databind.JsonNode;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class StepDefinitions {

	private PageObjects pageObjects;
	private JsonNode testData;
	AndroidDriver driver = DriverManager.getDriver();

	@Before
	public void setUp() throws MalformedURLException {
		System.out.println("========== STARTING DRIVER ==========");
		DriverManager.startDriver();
		System.out.println("========== DRIVER STARTED ==========");
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

	@Then("user validates that content description {string} is {string}")
	public void validateContentDescription(String contentDescription, String expectedState) {
		pageObjects.isContentDescriptionDisplayed(contentDescription, expectedState);
	}

}
