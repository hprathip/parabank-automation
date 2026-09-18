package com.parabank.automation.context;

import com.parabank.automation.factory.DriverFactory;
import com.parabank.automation.pages.AccountActivityPage;
import com.parabank.automation.pages.AccountServicesPage;
import com.parabank.automation.pages.AccountsOverviewPage;
import com.parabank.automation.pages.HomePage;
import com.parabank.automation.pages.OpenNewAccountPage;
import com.parabank.automation.pages.RegistrationPage;
import com.parabank.automation.pages.SideNavPage;

public class TestContext {

	private HomePage homePage;
	private AccountsOverviewPage accountsOverviewPage;
	private RegistrationPage registrationPage;
	private AccountServicesPage accountServicesPage;
	private AccountActivityPage accountActivityPage;
	private OpenNewAccountPage openNewAccountPage;

	private SideNavPage sideNavPage;

	private int customerId;
	private int existingAccountId;
	private int fromAccountId;
	private int newAccountIdCreatedViaAPI;
	private int newAccountIdCreatedViaUI;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public SideNavPage getSideNavPage() {
		if (sideNavPage == null) {
			sideNavPage = new SideNavPage(DriverFactory.getDriver());
		}
		return sideNavPage;
	}

	public HomePage getHomePage() {
		if (homePage == null) {
			homePage = new HomePage(DriverFactory.getDriver());
		}

		return homePage;
	}

	public AccountsOverviewPage getAccountsOverviewPage() {
		if (accountsOverviewPage == null) {
			accountsOverviewPage = new AccountsOverviewPage(DriverFactory.getDriver());
		}

		return accountsOverviewPage;
	}

	public RegistrationPage getRegistrationPage() {
		if (registrationPage == null) {
			registrationPage = new RegistrationPage(DriverFactory.getDriver());
		}

		return registrationPage;
	}

	public AccountServicesPage getAccountServicesPage() {
		if (accountServicesPage == null) {
			accountServicesPage = new AccountServicesPage(DriverFactory.getDriver());
		}

		return accountServicesPage;
	}

	public AccountActivityPage getAccountActivityPage() {
		if (accountActivityPage == null) {
			accountActivityPage = new AccountActivityPage(DriverFactory.getDriver());
		}

		return accountActivityPage;
	}

	public OpenNewAccountPage getOpenNewAccountPage() {
		if (openNewAccountPage == null) {
			openNewAccountPage = new OpenNewAccountPage(DriverFactory.getDriver());
		}
		return openNewAccountPage;
	}

	public int getExistingAccountId() {
		return existingAccountId;
	}

	public void setExistingAccountId(int existingAccountId) {
		this.existingAccountId = existingAccountId;
	}

	public int getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(int fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public int getNewAccountIdCreatedViaAPI() {
		return newAccountIdCreatedViaAPI;
	}

	public void setNewAccountIdCreatedViaAPI(int newAccountIdCreatedViaAPI) {
		this.newAccountIdCreatedViaAPI = newAccountIdCreatedViaAPI;
	}

	public int getNewAccountIdCreatedViaUI() {
		return newAccountIdCreatedViaUI;
	}

	public void setNewAccountIdCreatedViaUI(int newAccountIdCreatedViaUI) {
		this.newAccountIdCreatedViaUI = newAccountIdCreatedViaUI;
	}

}
