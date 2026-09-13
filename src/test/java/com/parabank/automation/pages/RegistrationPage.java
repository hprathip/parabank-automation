package com.parabank.automation.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.parabank.automation.utils.ElementUtil;
import com.parabank.automation.utils.LogUtil;

public class RegistrationPage {

	private static Logger log = LogUtil.getLogger(RegistrationPage.class);

	private WebDriver driver;

	private static final int WAIT_FOR_ELEMENT_VISIBLE = 5;
	private static final int WAIT_FOR_ELEMENT_CLICKABLE = 5;

//	Locators of Registration page
	private By firstName_inputBox = By.id("customer.firstName");
	private By lastName_inputBox = By.id("customer.lastName");
	private By address_inputBox = By.id("customer.address.street");
	private By city_inputBox = By.id("customer.address.city");
	private By state_inputBox = By.id("customer.address.state");
	private By zipCode_inputBox = By.id("customer.address.zipCode");
	private By phoneNumber_inputBox = By.id("customer.phoneNumber");
	private By username_inputBox = By.id("customer.username");
	private By ssn_inputBox = By.id("customer.ssn");
	private By password_inputBox = By.id("customer.password");
	private By confirmPassword_inputBox = By.id("repeatedPassword");
	private By registerButton = By.xpath("//input[@value='Register' and @type='submit']");

	private By error_firstNameInputBox = By.id("customer.firstName.errors");
	private By error_lastNameInputBox = By.id("customer.lastName.errors");
	private By error_addressInputBox = By.id("customer.address.street.errors");
	private By error_cityInputBox = By.id("customer.address.city.errors");
	private By error_stateInputBox = By.id("customer.address.state.errors");
	private By error_zipCodeInputBox = By.id("customer.address.zipCode.errors");
	private By error_ssnInputBox = By.id("customer.ssn.errors");
	private By error_usernameInputBox = By.id("customer.username.errors");
	private By error_passwordInputBox = By.id("customer.password.errors");
	private By error_confirmPasswordInputBox = By.id("repeatedPassword.errors");

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterFirstName(String firstName) {
		ElementUtil.enterData(driver, firstName_inputBox, firstName, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void enterLastName(String lastName) {
		ElementUtil.enterData(driver, lastName_inputBox, lastName, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void enterAddress(String address) {
		ElementUtil.enterData(driver, address_inputBox, address, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void enterCity(String city) {
		ElementUtil.enterData(driver, city_inputBox, city, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void enterState(String state) {
		ElementUtil.enterData(driver, state_inputBox, state, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void enterZipCode(String zipCode) {
		ElementUtil.enterData(driver, zipCode_inputBox, zipCode, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void enterPhoneNumber(String phoneNumber) {
		ElementUtil.enterData(driver, phoneNumber_inputBox, phoneNumber, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void enterUsername(String username) {
		ElementUtil.enterData(driver, username_inputBox, username, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void enterSsn(String ssn) {
		ElementUtil.enterData(driver, ssn_inputBox, ssn, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void enterPassword(String password) {
		ElementUtil.enterData(driver, password_inputBox, password, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void enterConfirmPassword(String confirm_password) {
		ElementUtil.enterData(driver, confirmPassword_inputBox, confirm_password, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void clickRegisterButton() {
		ElementUtil.clickElement(driver, registerButton, WAIT_FOR_ELEMENT_CLICKABLE);
	}

	public String getFirstNameInputBoxError() {
		return ElementUtil.getText(driver, error_firstNameInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public boolean isFirstNameInputBoxErrorDisplayed() {
		return ElementUtil.isElementDisplayed(driver, error_firstNameInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public String getLastNameInputBoxError() {
		return ElementUtil.getText(driver, error_lastNameInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public boolean isLastNameInputBoxErrorDisplayed() {
		return ElementUtil.isElementDisplayed(driver, error_lastNameInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public String getAddressInputBoxError() {
		return ElementUtil.getText(driver, error_addressInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public boolean isAddressInputBoxErrorDisplayed() {
		return ElementUtil.isElementDisplayed(driver, error_addressInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public String getCityInputBoxError() {
		return ElementUtil.getText(driver, error_cityInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public boolean isCityInputBoxErrorDisplayed() {
		return ElementUtil.isElementDisplayed(driver, error_cityInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public String getStateInputBoxError() {
		return ElementUtil.getText(driver, error_stateInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public boolean isZipCodeInputBoxErrorDisplayed() {
		return ElementUtil.isElementDisplayed(driver, error_zipCodeInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public String getZipCodeInputBoxError() {
		return ElementUtil.getText(driver, error_zipCodeInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public boolean isStateInputBoxErrorDisplayed() {
		return ElementUtil.isElementDisplayed(driver, error_stateInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public String getSSNInputBoxError() {
		return ElementUtil.getText(driver, error_ssnInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public boolean isSSNInputBoxErrorDisplayed() {
		return ElementUtil.isElementDisplayed(driver, error_ssnInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public String getUsernameInputBoxError() {
		return ElementUtil.getText(driver, error_usernameInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public boolean isUsernameInputBoxErrorDisplayed() {
		return ElementUtil.isElementDisplayed(driver, error_usernameInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public String getPasswordInputBoxError() {
		return ElementUtil.getText(driver, error_passwordInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public boolean isPasswordInputBoxErrorDisplayed() {
		return ElementUtil.isElementDisplayed(driver, error_passwordInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public String getConfirmPasswordInputBoxError() {
		return ElementUtil.getText(driver, error_confirmPasswordInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public boolean isConfirmPasswordInputBoxErrorDisplayed() {
		return ElementUtil.isElementDisplayed(driver, error_confirmPasswordInputBox, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void registerUser(String firstname, String lastname, String address, String city, String state,
			String zipCode, String phone, String ssn, String username, String password, String confirm_password) {
		enterFirstName(firstname);
		enterLastName(lastname);
		enterAddress(address);
		enterCity(city);
		enterState(state);
		enterZipCode(zipCode);
		enterPhoneNumber(phone);
		enterSsn(ssn);
		enterUsername(username);
		enterPassword(password);
		enterConfirmPassword(confirm_password);
	}

}
