package com.parabank.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.parabank.automation.utils.ElementUtil;

public class SideNavPage {

	private static final int WAIT_FOR_ELEMENT_VISIBLE = 5;
	private static final int WAIT_FOR_ELEMENT_CLICKABLE = 5;

	private WebDriver driver;

	private By welcomeMessage = By.xpath("//p[@class='smallText']");
	private By logOutLink = By.linkText("Log Out");
	private By openNewAccountLink = By.linkText("Open New Account");
	private By accountsOverviewLink = By.linkText("Accounts Overview");
	private By transferFundsLink = By.linkText("Transfer Funds");

	public SideNavPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getWelcomeMessage() {
		return ElementUtil.getText(driver, welcomeMessage, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void clickLogOut() {
		ElementUtil.clickElement(driver, logOutLink, WAIT_FOR_ELEMENT_CLICKABLE);
	}

	public void clickAccountsOverview() {
		ElementUtil.clickElement(driver, accountsOverviewLink, WAIT_FOR_ELEMENT_CLICKABLE);
	}

	public void clickOpenNewAccountLink() {
		ElementUtil.clickElement(driver, openNewAccountLink, WAIT_FOR_ELEMENT_CLICKABLE);
	}
}