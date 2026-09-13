package com.parabank.automation.stepdefinitions;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.parabank.automation.context.TestContext;
import com.parabank.automation.factory.DriverFactory;
import com.parabank.automation.utils.ConfigReader;
import com.parabank.automation.utils.LogUtil;
import com.parabank.automation.utils.WaitUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	private static Logger log = LogUtil.getLogger(LoginSteps.class);

	private final TestContext context;

	public LoginSteps(TestContext context) {
		this.context = context;
	}

	@When("the user enters the username {string} and password {string} and logs in")
	public void the_user_enters_the_username_and_password_and_logs_in(String username, String password) {

		String resolveUsername, resolvePassword;

		if (username.equalsIgnoreCase("valid")) {
			resolveUsername = ConfigReader.getProperty("valid.username");
		} else {
			resolveUsername = username;
		}

		if (password.equalsIgnoreCase("valid")) {
			resolvePassword = ConfigReader.getProperty("valid.password");
		} else {
			resolvePassword = password;
		}

		context.getHomePage().loginUser(resolveUsername, resolvePassword);
		log.info("The user has logged in successfully with the provided credentials");
	}

	@Then("the user should see the error message reading {string}")
	public void the_user_should_see_the_error_message_reading(String error_message) {
		Assert.assertEquals(context.getHomePage().getErrorTextOnHomePage(), error_message,
				"The error message shown to the user doesn't match the expected message");
	}

	@Given("the user is on the Parabank application homepage and has no active session")
	public void the_user_is_on_the_Parabank_application_homepage_and_has_no_active_session() {

		Assert.assertTrue(WaitUtil.waitForUrlContains(DriverFactory.getDriver(), "index", 45),
				"The user is not on the home page");
		DriverFactory.getDriver().manage().deleteAllCookies();
	}

	@Then("the user should be redirected to the homepage")
	public void the_user_should_be_redirected_to_the_homepage() {
		Assert.assertTrue(WaitUtil.waitForUrlContains(DriverFactory.getDriver(), "index", 45),
				"User was not redirected to homepage after logout");
	}

	@When("the user navigates to the Accounts overview directly without logging in")
	public void the_user_navigates_to_the_accounts_overview_directly_without_logging_in() {
		context.getAccountsOverviewPage().navigateToAccountsOverviewUrl();
	}

}
