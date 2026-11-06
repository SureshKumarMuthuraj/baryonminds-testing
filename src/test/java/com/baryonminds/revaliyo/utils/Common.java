package com.baryonminds.revaliyo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.NonNull;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.baryonminds.revaliyo.pages.PageObjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import nu.pattern.OpenCV;

public class Common {

	public AppiumDriver ldriver;

	FluentWait<AppiumDriver> wait;

	public Common(AppiumDriver rdriver) {

		ldriver = rdriver;

	}

	static {
		OpenCV.loadLocally();
	}

	private static final Logger logger = LogManager.getLogger(Common.class);

	public WebElement findElement(By locator) {
		try {
			scrollVerticalUntilVisible(locator);
		} catch (StaleElementReferenceException e) {

			scrollVerticalUntilVisible(locator);
		}

		// scrollVerticallyUntilVisible(locator);

		wait = new FluentWait<>(ldriver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	/*
	 * public WebElement findElement(By primaryLocator, By secondaryLocator) {
	 * 
	 * try { scrollVerticalUntilVisible(primaryLocator); } catch (Exception e) { //
	 * Primary not found/visible - don't stop here }
	 * 
	 * try { scrollVerticalUntilVisible(secondaryLocator); } catch (Exception e) {
	 * // Secondary not found/visible - wait will handle it }
	 * 
	 * wait = new FluentWait<>(ldriver) .withTimeout(Duration.ofSeconds(10))
	 * .pollingEvery(Duration.ofSeconds(1)) .ignoring(NoSuchElementException.class)
	 * .ignoring(StaleElementReferenceException.class);
	 * 
	 * return wait.until(driver -> {
	 * 
	 * // Priority 1 List<WebElement> primaryElements =
	 * driver.findElements(primaryLocator);
	 * 
	 * for (WebElement element : primaryElements) { if (element.isDisplayed()) {
	 * return element; } }
	 * 
	 * // Priority 2 List<WebElement> secondaryElements =
	 * driver.findElements(secondaryLocator);
	 * 
	 * for (WebElement element : secondaryElements) { if (element.isDisplayed()) {
	 * return element; } }
	 * 
	 * return null; }); }
	 */

	public String selectLocator(List<String> xpaths) {

		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(15));

		for (String xpath : xpaths) {

			By locator = By.xpath(xpath);

			try {

				WebElement element = wait.until(driver -> {

					try {
						// First scroll to the element
						scrollVerticalUntilVisible(locator);

						// Now find the element after scrolling
						WebElement foundElement = driver.findElement(locator);

						if (foundElement.isDisplayed()) {
							return foundElement;
						}

					} catch (StaleElementReferenceException e) {
						// DOM changed - retry
					} catch (Exception e) {
						// Element not available yet - retry
					}

					return null;
				});

				if (element != null) {
					return xpath;
				}

			} catch (TimeoutException e) {
				// Try next XPath
				continue;
			}
		}

		throw new NoSuchElementException("None of the xpaths was found");
	}

	private WebElement findVisibleElement(By locator) {

		List<WebElement> elements = ldriver.findElements(locator);

		for (WebElement element : elements) {
			try {
				if (element.isDisplayed()) {
					return element;
				}
			} catch (StaleElementReferenceException e) {
				// Ignore stale element
			}
		}

		return null;
	}

	public WebElement findElement(By primaryLocator, By secondaryLocator) {

		// First: check what is already visible
		WebElement element = findVisibleElement(primaryLocator);
		if (element != null) {
			return element;
		}

		element = findVisibleElement(secondaryLocator);
		if (element != null) {
			return element;
		}

		// Neither is visible.
		// Let primary locator perform the existing scrolling.
		try {
			scrollVerticalUntilVisible(primaryLocator);

			element = findVisibleElement(primaryLocator);
			if (element != null) {
				return element;
			}

			// At the same scroll position, check secondary
			element = findVisibleElement(secondaryLocator);
			if (element != null) {
				return element;
			}

		} catch (Exception e) {
			// Primary could not be located
		}

		// Only if still not found, try secondary scrolling
		try {
			scrollVerticalUntilVisible(secondaryLocator);

			element = findVisibleElement(primaryLocator);
			if (element != null) {
				return element;
			}

			element = findVisibleElement(secondaryLocator);
			if (element != null) {
				return element;
			}

		} catch (Exception e) {
			// Secondary could not be located
		}

		throw new NoSuchElementException("Neither primary nor secondary element was found");
	}

	public void clickElement(WebElement element) {

		// wait.until(ExpectedConditions.elementToBeClickable(element));

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

//	public boolean isElementEnabledOrDisabled(WebElement element, String status) {
//
//		boolean actualEnabled;
//		if (element.getAttribute("enabled").equals("true")) {
//			actualEnabled = true;
//		} else {
//			actualEnabled = false;
//		}
//
//		return status.equalsIgnoreCase("Enabled") == actualEnabled;
//	}

	public boolean isElementEnabledOrDisabled(WebElement element, String status) {

		return wait.until(driver -> {
			try {
				String enabledAttribute = element.getAttribute("enabled");

				boolean actualEnabled = "true".equalsIgnoreCase(enabledAttribute);

				return status.equalsIgnoreCase("Enabled") == actualEnabled;

			} catch (StaleElementReferenceException | NoSuchElementException e) {
				return false;
			}
		});
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

		int maxScrolls =15;

		// Already visible
		List<WebElement> targets = ldriver.findElements(target);

		if (!targets.isEmpty() && targets.get(0).isDisplayed()) {
			return true;
		}

		// Find the main/largest ScrollView
		WebElement mainScrollView = getMainScrollView();

		if (mainScrollView == null) {
			return false;
		}

		Rectangle rect = mainScrollView.getRect();

		int x = rect.getX() + rect.getWidth() / 2;

		// ==========================================
		// Scroll DOWN
		// ==========================================
		for (int i = 0; i < maxScrolls; i++) {

			rect = mainScrollView.getRect();

			int startY = rect.getY() + (int) (rect.getHeight() * 0.80);
			int endY = rect.getY() + (int) (rect.getHeight() * 0.20);

			performSwipe(x, startY, x, endY);

			targets = ldriver.findElements(target);

			if (!targets.isEmpty() && targets.get(0).isDisplayed()) {
				return true;
			}

			System.out.println("================================");
			System.out.println("targets: " + targets);

		}

		// ==========================================
		// Scroll UP
		// ==========================================
		for (int i = 0; i < maxScrolls; i++) {

			rect = mainScrollView.getRect();

			int startY = rect.getY() + (int) (rect.getHeight() * 0.20);
			int endY = rect.getY() + (int) (rect.getHeight() * 0.80);

			performSwipe(x, startY, x, endY);

			targets = ldriver.findElements(target);

			if (!targets.isEmpty() && targets.get(0).isDisplayed()) {
				return true;
			}

			System.out.println("================================");
			System.out.println("targets: " + targets);
		}

		return false;
	}

	private boolean isElementVisibleInScrollView(By target, WebElement scrollView) {

		List<WebElement> elements = ldriver.findElements(target);

		if (elements.isEmpty()) {
			return false;
		}

		WebElement element = elements.get(0);

		if (!element.isDisplayed()) {
			return false;
		}

		Rectangle elementRect = element.getRect();
		Rectangle scrollRect = scrollView.getRect();

		int elementTop = elementRect.getY();
		int elementBottom = elementRect.getY() + elementRect.getHeight();

		int scrollTop = scrollRect.getY();
		int scrollBottom = scrollRect.getY() + scrollRect.getHeight();

		return elementTop >= scrollTop && elementBottom <= scrollBottom;
	}

	private WebElement getMainScrollView() {

		List<WebElement> scrollViews = ldriver.findElements(By.className("android.widget.ScrollView"));

		if (scrollViews.isEmpty()) {
			return null;
		}

		WebElement mainScrollView = null;
		int largestArea = 0;

		for (WebElement scrollView : scrollViews) {

			Rectangle rect = scrollView.getRect();

			int area = rect.getWidth() * rect.getHeight();

			System.out.println("ScrollView: x=" + rect.getX() + ", y=" + rect.getY() + ", width=" + rect.getWidth()
					+ ", height=" + rect.getHeight());

			if (area > largestArea) {
				largestArea = area;
				mainScrollView = scrollView;
			}
		}

		return mainScrollView;
	}

	private void performSwipe(int startX, int startY, int endX, int endY) {

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence swipe = new Sequence(finger, 0);

		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));

		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

		swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY));

		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

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

	public void setCheckbox(WebElement checkboxElement, String status) throws InterruptedException {

		Thread.sleep(3000);

		boolean shouldBeChecked;

		if (status.equalsIgnoreCase("checked")) {
			shouldBeChecked = true;
		} else if (status.equalsIgnoreCase("unchecked")) {
			shouldBeChecked = false;
		} else {
			throw new IllegalArgumentException(
					"Invalid checkbox status: " + status + ". Expected 'checked' or 'unchecked'.");
		}

		boolean isChecked = Boolean.parseBoolean(checkboxElement.getAttribute("checked"));

		logger.info(checkboxElement + " status :" + isChecked);

		if (isChecked != shouldBeChecked) {
			checkboxElement.click();
		}

	}

	public String getText(WebElement element) {
		try {
			return element.getText();
		} catch (Exception e) {
			return null;
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

				return value.equals(currentValue) || value.equals(currentText);
			}
		});
	}

	public String getFieldValue(WebElement element) {

		String currentValue = getAttributeOfElement(element, "value");
		String currentText = getAttributeOfElement(element, "text");

		if (currentValue != null) {

			return currentValue;
		}

		else {

			return currentText;

		}
	}

	public void waitForValue(WebElement element) {

		wait.until(new Function<AppiumDriver, Boolean>() {

			@Override
			public Boolean apply(AppiumDriver driver) {

				String currentValue = getAttributeOfElement(element, "value");
				String currentText = getAttributeOfElement(element, "text");

				return (currentValue != null && !currentValue.trim().isEmpty()) || !currentText.equals("");
			}
		});
	}

	public void waitForElementToDisappear(WebElement element) {

		wait = new FluentWait<>(ldriver).withTimeout(Duration.ofSeconds(90)).pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);

		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public String generateUniqueValue(String text) throws InterruptedException {

		String data = DataReader.get(text);

		if (data != null && !data.isEmpty()) {
			text = data;
		}

		DateFormat dateFormat = new SimpleDateFormat("MM:dd HH:mm:ss");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		String ID = text + date1.replace(" ", "").replace(":", "");
		return ID;
	}

	public void pushImageToDevice(String imageName) throws Exception {

		URL resource = getClass().getClassLoader().getResource("images/" + imageName);

		if (resource == null) {
			throw new FileNotFoundException("Image not found: " + imageName);
		}

		Path imagePath = Paths.get(resource.toURI());

		if (!imagePath.toFile().exists()) {
			throw new RuntimeException("Profile image not found: " + imagePath);
		}

		// Android shared internal storage
		String remotePath = "/storage/emulated/0/Pictures/" + imageName;

		// ============================================================
		// 1. Push image to device
		// ============================================================

		ProcessBuilder processBuilder = new ProcessBuilder("adb", "push", imagePath.toString(), remotePath);

		processBuilder.redirectErrorStream(true);

		Process process = processBuilder.start();

		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

		String line;

		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}

		int exitCode = process.waitFor();

		if (exitCode != 0) {
			throw new RuntimeException("Failed to push profile image to device");
		}

		System.out.println("Profile image pushed successfully: " + remotePath);

		// ============================================================
		// 2. Update image modified date/time to current device time
		// ============================================================

		ProcessBuilder touchProcessBuilder = new ProcessBuilder("adb", "shell", "touch", remotePath);

		touchProcessBuilder.redirectErrorStream(true);

		Process touchProcess = touchProcessBuilder.start();

		BufferedReader touchReader = new BufferedReader(new InputStreamReader(touchProcess.getInputStream()));

		while ((line = touchReader.readLine()) != null) {
			System.out.println(line);
		}

		int touchExitCode = touchProcess.waitFor();

		if (touchExitCode != 0) {
			throw new RuntimeException("Failed to update image date/time: " + remotePath);
		}

		System.out.println("Image date/time updated successfully: " + remotePath);

		// ============================================================
		// 3. Trigger Android Media Scanner
		// ============================================================

		ProcessBuilder scanProcessBuilder = new ProcessBuilder("adb", "shell", "am", "broadcast", "-a",
				"android.intent.action.MEDIA_SCANNER_SCAN_FILE", "-d", "file://" + remotePath);

		scanProcessBuilder.redirectErrorStream(true);

		Process scanProcess = scanProcessBuilder.start();

		BufferedReader scanReader = new BufferedReader(new InputStreamReader(scanProcess.getInputStream()));

		while ((line = scanReader.readLine()) != null) {
			System.out.println(line);
		}

		int scanExitCode = scanProcess.waitFor();

		if (scanExitCode != 0) {
			throw new RuntimeException("Failed to scan profile image");
		}

		System.out.println("Profile image registered with media scanner: " + remotePath);

		// Give MediaStore a little time to update
		Thread.sleep(1000);
	}

	public boolean compareImages(String expectedImageName, String actualImageName)
			throws URISyntaxException, IOException {

//		URL resourceExpected = getClass().getClassLoader().getResource("images/" + expectedImageName);
//
//		URL resourceActual = getClass().getClassLoader().getResource("images_screenshots/" + actualImageName);
//
//		if (resourceExpected == null) {
//			throw new FileNotFoundException("Expected image not found: " + resourceExpected);
//		}
//
//		if (resourceActual == null) {
//			throw new FileNotFoundException("Actual image not found: " + resourceActual);
//		}

		Path screenshotsDirExpected = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "images");

		Files.createDirectories(screenshotsDirExpected);

		Path destinationExpected = screenshotsDirExpected.resolve(expectedImageName);

		System.out.println("destination: " + destinationExpected);

		Path screenshotsDirActual = Paths.get(System.getProperty("user.dir"), "src", "test", "resources",
				"images_screenshots");

		Files.createDirectories(screenshotsDirActual);

		Path destinationActual = screenshotsDirActual.resolve(actualImageName);

		System.out.println("destination: " + destinationActual);

		if (!destinationExpected.toFile().exists()) {
			throw new RuntimeException("Expected image not found: " + destinationExpected);
		}

		if (!destinationActual.toFile().exists()) {
			throw new RuntimeException("Actual image not found: " + destinationActual);
		}

		Mat expected = Imgcodecs.imread(destinationExpected.toString());

		Mat actual = Imgcodecs.imread(destinationActual.toString());

		if (expected.empty() || actual.empty()) {
			throw new RuntimeException("Unable to read one of the images");
		}

		// Resize actual image to expected dimensions
		Imgproc.resize(actual, actual, expected.size());

		// Create circular mask
		Mat mask = Mat.zeros(expected.size(), CvType.CV_8UC1);

		int centerX = expected.cols() / 2;
		int centerY = expected.rows() / 2;

		int radius = Math.min(expected.cols(), expected.rows()) / 2;

		Imgproc.circle(mask, new Point(centerX, centerY), radius, new Scalar(255), -1);

		// Compare only inside the circle
		Mat diff = new Mat();

		Core.absdiff(expected, actual, diff);

		Scalar meanDifference = Core.mean(diff, mask);

		double difference = (meanDifference.val[0] + meanDifference.val[1] + meanDifference.val[2]) / 3.0;

		double similarity = 100.0 - (difference / 255.0 * 100.0);

		System.out.println("Image similarity: " + similarity + "%");

		return similarity >= 90.0;
	}

	public long WaitTime(String arg) throws InterruptedException {

		Thread.sleep(Long.parseLong(arg) * 1000);

		return Long.parseLong(arg) * 1000;
	}

	public void fileDelete(Path file) throws IOException {

		System.out.println("file: " + file);

		if (Files.exists(file)) {
			System.out.println("Files: " + Files.exists(file));

			Files.delete(file);
		}

		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(30));

		wait.until(driver -> !Files.exists(file));

		System.out.println("File deleted successfully: " + file);
	}

	public void concatValues(String arg1, String arg2) {
		if (arg1.contains("?")) {
			String[] split = arg1.split("\\?");
			StringBuilder finalValue = new StringBuilder();
			for (String string : split) {
				if (DataReader.get(string) != null) {
					String value = DataReader.get(string);
					finalValue.append(value);
				} else {
					finalValue.append(string);
				}
			}
			DataReader.set(arg2, finalValue.toString());
		}

		System.out.println(arg2 + " :" + DataReader.get(arg2));
	}

}
