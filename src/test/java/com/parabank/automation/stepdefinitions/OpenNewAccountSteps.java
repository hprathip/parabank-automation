package com.parabank.automation.stepdefinitions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.parabank.automation.context.TestContext;
import com.parabank.automation.pages.OpenNewAccountPage;
import com.parabank.automation.utils.LogUtil;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OpenNewAccountSteps {

	private Logger log = LogUtil.getLogger(OpenNewAccountSteps.class);

	private TestContext context;

	public OpenNewAccountSteps(TestContext context) {
		this.context = context;
	}

	@And("the user selects account type as {string} from the dropdown")
	public void the_user_selects_account_type_from_the_dropdown(String accountType) {
		context.getOpenNewAccountPage().selectAccountTypeInDropDown(accountType);
	}

	@And("the user selects account id from which the funds should be transferred")
	public void the_user_selects_account_id_from_which_the_funds_should_be_transferred() {
		context.getOpenNewAccountPage()
				.selectFromAccountIdInDropDown(String.valueOf(context.getNewAccountIdCreatedViaAPI()));
	}

	@Then("the user should see the success message with newly opened Account's ID")
	public void the_user_should_see_the_success_message_with_new_account_number() {
		Assert.assertTrue(context.getOpenNewAccountPage().isAccountOpenedHeadingDisplayed(),
				"Account Opened! heading is not displayed");

		Assert.assertTrue(context.getOpenNewAccountPage().isAccountOpenedMessageDisplayed(),
				"Account is now open confirmation message is not displayed");

		Assert.assertTrue(context.getOpenNewAccountPage().isNewOpenedAccountIdDisplayed(),
				"The newly opened AccountId is not displayed");
	}

	@And("the user notes the new accountId")
	public void the_user_notes_the_new_accountId() {
		context.setNewAccountIdCreatedViaUI(Integer.parseInt(context.getOpenNewAccountPage().getNewOpenedAccountId()));
	}

	@Then("the user should see the newly created AccountID via UI in the Accounts Overview table")
	public void the_user_should_see_the_newly_created_accountID_in_the_accounts_overview_table() {
		Assert.assertTrue(
				context.getAccountsOverviewPage().isAccountIdHyperlinkPresent(context.getNewAccountIdCreatedViaUI()),
				"The newly created account: " + context.getNewAccountIdCreatedViaUI() + " for the customer: "
						+ context.getCustomerId() + " is not shown in the Accounts Overview table");
	}

	@When("the user identifies the account with negative balance from Accounts Overview table")
	public void the_user_identifies_the_account_with_negative_balancefrom_accounts_overview_table() {
		context.setFromAccountId(context.getAccountsOverviewPage().getAccountIdWithNegativeBalance());
	}

	@And("the user selects noted negative balance account id from which the funds should be transferred")
	public void the_user_selects_noted_negative_balance_account_id_from_which_the_funds_should_be_transferred() {
		context.getOpenNewAccountPage().selectFromAccountIdInDropDown(String.valueOf(context.getFromAccountId()));
	}

}
