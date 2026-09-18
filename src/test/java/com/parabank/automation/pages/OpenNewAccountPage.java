package com.parabank.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.parabank.automation.utils.ElementUtil;

public class OpenNewAccountPage {

	private static final int WAIT_FOR_ELEMENT_VISIBLE = 5;
	private static final int WAIT_FOR_ELEMENT_CLICKABLE = 5;

	private WebDriver driver;

	private By accountTypeSelectDropDown = By.id("type");
	private By fromAccountIdSelectDropDown = By.id("fromAccountId");
	private By openNewAccountButton = By.xpath("//input[@type='button' and @value='Open New Account']");

	private By accountOpenedHeading = By.xpath("//div[@id='openAccountResult']/h1");
	private By accountOpeningMessage = By.xpath("//div[@id='openAccountResult']/p[1]");
	private By newAccountOpenedId = By.id("newAccountId");

	public OpenNewAccountPage(WebDriver driver) {
		this.driver = driver;
	}

	public void selectAccountTypeInDropDown(String accountType) {
		ElementUtil.selectOptionByVisibleTextFromTheDropDown(driver, accountTypeSelectDropDown, accountType,
				WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void selectFromAccountIdInDropDown(String accountId) {
		ElementUtil.selectOptionByVisibleTextFromTheDropDown(driver, fromAccountIdSelectDropDown, accountId,
				WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void clickOpenNewAccountButton() {
		ElementUtil.clickElement(driver, openNewAccountButton, WAIT_FOR_ELEMENT_CLICKABLE);
	}

	public String getAccountOpenedHeading() {
		return ElementUtil.getText(driver, accountOpenedHeading, WAIT_FOR_ELEMENT_VISIBLE);
	}
	
	public boolean isAccountOpenedHeadingDisplayed() {
		return ElementUtil.isElementDisplayed(driver, accountOpenedHeading, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public String getAccountOpenedMessage() {
		return ElementUtil.getText(driver, accountOpeningMessage, WAIT_FOR_ELEMENT_VISIBLE);
	}
	
	public boolean isAccountOpenedMessageDisplayed() {
		return ElementUtil.isElementDisplayed(driver, accountOpeningMessage, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public String getNewOpenedAccountId() {
		return ElementUtil.getText(driver, newAccountOpenedId, WAIT_FOR_ELEMENT_VISIBLE);
	}
	
	public boolean isNewOpenedAccountIdDisplayed() {
		return ElementUtil.isElementDisplayed(driver, newAccountOpenedId, WAIT_FOR_ELEMENT_VISIBLE);
	}

}
