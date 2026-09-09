package com.baryonminds.revaliyo.utils;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
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

	public boolean scrollVerticallyUntilVisible(By targetLocator) {

		int maxScrolls = 3;

		for (int i = 0; i < maxScrolls; i++) {

			// Check without calling findElement(), to avoid recursion
			List<WebElement> elements = ldriver.findElements(targetLocator);

			if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
				return true;
			}

			// Find vertical scrollable elements
			List<WebElement> scrollables = ldriver.findElements(By.xpath("//*[@scrollable='true']"));

			if (scrollables.isEmpty()) {
				return false;
			}

			boolean scrolled = false;

			for (WebElement scrollable : scrollables) {

				try {
					Rectangle rect = scrollable.getRect();

					Map<String, Object> args = new HashMap<>();
					args.put("left", rect.getX());
					args.put("top", rect.getY());
					args.put("width", rect.getWidth());
					args.put("height", rect.getHeight());
					args.put("direction", "down");
					args.put("percent", 0.8);

					Boolean canScroll = (Boolean) ((JavascriptExecutor) ldriver).executeScript("mobile: scrollGesture",
							args);

					scrolled = true;

					// Check immediately after scrolling
					elements = ldriver.findElements(targetLocator);

					if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
						return true;
					}

					// If this scrollable cannot scroll further
					if (Boolean.FALSE.equals(canScroll)) {
						break;
					}

				} catch (StaleElementReferenceException e) {
					// UI changed; retry next iteration
				}
			}

			if (!scrolled) {
				return false;
			}
		}

		return false;
	}

	public WebElement findElement(By locator) {

		scrollVerticalUntilVisible(locator);

		// scrollVerticallyUntilVisible(locator);

		wait = new FluentWait<>(ldriver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public void clickElement(WebElement element) {

		wait.until(ExpectedConditions.elementToBeClickable(element));

		element.click();

	}

	public void setText(WebElement element, String value) {

		element.clear();

		element.sendKeys(value);

	}

	public boolean isElementDisplayedOrNotDisplayed(By locator, String status) {

		boolean actualDisplayed;

		try {
			actualDisplayed = !ldriver.findElements(locator).isEmpty()
					&& ldriver.findElements(locator).get(0).isDisplayed();
		} catch (Exception e) {
			actualDisplayed = false;
		}

		return status.equalsIgnoreCase("Displayed") == actualDisplayed;
	}

	public boolean isElementDisplayedOrNotDisplayed(WebElement element, String status) {

		boolean actualDisplayed;
		if (element == null) {
			actualDisplayed = false;
		} else {
			try {
				actualDisplayed = element.isDisplayed();
			} catch (TimeoutException | NoSuchElementException e) {
				actualDisplayed = false;
			}
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

		int maxRounds = 4;

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

	public boolean scrollVerticalUntilVisible(By target) {

		int maxScrolls = 3;

		// Already visible
		List<WebElement> targets = ldriver.findElements(target);

		if (!targets.isEmpty() && targets.get(0).isDisplayed()) {
			return true;
		}

		// ==========================================
		// Scroll DOWN - search for element below
		// ==========================================
		for (int i = 0; i < maxScrolls; i++) {

			List<WebElement> scrollViews = ldriver.findElements(By.className("android.widget.ScrollView"));

			if (scrollViews.isEmpty()) {
				break;
			}

			WebElement scrollView = scrollViews.get(0);

			Rectangle rect = scrollView.getRect();

			int x = rect.getX() + rect.getWidth() / 2;

			int startY = rect.getY() + (int) (rect.getHeight() * 0.80);

			int endY = rect.getY() + (int) (rect.getHeight() * 0.20);

			performSwipe(x, startY, x, endY);

			// Check target
			targets = ldriver.findElements(target);

			if (!targets.isEmpty() && targets.get(0).isDisplayed()) {
				return true;
			}
		}

		// ==========================================
		// Scroll UP - search for element above
		// ==========================================
		for (int i = 0; i < maxScrolls; i++) {

			List<WebElement> scrollViews = ldriver.findElements(By.className("android.widget.ScrollView"));

			if (scrollViews.isEmpty()) {
				break;
			}

			WebElement scrollView = scrollViews.get(0);

			Rectangle rect = scrollView.getRect();

			int x = rect.getX() + rect.getWidth() / 2;

			int startY = rect.getY() + (int) (rect.getHeight() * 0.20);

			int endY = rect.getY() + (int) (rect.getHeight() * 0.80);

			performSwipe(x, startY, x, endY);

			// Check target
			targets = ldriver.findElements(target);

			if (!targets.isEmpty() && targets.get(0).isDisplayed()) {
				return true;
			}
		}

		return false;
	}

	private void performSwipe(int startX, int startY, int endX, int endY) {

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence swipe = new Sequence(finger, 0);

		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));

		swipe.addAction(finger.createPointerDown(0));

		swipe.addAction(finger.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), endX, endY));

		swipe.addAction(finger.createPointerUp(0));

		ldriver.perform(Collections.singletonList(swipe));
	}

	public void clickOutsidePopup(WebElement popup) {

		Rectangle rect = popup.getRect();
		Dimension screen = ldriver.manage().window().getSize();

		int x;
		int y;

		// Tap to the left of the popup if space is available
		if (rect.getX() > 10) {
			x = rect.getX() / 2;
			y = rect.getY() + rect.getHeight() / 2;
		}
		// Otherwise tap to the right
		else if (rect.getX() + rect.getWidth() < screen.getWidth() - 10) {
			x = rect.getX() + rect.getWidth() + (screen.getWidth() - (rect.getX() + rect.getWidth())) / 2;
			y = rect.getY() + rect.getHeight() / 2;
		}
		// Otherwise tap above the popup
		else {
			x = screen.getWidth() / 2;
			y = rect.getY() / 2;
		}

		new PointerInput(PointerInput.Kind.TOUCH, "touch");
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence tap = new Sequence(finger, 1);
		tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		ldriver.perform(Collections.singletonList(tap));

		// Verify popup is closed
//		isElementDisplayedOrNotDisplayed(popup, "Not Displayed");
	}

	public void setCheckbox(WebElement checkboxTextElement, String status) {

		List<WebElement> checkboxes = ldriver.findElements(By.className("android.widget.CheckBox"));

		Rectangle textRect = checkboxTextElement.getRect();

		WebElement checkbox = null;

		for (WebElement cb : checkboxes) {

			Rectangle cbRect = cb.getRect();

			// Checkbox is immediately to the left of the text
			if (Math.abs(cbRect.getY() - textRect.getY()) <= 2 && cbRect.getX() < textRect.getX()) {

				checkbox = cb;
				break;
			}
		}

		if (checkbox == null) {
			throw new NoSuchElementException("Checkbox not found for text element: " + checkboxTextElement);
		}

		boolean shouldBeChecked;

		if (status.equalsIgnoreCase("checked")) {
			shouldBeChecked = true;
		} else if (status.equalsIgnoreCase("unchecked")) {
			shouldBeChecked = false;
		} else {
			throw new IllegalArgumentException(
					"Invalid checkbox status: " + status + ". Expected 'checked' or 'unchecked'.");
		}

		boolean isChecked = Boolean.parseBoolean(checkbox.getAttribute("checked"));

		if (isChecked != shouldBeChecked) {
			checkbox.click();
		}
	}

	public String getAttributeOfElement(WebElement element, String attribute) {
		try {
			return element.getAttribute(attribute);
		} catch (Exception e) {
			return null;
		}
	}

	public void waitForExpectedValue(WebElement element, String value) {

		wait.until(new Function<AppiumDriver, Boolean>() {

			@Override
			public Boolean apply(AppiumDriver driver) {

				String currentValue = getAttributeOfElement(element, "value");
				String currentText = getAttributeOfElement(element, "text");

				System.out.println("Current Value: " + currentValue);
				System.out.println("Current Text: " + currentText);
				System.out.println("Expected Value: " + value);

				return value.equals(currentValue) || value.equals(currentText);
			}
		});
	}
}
