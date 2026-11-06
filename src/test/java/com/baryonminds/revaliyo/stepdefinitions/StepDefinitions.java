package com.baryonminds.revaliyo.stepdefinitions;

import java.net.MalformedURLException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.baryonminds.revaliyo.pages.PageObjects;
import com.baryonminds.revaliyo.utils.Common;
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
	private Common common;
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
		common = new Common(driver);
		
		driver.executeScript("mobile: shell", Map.of(
		        "command", "settings",
		        "args", List.of(
		            "put", "global",
		            "policy_control",
		            "immersive.navigation=*"
		        )
		    ));

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

	@Given("user enters value in {string} text field")
	public void user_enters_value_in_text_field(String string) throws InterruptedException {
		pageObjects.setText(string);
	}

	@Given("user enters value in {string} lookup field")
	public void user_enters_value_in_lookup_field(String string) throws InterruptedException {
		pageObjects.setTextInLookupField(string);
	}

	@Given("user clicks on {string} button")
	public void user_clicks_on_button(String string) throws InterruptedException {
		pageObjects.clickButton(string);
	}

	@Given("user clicks on {string} icon")
	public void user_clicks_on_icon(String string) {
		pageObjects.clickIcon(string);
	}

	@Given("user clicks on {string} lookup field")
	public void user_clicks_on_lookup_field(String string) {
		pageObjects.clickLookupField(string);
	}

	@Given("user clicks on {string} content description")
	public void user_clicks_on_content_description(String string) {
		pageObjects.clickContentDescription(string);
	}

	@Then("user validates that content description {string} is {string}")
	public void validateContentDescription(String contentDescription, String expectedState) {
		pageObjects.isContentDescriptionDisplayedOrNotDisplayed(contentDescription, expectedState);
	}

	@Then("user validates that button {string} is {string}")
	public void validateButton(String button, String expectedState) {
		pageObjects.isButtonDisplayedOrNotDisplayed(button, expectedState);
	}

	@Then("user validates that state of button {string} is {string}")
	public void validateButtonStateOfButtonIs(String button, String expectedState) {
		pageObjects.isButtonEnabledOrDisabled(button, expectedState);
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
	public void setcheckbox(String textField, String expectedState) throws InterruptedException {
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

	@Then("user gets the {string} from the email {string}")
	public void user_gets_the_from_the_email(String string, String string2) throws Exception {
		System.out.println("DataReader.get(string2): " + DataReader.get(string2));

		otp = GmailService.getOTP(DataReader.get("Email")); 
		DataReader.set(string, otp);
	}

	@Then("user validates the value of {string}")
	public void validateFieldValue(String fieldName) {
		pageObjects.validateFieldValue(fieldName);
	}

	@Given("user get the existing value for {string} from {string}")
	public void user_get_the_existing_value_for_from(String arg1, String arg2) throws Throwable {
		pageObjects.setNewKey(arg1, arg2);
	}

	@Then("user validates {string} field value")
	public void user_validates_field_value(String fieldName) {
		pageObjects.validateFieldValue(fieldName);

	}

	@Then("user validates {string} field value is {string}")
	public void user_validates_field_value_is(String fieldName, String value) {
		pageObjects.validateFieldValue(fieldName, value);
	}

	@Then("user validates {string} dropdown field value is {string}")
	public void user_validates_dropdown_field_value_is(String fieldName, String value) {
		pageObjects.validateDropDownFieldValue(fieldName, value);
	}

	@Then("user selects option in {string} lookup field")
	public void user_selects_option_in_lookup_field(String fieldName) {
		pageObjects.clickLookupFieldOption(fieldName);

	}

	@Then("user waits till {string} element is disappeared")
	public void user_waits_till_element_is_disappeared(String string) {
		pageObjects.waitTillElementDisappears(string);
	}

	@Then("user validates that video is {string}")
	public void user_validates_that_video_is(String string) {
		pageObjects.isVideoDisplayedOrNotDisplayed(string);
	}

	@Then("user gets {string} text field value")
	public void user_gets_text_field_value(String string) {
		pageObjects.getTextFieldValue(string);
	}

	@Then("user gets {string} field value")
	public void user_gets_field_value(String string) {
		pageObjects.getFieldValue(string);
	}

	@Then("user selects value in {string} dropdown field")
	public void user_selects_value_in_dropdown_field(String string) {
		pageObjects.setDropDownField(string);
	}

	@Then("user validates {string} field value contains {string}")
	public void user_validates_field_value_contains(String string, String string2) {
		pageObjects.validateFieldValueContains(string, string2);
	}

	@Given("^User generate a unique value for \"([^\"]*)\" with prefix \"([^\"]*)\"$")
	public void user_generate_the_value_for(String arg1, String arg2) throws Throwable {
		DataReader.set(arg1, common.generateUniqueValue(arg2));
	}

	@Given("user gets listing {string} name as {string}")
	public void user_gets_listing_name_as(String arg1, String arg2) {
		String value = pageObjects.getProductListingName(arg1);

		DataReader.set(arg2, value);
	}

	@Given("user selects {string} stars for question {string}")
	public void selectStarsForQuestion(String stars, String question) {
		pageObjects.selectStars(question, Integer.parseInt(stars));
	}

	@Then("^User clicks on \"([^\"]*)\" button as optional$")
	public void user_clicks_button_as_optional(String arg1) throws Throwable {
		pageObjects.clickButtonOptional(arg1);
	}

	@Then("user clicks on {string} button for product with {string}")
	public void user_clicks_on_button_for_product_with(String string, String string2) {
		pageObjects.clickButtonBasedOnProduct(string, string2);
	}

	@Then("user validates the status is {string} for product with {string}")
	public void user_validates_the_status_is_for_product_with(String string, String string2) {
		pageObjects.validateStatusOfListing(string, string2);
	}

	@Given("user pushes the image {string} to the device")
	public void user_pushes_the_image_to_the_device(String string) throws Exception {
		common.pushImageToDevice(string);
	}

	@Given("user takes screenshot of the content description element {string} as {string}")
	public void user_takes_screenshot_of_the_content_description_element(String string, String string2)
			throws Exception {
		pageObjects.takeScreenshotOfTheContentDescriptionElement(string, string2);
	}

	@Then("User validates that the images {string} and {string} are {string}")
	public void user_validates_that_the_images_and_are(String string, String string2, String string3) throws Exception {
		pageObjects.validateImages(string, string2, string3);
	}

	@Then("^wait \"([^\"]*)\" seconds$")
	public void wait_Seconds(String arg) throws Throwable {

		common.WaitTime(arg);
	}
	
	@Then("^User merge the \"([^\"]*)\" as \"([^\"]*)\"$")
	public void user_merge_the_as(String arg1, String arg2) throws Throwable {
		common.concatValues(arg1, arg2);
	}
	
	@Given("user clicks on {string} icon of {string} field")
	public void user_clicks_on_icon_of_field(String string, String string2) {
	    pageObjects.clickIconOfField(string, string2);
	}
}
