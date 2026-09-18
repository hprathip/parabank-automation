package com.parabank.automation.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ElementUtil {

	public static void clickElement(WebDriver driver, By locator, int timeoutSeconds) {
		WaitUtil.waitForElementToBeClickable(driver, locator, timeoutSeconds).click();
	}

	public static void enterData(WebDriver driver, By locator, String text, int timeoutSeconds) {
		WaitUtil.waitForElementToBeVisible(driver, locator, timeoutSeconds).sendKeys(text);
	}

	public static boolean isElementDisplayed(WebDriver driver, By locator, int timeoutSeconds) {
		try {
			return WaitUtil.waitForElementToBeVisible(driver, locator, timeoutSeconds).isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

	public static String getText(WebDriver driver, By locator, int timeoutSeconds) {
		return WaitUtil.waitForElementToBeVisible(driver, locator, timeoutSeconds).getText();
	}
	
	public static List<WebElement> getAllElementsIdentifiedByLocator(WebDriver driver, By locator, int timeoutSeconds) {
		return WaitUtil.waitForElementsToBeVisible(driver, locator, timeoutSeconds);
	}
	
	public static void selectOptionByVisibleTextFromTheDropDown(WebDriver driver, By locator, String visibleOptionText, int timeoutSeconds) {
		Select selectDropDown = new Select(WaitUtil.waitForElementToBeVisible(driver, locator, timeoutSeconds));
		
		selectDropDown.selectByVisibleText(visibleOptionText);
	}

}
