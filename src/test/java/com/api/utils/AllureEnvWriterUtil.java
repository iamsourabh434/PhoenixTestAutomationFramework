package com.api.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AllureEnvWriterUtil {
	
	private static final Logger LOGGER= LogManager.getLogger(AllureEnvWriterUtil.class);

	public static void createEnvironmentPropertiesFile()  {
		
		String folderPath ="target/allure-results";
		File file = new File(folderPath);
		file.mkdir();
		
		Properties prop = new Properties();
		prop.setProperty("Name", "Sourabh");
		prop.setProperty("Env", ConfigManager.env);
		prop.setProperty("Base_URI", ConfigManager.getProperty("BASE_URI"));
		prop.setProperty("Operating_System_Name",(System.getProperty("os.name")));
		prop.setProperty("Operating_System_Version",(System.getProperty("os.version")));
		prop.setProperty("Java_Version", System.getProperty("java.version"));
		prop.setProperty("Project_Name", "Phoenix Test Automation Framework");
		
		FileWriter fw;
		try {
			fw = new FileWriter(folderPath + "/environment.properties");
			prop.store(fw, "This is my proprties file");
			LOGGER.info("Created the environment.properties file at {} ",folderPath);
		} catch (IOException e) {
			LOGGER.error("Unable to create the environment.properties file",e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

}
