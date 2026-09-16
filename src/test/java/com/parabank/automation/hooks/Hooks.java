package com.parabank.automation.hooks;

import org.apache.logging.log4j.Logger;

import com.parabank.automation.factory.DriverFactory;
import com.parabank.automation.utils.ConfigReader;
import com.parabank.automation.utils.LogUtil;
import com.parabank.automation.utils.ScreenshotUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.parabank.automation.context.TestContext;

public class Hooks {

	private static final Logger log = LogUtil.getLogger(Hooks.class);

	private final TestContext context;

	public Hooks(TestContext context) {
		this.context = context;
	}

	@Before(order = 0)
	public void setUp(Scenario scenario) {
		log.info("Starting scenario: {}", scenario.getName());
		DriverFactory.initDriver();
		DriverFactory.getDriver().get(ConfigReader.getProperty("url"));
	}

	@Before(order = 1, value = "@requiresLogin")
	public void loginValidUser(Scenario scenario) {
		log.info("Logging in with valid credentials for the scenario: {}", scenario.getName());
		context.getHomePage().loginUser(ConfigReader.getProperty("valid.username"),
				ConfigReader.getProperty("valid.password"));
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			log.error("Scenario FAILED: {}", scenario.getName());
			byte[] screenshot = ScreenshotUtil.captureScreenshot(DriverFactory.getDriver());
			scenario.attach(screenshot, "image/png", "Failed_" + scenario.getName());
		} else {
			log.info("Scenario PASSED: {}", scenario.getName());
		}

		DriverFactory.quitDriver();

	}

}
