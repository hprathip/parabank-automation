package com.parabank.automation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop;

	static {
		try {
			InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
			if (input == null) {
				throw new RuntimeException("config.properties not found in classpath (src/test/resources)");
			}
			prop = new Properties();
			prop.load(input);
		} catch (IOException e) {
			throw new RuntimeException("Could't read the config.properties file: " + e);
		}
	}

	public static String getProperty(String key) {
		String value = prop.getProperty(key);

		if (value == null) {
			throw new RuntimeException("The property: " + key + " doesn't exist in the config.properties file");
		}
		return value;

	}

}
