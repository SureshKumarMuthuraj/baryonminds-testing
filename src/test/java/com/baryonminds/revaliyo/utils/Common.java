package com.baryonminds.revaliyo.utils;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Common {

	public AppiumDriver ldriver;

	FluentWait<AppiumDriver> wait;

	public Common(AppiumDriver rdriver) {

		ldriver = rdriver;

	}

	public WebElement findElement(By locator) {

		wait = new FluentWait<>(ldriver).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void clickElement(By locator) {

		scrollAllScrollableUntilVisible(locator);

		WebElement element = findElement(locator);

		wait.until(ExpectedConditions.elementToBeClickable(element));

		element.click();

	}

	public void setText(By locator, String value) {

		WebElement element = findElement(locator);

		element.clear();

		element.sendKeys(value);

	}

	public boolean isElementDisplayedOrNotDisplayed(By locator, String status) {

		boolean actualDisplayed;

		try {
			actualDisplayed = findElement(locator).isDisplayed();
		} catch (TimeoutException | NoSuchElementException e) {
			actualDisplayed = false;
		}

		return status.equalsIgnoreCase("Displayed") == actualDisplayed;
	}

	public void swipeLeft(WebElement element) {

		Rectangle rect = element.getRect();

		int startX = rect.getX() + (int) (rect.getWidth() * 0.85);
		int endX = rect.getX() + (int) (rect.getWidth() * 0.15);
		int y = rect.getY() + rect.getHeight() / 2;

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence swipe = new Sequence(finger, 1);

		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, y));

		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

		swipe.addAction(finger.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), endX, y));

		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		ldriver.perform(Collections.singletonList(swipe));
	}

	public boolean scrollAllScrollableUntilVisible(By target) {

		int maxRounds = 5;

		for (int round = 0; round < maxRounds; round++) {

			try {
				if (isElementDisplayedOrNotDisplayed(target, "Displayed")) {
					return true;
				}
			} catch (Exception e) {

			}

			List<WebElement> scrollables = ldriver.findElements(By.xpath("//*[@scrollable='true']"));

			if (scrollables.isEmpty()) {
				return false;
			}

			for (WebElement scrollable : scrollables) {

				try {
					swipeLeft(scrollable);

					// Check after scrolling
					if (isElementDisplayedOrNotDisplayed(target, "Displayed")) {
						return true;
					}

				} catch (StaleElementReferenceException e) {

				}
			}
		}

		return false;
	}
}
