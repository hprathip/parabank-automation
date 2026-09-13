package com.parabank.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.parabank.automation.utils.ConfigReader;
import com.parabank.automation.utils.ElementUtil;
import com.parabank.automation.utils.LogUtil;

public class HomePage {
	private static Logger log = LogUtil.getLogger(HomePage.class);

	private WebDriver driver;
	
	private By registerLink = By.linkText("Register");
	private By usernameInputBox = By.name("username");
	private By passwordInputBox = By.name("password");
	private By loginButton = By.xpath("//div[@class='login']/input[@type='submit' and @class='button']");
	private By errorTextOnHomePage = By.xpath("//h1[text()='Error!']//following-sibling::p[@class='error']");
	
	private static final int WAIT_TIME_FOR_LINK_CLICK = Integer.parseInt(ConfigReader.getProperty("links.click.wait"));
	private static final int WAIT_FOR_ELEMENT_VISIBLE = 5;
	private static final int WAIT_FOR_ELEMENT_CLICKABLE = 5;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterUsername(String username) {
		ElementUtil.enterData(driver, usernameInputBox, username, WAIT_FOR_ELEMENT_VISIBLE);
	}
	
	public void enterPassword(String password) {
		ElementUtil.enterData(driver, passwordInputBox, password, WAIT_FOR_ELEMENT_VISIBLE);
	}
	
	public String getErrorTextOnHomePage() {
		return ElementUtil.getText(driver, errorTextOnHomePage, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void clickRegisterLink() {
		ElementUtil.clickElement(driver, registerLink, WAIT_TIME_FOR_LINK_CLICK);
	}
	
	public void clickLoginButton() {
		ElementUtil.clickElement(driver, loginButton, WAIT_FOR_ELEMENT_CLICKABLE);
	}
	
	public void loginUser(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		
		clickLoginButton();
	}

}
