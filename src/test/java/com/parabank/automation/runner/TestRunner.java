package com.parabank.automation.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = { "com.parabank.automation.hooks",
		"com.parabank.automation.stepdefinitions" }, plugin = { "pretty",
				"html:target/cucumber-reports/cucumber.html" }, monochrome = true, dryRun = false)

public class TestRunner extends AbstractTestNGCucumberTests {

}
 