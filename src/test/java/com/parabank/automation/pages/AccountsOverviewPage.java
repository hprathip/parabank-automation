package com.parabank.automation.pages;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.parabank.automation.utils.ConfigReader;
import com.parabank.automation.utils.ElementUtil;
import com.parabank.automation.utils.LogUtil;

public class AccountsOverviewPage {

	private WebDriver driver;

	private final Logger log = LogUtil.getLogger(AccountsOverviewPage.class);

	private static final int WAIT_FOR_ELEMENT_VISIBLE = 5;
	private static final int WAIT_FOR_ELEMENT_CLICKABLE = 5;
	private String accountsOverview_url = ConfigReader.getProperty("accounts.overview.url");

	private By accountsOverviewHeader = By.xpath("//div[@id='showOverview']/h1[@class='title']");
	private By accountsOverviewTable = By.id("accountTable");
	private By columnsForAccountsOverviewTable = By.xpath("//table[@id='accountTable']/thead/tr/th");
	private By accountColumnTH = By.xpath("//table[@id='accountTable']//th[text()='Account']");
	private By balanceColumnTH = By.xpath("//table[@id='accountTable']//th[text()='Balance*']");
	private By availableAmountColumnTH = By.xpath("//table[@id='accountTable']//th[text()='Available Amount']");

	private String accountsOverviewTableColumnBasePath = "//table[@id='accountTable']//th";
	private By accountsOverviewTableRows = By.xpath("//table[@id='accountTable']/tbody/tr");
	private String accountsOverviewTableRowWiseValuesBasePath = "//table[@id='accountTable']/tbody/tr";
	private String accountIdHyperlinkBasePath = "//a[text()='";
	private String accountIdRespectiveBalanceBasePath = "/parent::td/following-sibling::td[1]"; // //a[text()='12345']/parent::td/following-sibling::td[1]
	private String accountIdRespectiveAvailableAmountBasePath = "/parent::td/following-sibling::td[2]"; // //a[text()='12345']/parent::td/following-sibling::td[2]

	private By listOfBalancesInAccountsOverviewTable = By.xpath("//table[@id='accountTable']/tbody/tr/td[2]");

