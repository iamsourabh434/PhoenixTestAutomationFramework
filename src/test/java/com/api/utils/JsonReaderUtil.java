package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.request.model.UserCredentials;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.qameta.allure.Step;

public class JsonReaderUtil {
	
	private static final Logger LOGGER = LogManager.getLogger(JsonReaderUtil.class);
	
	@Step("Loading test data from JSON file")
	public static <T> Iterator<T> loadJSON(String filename , Class<T[]> clazz) {
		
		LOGGER.info("Reading the JSON from the file {}",filename);

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);

		ObjectMapper objectMapper = new ObjectMapper();
		T[] classArray;
		List<T> list = null;
		try {
			LOGGER.info("converting the json data to the bean class {}",clazz);
			classArray = objectMapper.readValue(is, clazz);
			list = Arrays.asList(classArray);

		} catch (IOException e) {
			LOGGER.error("Cannot read the json from the file {}",filename,e);
			e.printStackTrace();
		}

		return list.iterator();

	}
}
