package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	private static Properties properties = new Properties();
	private static String path = "config/config.properties";
	private static String env;

	public ConfigManager() {
		// Private constructor to prevent instantiation of this class
		// This class is meant to be used as a utility class with static methods
	}

	static {
		env = System.getProperty("env","qa");
		env=env.toLowerCase().trim();
		switch (env) {
		case "dev" -> path = "config/config.dev.properties";

		case "qa" -> path = "config/config.qa.properties";

		case "uat" -> path = "config/config.uat.properties";

		default -> path = "config/config.qa.properties";
		
		}
		
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		if (input == null) {
			throw new RuntimeException("Cannot find file at the path" + path);
		}

		try {
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {

		return properties.getProperty(key);
	}
}
