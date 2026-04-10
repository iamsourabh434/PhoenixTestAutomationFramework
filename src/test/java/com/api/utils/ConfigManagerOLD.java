package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOLD {

	private static Properties prop = new Properties();

	static {
		// operation of loading the property file in the memory
		// static block executed only once during class loading time
		File configFile = new File(
				System.getProperty("user.dir") +File.separator +"src"+File.separator +"test"+File.separator +"resources"+File.separator+ "config"+File.separator +"config.properties");
		FileReader fileRead = null;

		try {
			fileRead = new FileReader(configFile);
			prop.load(fileRead);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		
		return prop.getProperty(key);

	}

}
