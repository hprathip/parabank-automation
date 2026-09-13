package com.parabank.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.parabank.automation.utils.ConfigReader;
import com.parabank.automation.utils.ElementUtil;

public class AccountsOverviewPage {

	private WebDriver driver;
	private static final int WAIT_FOR_ELEMENT_VISIBLE = 5;
	private static final int WAIT_FOR_ELEMENT_CLICKABLE = 5;
	private String accountsOverview_url = ConfigReader.getProperty("accounts.overview.url");

	private By accountOverviewHeader = By.xpath("//div[@id='showOverview']/h1[@class='title']");

	public AccountsOverviewPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getAccountOverviewHeader() {
		return ElementUtil.getText(driver, accountOverviewHeader, WAIT_FOR_ELEMENT_VISIBLE);
	}
	
	public void navigateToAccountsOverviewUrl() {
		driver.get(accountsOverview_url);
	}

}
