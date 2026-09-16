package com.parabank.automation.stepdefinitions;

import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.parabank.automation.context.TestContext;
import com.parabank.automation.factory.DriverFactory;
import com.parabank.automation.utils.LogUtil;
import com.parabank.automation.utils.WaitUtil;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationSteps {

	private static Logger log = LogUtil.getLogger(RegistrationSteps.class);

	private final TestContext context;

	public RegistrationSteps(TestContext context) {
		this.context = context;
	}

	private String uniqueUsername;
	private Map<String, String> lastRegistrationData;

	@When("the user clicks on the register link")
	public void the_user_clicks_on_the_register_link() {
		context.getHomePage().clickRegisterLink();

		Assert.assertTrue(WaitUtil.waitForUrlContains(DriverFactory.getDriver(), "register", 45),
				"The user is not on the registration page");
	}

	@When("the user enters the following registration details:")
	public void the_user_enters_the_following_registration_details(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap(String.class, String.class);
		lastRegistrationData = data;
		uniqueUsername = data.get("username") + System.currentTimeMillis();
		log.info("Username: "+uniqueUsername);
		context.getRegistrationPage().registerUser(data.get("firstName"), data.get("lastName"), data.get("address"),
				data.get("city"), data.get("state"), data.get("zipCode"), data.get("phone"), data.get("ssn"),
				uniqueUsername, data.get("password"), data.get("confirmPassword"));
	}

	@And("user clicks on the Register button")
	public void user_clicks_button() {
		context.getRegistrationPage().clickRegisterButton();
	}

	@Then("the account should be created successfully with a confirmation message {string}")
	public void the_account_should_be_created_successfully_with_confirmation_message(String confirmation_message) {
		Assert.assertEquals(context.getAccountServicesPage().getSuccessMessageString(), confirmation_message,
				"The account creation confirmation message is not shown as expected message");
	}


	@And("the user attempts to register with the same username again")
	public void the_user_attempts_to_register_with_the_same_username_again() {
		context.getRegistrationPage().registerUser(lastRegistrationData.get("firstName"),
				lastRegistrationData.get("lastName"), lastRegistrationData.get("address"),
				lastRegistrationData.get("city"), lastRegistrationData.get("state"),
				lastRegistrationData.get("zipCode"), lastRegistrationData.get("phone"), lastRegistrationData.get("ssn"),
				uniqueUsername, lastRegistrationData.get("password"), lastRegistrationData.get("confirmPassword"));
	}

	@Then("the user should see an error message {string} near the username field")
	public void the_user_should_see_an_error_message_near_the_username_field(String error_message) {
		boolean is_error_shown = context.getRegistrationPage().isUsernameInputBoxErrorDisplayed();
		Assert.assertTrue(is_error_shown, "The error message near username is not shown");
		Assert.assertEquals(context.getRegistrationPage().getUsernameInputBoxError(), error_message,
				"The expected error message is not shown near the username field");
	}

	@Then("the user should see an error message {string} near the confirm password field")
	public void the_user_should_see_an_error_message_near_the_confirm_password_field(String error_message) {
		boolean is_error_shown = context.getRegistrationPage().isConfirmPasswordInputBoxErrorDisplayed();
		Assert.assertTrue(is_error_shown, "The error message near confirm password field is not shown");
		Assert.assertEquals(context.getRegistrationPage().getConfirmPasswordInputBoxError(), error_message,
				"The expected error message is not shown near the confirm password field");
	}

	@And("user leaves all the input fields empty")
	public void user_leaves_all_the_input_fields_empty() {
		log.info("The user leaves the input fields empty");
	}

	@Then("the user should see required validation message near all the mandatory fields")
	public void the_user_should_see_required_validation_message_near_all_the_mandatory_fields() {
		Assert.assertTrue(context.getRegistrationPage().isFirstNameInputBoxErrorDisplayed(),
				"Validation message is not shown near the first name field");
		Assert.assertTrue(context.getRegistrationPage().isLastNameInputBoxErrorDisplayed(),
				"Validation message is not shown near the last name field");
		Assert.assertTrue(context.getRegistrationPage().isAddressInputBoxErrorDisplayed(),
				"Validation message is not shown near the address field");
		Assert.assertTrue(context.getRegistrationPage().isCityInputBoxErrorDisplayed(),
				"Validation message is not shown near the city field");
		Assert.assertTrue(context.getRegistrationPage().isStateInputBoxErrorDisplayed(),
				"Validation message is not shown near the state field");
		Assert.assertTrue(context.getRegistrationPage().isZipCodeInputBoxErrorDisplayed(),
				"Validation message is not shown near the zip code field");
		Assert.assertTrue(context.getRegistrationPage().isSSNInputBoxErrorDisplayed(),
				"Validation message is not shown near the SSN field");
		Assert.assertTrue(context.getRegistrationPage().isUsernameInputBoxErrorDisplayed(),
				"Validation message is not shown near the username field");
		Assert.assertTrue(context.getRegistrationPage().isPasswordInputBoxErrorDisplayed(),
				"Validation message is not shown near the password field");
		Assert.assertTrue(context.getRegistrationPage().isConfirmPasswordInputBoxErrorDisplayed(),
				"Validation message is not shown near the confirm password field");

		Assert.assertEquals(context.getRegistrationPage().getFirstNameInputBoxError(), "First name is required.",
				"The expected validation message is not shown accurately for the first name field");
		Assert.assertEquals(context.getRegistrationPage().getLastNameInputBoxError(), "Last name is required.",
				"The expected validation message is not shown accurately for the last name field");
		Assert.assertEquals(context.getRegistrationPage().getAddressInputBoxError(), "Address is required.",
				"The expected validation message is not shown accurately for the address field");
		Assert.assertEquals(context.getRegistrationPage().getCityInputBoxError(), "City is required.",
				"The expected validation message is not shown accurately for the city field");
		Assert.assertEquals(context.getRegistrationPage().getStateInputBoxError(), "State is required.",
				"The expected validation message is not shown accurately for the state field");
		Assert.assertEquals(context.getRegistrationPage().getZipCodeInputBoxError(), "Zip Code is required.",
				"The expected validation message is not shown accurately for the zip code field");
		Assert.assertEquals(context.getRegistrationPage().getSSNInputBoxError(), "Social Security Number is required.",
				"The expected validation message is not shown accurately for the SSN field");
		Assert.assertEquals(context.getRegistrationPage().getUsernameInputBoxError(), "Username is required.",
				"The expected validation message is not shown accurately for the username field");
		Assert.assertEquals(context.getRegistrationPage().getPasswordInputBoxError(), "Password is required.",
				"The expected validation message is not shown accurately for the password field");
		Assert.assertEquals(context.getRegistrationPage().getConfirmPasswordInputBoxError(),
				"Password confirmation is required.",
				"The expected validation message is not shown accurately for the confirm password field");

	}

}
