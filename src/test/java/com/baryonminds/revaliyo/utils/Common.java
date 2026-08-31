package com.baryonminds.revaliyo.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;

public class Common {

	public WebDriver ldriver;

	FluentWait<WebDriver> wait;

	public Common(WebDriver rdriver) {

		ldriver = rdriver;

	}

	public WebElement findElement(By locator) {

		wait = new FluentWait<>(ldriver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void clickElement(By locator) {

		WebElement element = findElement(locator);

		wait.until(ExpectedConditions.elementToBeClickable(element));

		element.click();

	}

	public void setText(By locator, String value) {

		WebElement element = findElement(locator);

		element.clear();
		
		element.sendKeys(value);

	}
	
	public void isElementDisplayed(By locator, String status) {

	    boolean actualDisplayed;

	    try {
	        actualDisplayed = findElement(locator).isDisplayed();

	    } catch (TimeoutException | NoSuchElementException e) {
	        actualDisplayed = false;
	    }

	    if (status.equalsIgnoreCase("Displayed")) {

	        Assert.assertTrue(
	            actualDisplayed,
	            "Element is not displayed: " + locator
	        );

	    } else if (status.equalsIgnoreCase("Not Displayed")) {

	        Assert.assertFalse(
	            actualDisplayed,
	            "Element is displayed: " + locator
	        );

	    } else {

	        throw new IllegalArgumentException(
	            "Invalid status: " + status +
	            ". Expected 'Displayed' or 'Not Displayed'."
	        );
	    }
	}
}
