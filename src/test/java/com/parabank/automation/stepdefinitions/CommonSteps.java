package com.parabank.automation.stepdefinitions;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.parabank.automation.context.TestContext;
import com.parabank.automation.factory.DriverFactory;
import com.parabank.automation.utils.ApiHelper;
import com.parabank.automation.utils.ConfigReader;
import com.parabank.automation.utils.LogUtil;
import com.parabank.automation.utils.WaitUtil;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonSteps {

	private final TestContext context;
	private static Logger log = LogUtil.getLogger(CommonSteps.class);

	public CommonSteps(TestContext context) {
		this.context = context;
	}

	@Given("the user is on the Parabank application homepage")
	public void the_user_is_on_the_parabank_application_homepage() {
		Assert.assertTrue(WaitUtil.waitForUrlContains(DriverFactory.getDriver(), "index", 45),
				"The user is not on the home page");
	}

	@When("the user clicks the log out link")
	public void the_user_clicks_the_log_out_link() {
		context.getSideNavPage().clickLogOut();
	}

	@And("the user should see the welcome message reading {string}")
	public void the_user_should_see_the_welcome_message_reading(String welcome_message) {
		String resolveWelcomeMessage;

		// log.info(welcome_message + " " +
		// context.getSideNavPage().getWelcomeMessage());

		if (welcome_message.equalsIgnoreCase("valid")) {
			resolveWelcomeMessage = ConfigReader.getProperty("validuser.welcome_message");

		} else {
			resolveWelcomeMessage = welcome_message;

		}
		Assert.assertEquals(context.getSideNavPage().getWelcomeMessage(), resolveWelcomeMessage,
				"The welcome message doesn't include the logged in user's name");

	}

	@Then("the user is redirected to {string} page")
	public void the_user_is_redirected_to_page(String pageTitle) {

		if (pageTitle.contains("Overview")) {

			Assert.assertTrue(WaitUtil.waitForUrlContains(DriverFactory.getDriver(), "overview", 45),
					"The user did not land on the Accounts Overview page");

			Assert.assertEquals(context.getAccountsOverviewPage().getAccountOverviewHeader(), pageTitle,
					"The user did not land on the Accounts Overview page");

		} else if (pageTitle.contains("Home")) {

			Assert.assertTrue(WaitUtil.waitForUrlContains(DriverFactory.getDriver(), "index", 45),
					"The user did not land on the Home page");

		}

		else if (pageTitle.contains("Open New Account")) {

			Assert.assertTrue(WaitUtil.waitForUrlContains(DriverFactory.getDriver(), "openaccount", 45),
					"The user did not land on the Open New Account page");

		}

		else {
			Assert.fail("Unhandled page title in step definition: " + pageTitle);
		}

	}

	@And("the user refreshes the {string} page")
	public void the_user_refreshes_the_page(String pageTitle) {
		DriverFactory.getDriver().navigate().refresh();

	}

	@When("the user creates a new {string} account for the customer using the API")
	public void the_user_creates_a_new_account_for_the_customer_using_the_api(String accountType) {
		int customerId = ApiHelper.getCustomerId(ConfigReader.getProperty("valid.username"),
				ConfigReader.getProperty("valid.password"));
		context.setCustomerId(customerId);

		int fromAccountId = ApiHelper.getAccountIdForCustomer(customerId);
		context.setFromAccountId(fromAccountId);

		int accountTypeId = resolveAccountTypeId(accountType);
		int newAccountId = ApiHelper.createAccount(customerId, accountTypeId, fromAccountId);
		context.setNewAccountIdCreatedViaAPI(newAccountId);

		log.info("Created new account: " + newAccountId + " for customer: " + customerId);
	}

	private int resolveAccountTypeId(String accountType) {
		switch (accountType.toLowerCase()) {
		case "checking":
			return 0;
		case "savings":
			return 1;
		case "loan":
			return 2;
		default:
			throw new IllegalArgumentException("Unknown account type: " + accountType);
		}
	}

	@And("the user clicks the {string} link from the side menu")
	public void the_user_clicks_the_link_from_the_side_menu(String pageLink) {
		if (pageLink.contains("Open New Account")) {
			context.getSideNavPage().clickOpenNewAccountLink();
		}

	}

	@And("the user clicks {string} button")
	public void the_user_clicks_button(String buttonName) {
		if (buttonName.equals("Open new Account")) {
			context.getOpenNewAccountPage().clickOpenNewAccountButton();
		}
	}

}
