package com.parabank.automation.factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.parabank.automation.utils.ConfigReader;

public class DriverFactory {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public static void initDriver() {
		String browser = ConfigReader.getProperty("browser").toLowerCase();

		switch (browser) {
		case "chrome":
			driver.set(new ChromeDriver());
			break;
		case "firefox":
			driver.set(new FirefoxDriver());
			break;
		case "edge":
			driver.set(new EdgeDriver());
			break;
		default:
			throw new RuntimeException("Browser '" + browser + "' not supported");
		}

		driver.get().manage().window().maximize();
		
		driver.get().manage().timeouts()
				.pageLoadTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("pageload.wait"))));
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}

}
