package com.parabank.automation.stepdefinitions;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.parabank.automation.context.TestContext;
import com.parabank.automation.factory.DriverFactory;
import com.parabank.automation.utils.ApiHelper;
import com.parabank.automation.utils.ConfigReader;
import com.parabank.automation.utils.ElementUtil;
import com.parabank.automation.utils.LogUtil;
import com.parabank.automation.utils.WaitUtil;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountsOverviewSteps {

	private final Logger log = LogUtil.getLogger(AccountsOverviewSteps.class);

	private static final int WAIT_FOR_URL_UPDATE = 10;

	private int accountId;
	private String balanceFromAccountOverviewTable;
	private String availableAmountFromAccountOverviewTable;

	private int customerId;
	private int newAccountId;

	private double sumOfAllBalancesInAccountsOverviewTable;
	private double totalBalanceShownInAccountsOverviewTable;

	private final TestContext context;

	public AccountsOverviewSteps(TestContext context) {
		this.context = context;
	}

	@Then("the user should see the Accounts overview table with {string}, {string} and {string} columns")
	public void the_user_should_see_the_Accounts_overview_table(String column1, String column2, String column3) {
		Assert.assertTrue(context.getAccountsOverviewPage().isAccountsOverviewTableDisplayed(),
				"The Accounts Overview table is not displayed in the Accounts Overview page");

		Assert.assertEquals(context.getAccountsOverviewPage().getAccountsOverviewTableColumnName(1), column1,
				"The first column name doesn't match the " + column1);
		Assert.assertTrue(context.getAccountsOverviewPage().isAccountColumnDisplayed(),
				"Account column is not displayed in the Accounts Overview table");

		Assert.assertEquals(context.getAccountsOverviewPage().getAccountsOverviewTableColumnName(2), column2,
				"The second column name doesn't match the " + column2);
		Assert.assertTrue(context.getAccountsOverviewPage().isBalanceColumnDisplayed(),
				"Balance* column is not displayed in the Accounts Overview table");

		Assert.assertEquals(context.getAccountsOverviewPage().getAccountsOverviewTableColumnName(3), column3,
				"The third column name doesn't match the " + column3);
		Assert.assertTrue(context.getAccountsOverviewPage().isAvailableAmountColumnDisplayed(),
				"Available Amount column is not displayed in the Accounts Overview table");

	}

	@And("the Accounts overview table should display at least one account row")
	public void the_accounts_overview_table_should_display_at_least_one_account_row_populated_with_non_empty_values_for_every_row() {
		Assert.assertTrue(context.getAccountsOverviewPage().getNumberOfAccountsInTheAccountsOverviewPage() > 0,
				"The Accounts Overview page doesn't show any accounts in the Accounts Overview table");
	}

	@And("every account row in the table should be populated with non-empty values")
	public void every_account_row_in_the_table_should_be_populated_with_non_empty_values_for_every_row() {
		Assert.assertTrue(context.getAccountsOverviewPage().verifyAccountsOverviewTableRowsData(),
				"The Accounts Overview table has empty values");
	}

	@When("the user clicks on an account number link in the table")
	public void the_user_clicks_on_an_account_number_link_in_the_table() {
		int customerId = ApiHelper.getCustomerId(ConfigReader.getProperty("valid.username"),
				ConfigReader.getProperty("valid.password"));
		accountId = ApiHelper.getAccountIdForCustomer(customerId);

		log.info("clicking the Account: " + accountId + " from the Accounts Overview table");

		balanceFromAccountOverviewTable = context.getAccountsOverviewPage()
				.getBalanceFromAccountOverviewTableForAccountId(accountId);
		availableAmountFromAccountOverviewTable = context.getAccountsOverviewPage()
				.getAvailableAmountFromAccountOverviewTableForAccountId(accountId);

		context.getAccountsOverviewPage().clickAccountIdHyperlink(accountId);

	}

	@Then("the user is redirected to {string} page of the account id selected")
	public void the_user_is_redirected_to_page_of_the_account_id_selected(String pageTitle) {
		Assert.assertTrue(WaitUtil.waitForUrlContains(DriverFactory.getDriver(), "activity", WAIT_FOR_URL_UPDATE),
				"The user is not navigated to the account acitvity page");

		Assert.assertTrue(WaitUtil.waitForUrlContains(DriverFactory.getDriver(), String.valueOf(accountId), WAIT_FOR_URL_UPDATE),
				"The user is not navigated to the account acitvity page of the account ID selected: "+accountId);

	}

	@And("the user should see the matching Account Number, Account Type, Balance, and Available amount in the Account activity page")
	public void the_user_should_see_the_matching_in_the_account_activity_page() {
		Assert.assertEquals(context.getAccountActivityPage().getAccountIdFromAccountActivityPage(), accountId,
				"The account Id in Account activity page doesn't match the account Id clicked in the Accounts Overview page");
		Assert.assertEquals(context.getAccountActivityPage().getBalanceFromAccountActivityPage(),
				balanceFromAccountOverviewTable, "The balance shown in Account activity page for accountID: "
						+ accountId + " doesn't match the balance shown in the Accounts Overview page");
		Assert.assertEquals(context.getAccountActivityPage().getAvailableBalanceFromAccountActivityPage(),
				availableAmountFromAccountOverviewTable,
				"The available amount shown in Account activity page for accountID: " + accountId
						+ " doesn't match the available amount shown in the Accounts Overview page");
	}

	@When("the user creates the new {string} account for the customer using the API")
	public void the_user_creates_the_new_account_for_the_customer_using_the_api(String accountType) {
		customerId = ApiHelper.getCustomerId(ConfigReader.getProperty("valid.username"),
				ConfigReader.getProperty("valid.password"));

		log.info("The customerId of the logged in customer is: " + customerId);

		accountId = ApiHelper.getAccountIdForCustomer(customerId);

		int accountTypeId = -1;

		switch (accountType.toLowerCase()) {
		case "checking":
			accountTypeId = 0;
			break;
		case "savings":
			accountTypeId = 1;
			break;
		case "loan":
			accountTypeId = 2;
			break;
		default:
			log.info("CHECKING, SAVINGS, LOAN -- are the only account types allowed.");
		}

		newAccountId = ApiHelper.createAccount(customerId, accountTypeId, accountId);

		log.info("The new account: " + newAccountId + " is created for the customer: " + customerId);

	}

	@Then("the user should see the newly created AccountID in the Accounts Overview table")
	public void the_user_should_see_the_newly_created_accountID_in_the_accounts_overview_table() {
		Assert.assertTrue(context.getAccountsOverviewPage().isAccountIdHyperlinkPresent(accountId),
				"The newly created account: " + accountId + " for the customer: " + customerId
						+ " is not shown in the Accounts Overview table");
	}

	@When("the user adds individual account balances in the Accounts Overview table")
	public void the_user_adds_individual_account_balances_in_the_accounts_overview_table() {
		sumOfAllBalancesInAccountsOverviewTable = context.getAccountsOverviewPage()
				.getSumOfAllBalancesInAccountsOverviewTable();
	}

	@And("read the total displayed in the Accounts Overview table")
	public void read_the_total_displayed_in_the_accounts_overview_table() {
		totalBalanceShownInAccountsOverviewTable = context.getAccountsOverviewPage()
				.getTotalBalanceInAccountsOverviewTable();
	}

	@Then("the sum of all balances in table should be equal to the total displayed in the Accounts Overview table")
	public void the_sum_of_all_balances_in_table_should_be_equal_to_the_total_displayed_in_the_accounts_overview_table() {
		log.info("Sum of balances = " + sumOfAllBalancesInAccountsOverviewTable);
		log.info("Total balance = " + totalBalanceShownInAccountsOverviewTable);
		Assert.assertTrue(sumOfAllBalancesInAccountsOverviewTable == totalBalanceShownInAccountsOverviewTable,
				"The total balance shown in the Accounts Overview table does NOT match the total sum of all the balances in the Accounts Overview table");
	}
}