	public AccountsOverviewPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getAccountOverviewHeader() {
		return ElementUtil.getText(driver, accountsOverviewHeader, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public String getBalanceFromAccountOverviewTableForAccountId(int accountId) {
		return ElementUtil.getText(driver,
				By.xpath(accountIdHyperlinkBasePath + accountId + "']" + accountIdRespectiveBalanceBasePath),
				WAIT_FOR_ELEMENT_VISIBLE);
	}

	public String getAvailableAmountFromAccountOverviewTableForAccountId(int accountId) {
		return ElementUtil.getText(driver,
				By.xpath(accountIdHyperlinkBasePath + accountId + "']" + accountIdRespectiveAvailableAmountBasePath),
				WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void navigateToAccountsOverviewUrl() {
		driver.get(accountsOverview_url);
	}

	public boolean isAccountsOverviewTableDisplayed() {
		return ElementUtil.isElementDisplayed(driver, accountsOverviewTable, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public boolean isAccountColumnDisplayed() {
		return ElementUtil.isElementDisplayed(driver, accountColumnTH, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public boolean isBalanceColumnDisplayed() {
		return ElementUtil.isElementDisplayed(driver, balanceColumnTH, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public boolean isAvailableAmountColumnDisplayed() {
		return ElementUtil.isElementDisplayed(driver, availableAmountColumnTH, WAIT_FOR_ELEMENT_VISIBLE);
	}

	public int getTotalColumnsForAccountsOverviewTable() {
		return ElementUtil
				.getAllElementsIdentifiedByLocator(driver, columnsForAccountsOverviewTable, WAIT_FOR_ELEMENT_VISIBLE)
				.size();
	}

	public boolean isAccountIdHyperlinkPresent(int accountId) {
		return ElementUtil.isElementDisplayed(driver, By.xpath(accountIdHyperlinkBasePath + accountId + "']"),
				WAIT_FOR_ELEMENT_VISIBLE);
	}

	public void clickAccountIdHyperlink(int accountId) {
		ElementUtil.clickElement(driver, By.xpath(accountIdHyperlinkBasePath + accountId + "']"),
				WAIT_FOR_ELEMENT_CLICKABLE);
	}

	public String getAccountsOverviewTableColumnName(int columnNumber) {
		String columnName;

		columnName = ElementUtil.getText(driver,
				By.xpath(accountsOverviewTableColumnBasePath + "[" + columnNumber + "]"), WAIT_FOR_ELEMENT_VISIBLE);

		return columnName;
	}

	public int getNumberOfAccountsInTheAccountsOverviewPage() {
		return ElementUtil
				.getAllElementsIdentifiedByLocator(driver, accountsOverviewTableRows, WAIT_FOR_ELEMENT_VISIBLE).size();
	}

	public boolean verifyAccountsOverviewTableRowsData() {
		int totalRows = getNumberOfAccountsInTheAccountsOverviewPage();

		for (int i = 1; i <= totalRows; i++) {
			String rowLocator = accountsOverviewTableRowWiseValuesBasePath + "[" + i + "]/td";
			if (!verifyAccountsOverviewTableEachRowForNonEmptyValues(i, rowLocator)) {
				log.info("The value in row " + i + " is empty");
				return false;
			}
		}

		return true;

	}

	public boolean verifyAccountsOverviewTableEachRowForNonEmptyValues(int rowNumber, String rowLocator) {

		int totalColumns = getTotalColumnsForAccountsOverviewTable();

		for (int i = 1; i <= totalColumns; i++) {
			By valueLocator = By
					.xpath(accountsOverviewTableRowWiseValuesBasePath + "[" + rowNumber + "]/td[" + i + "]");
			if (ElementUtil.getText(driver, valueLocator, WAIT_FOR_ELEMENT_VISIBLE).isEmpty()) {
				log.info("The value in row " + rowNumber + ", column " + i + " is empty");
				return false;
			}
		}

		return true;
	}

	public double getSumOfAllBalancesInAccountsOverviewTable() {
		double sum = 0;

		List<WebElement> balances = ElementUtil.getAllElementsIdentifiedByLocator(driver,
				listOfBalancesInAccountsOverviewTable, WAIT_FOR_ELEMENT_VISIBLE);

		for (int i = 0; i < balances.size() - 1; i++) {
			String actualBalance = balances.get(i).getText();
			int dollar_index = actualBalance.indexOf('$');
			ArrayList<String> bal = new ArrayList<String>();
			bal.add(actualBalance.substring(0, dollar_index));
			bal.add(actualBalance.substring(dollar_index + 1, actualBalance.length()));

			StringBuilder finalBal = new StringBuilder();
			for (String b : bal) {

				finalBal.append(b);
			}

			sum += Double.parseDouble(finalBal.toString());

		}
//		log.info("sum:" + BigDecimal.valueOf(sum).setScale(2, RoundingMode.HALF_UP));

		return BigDecimal.valueOf(sum).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	public double getTotalBalanceInAccountsOverviewTable() {

		List<WebElement> balances = ElementUtil.getAllElementsIdentifiedByLocator(driver,
				listOfBalancesInAccountsOverviewTable, WAIT_FOR_ELEMENT_VISIBLE);
		String actualBalance = balances.get(balances.size() - 1).getText();
		int dollar_index = actualBalance.indexOf('$');
		ArrayList<String> bal = new ArrayList<String>();
		bal.add(actualBalance.substring(0, dollar_index));
		bal.add(actualBalance.substring(dollar_index + 1, actualBalance.length()));

		StringBuilder finalBal = new StringBuilder();
		for (String b : bal) {

			finalBal.append(b);
		}

		return BigDecimal.valueOf(Double.parseDouble(finalBal.toString())).setScale(2, RoundingMode.HALF_UP)
				.doubleValue();
	}

}
