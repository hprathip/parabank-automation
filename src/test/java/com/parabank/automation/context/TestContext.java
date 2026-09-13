package com.parabank.automation.context;

import com.parabank.automation.factory.DriverFactory;
import com.parabank.automation.pages.AccountServicesPage;
import com.parabank.automation.pages.AccountsOverviewPage;
import com.parabank.automation.pages.HomePage;
import com.parabank.automation.pages.RegistrationPage;
import com.parabank.automation.pages.SideNavPage;

public class TestContext {

	private HomePage homePage;
	private AccountsOverviewPage accountsOverviewPage;
	private RegistrationPage registrationPage;
	private AccountServicesPage accountServicesPage;
	
	private SideNavPage sideNavPage;

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

}
