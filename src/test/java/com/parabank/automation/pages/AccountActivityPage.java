package com.parabank.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.parabank.automation.utils.ConfigReader;
import com.parabank.automation.utils.ElementUtil;
import com.parabank.automation.utils.LogUtil;

public class AccountActivityPage {

	private WebDriver driver;

	private final Logger log = LogUtil.getLogger(AccountActivityPage.class);

	private static final int WAIT_FOR_ELEMENT_VISIBLE = 5;
	private static final int WAIT_FOR_ELEMENT_CLICKABLE = 5;

	private String accountActivity_url = ConfigReader.getProperty("account.activity.url");
	private By accountIdDetail = By.id("accountId");
	private By accountTypeDetail = By.id("accountType");
	private By balanceDetail = By.id("balance");
	private By availableBalanceDetail = By.id("availableBalance");

	public AccountActivityPage(WebDriver driver) {
		this.driver = driver;
	}

	public int getAccountIdFromAccountActivityPage() {
		return Integer.parseInt(ElementUtil.getText(driver, accountIdDetail, WAIT_FOR_ELEMENT_VISIBLE));
	}

	public String getAccountTypeFromAccountActivityPage() {
		return ElementUtil.getText(driver, accountTypeDetail, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public String getBalanceFromAccountActivityPage() {
		return ElementUtil.getText(driver, balanceDetail, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public String getAvailableBalanceFromAccountActivityPage() {
		return ElementUtil.getText(driver, availableBalanceDetail, WAIT_FOR_ELEMENT_VISIBLE);
	}

}
