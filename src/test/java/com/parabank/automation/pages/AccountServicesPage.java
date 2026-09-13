package com.parabank.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.parabank.automation.utils.ElementUtil;
import com.parabank.automation.utils.LogUtil;

public class AccountServicesPage {

	private static Logger log = LogUtil.getLogger(AccountServicesPage.class);

	private WebDriver driver;
	private static final int WAIT_FOR_ELEMENT_VISIBLE = 5;

	private By accountCreation_success_message = By
			.xpath("//h1[@class='title' and starts-with(text(), 'Welcome')]/following-sibling::p");

	public AccountServicesPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getSuccessMessageString() {
		return ElementUtil.getText(driver, accountCreation_success_message, WAIT_FOR_ELEMENT_VISIBLE);
	}
	

}
