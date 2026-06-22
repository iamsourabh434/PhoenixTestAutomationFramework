package com.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.utils.Demo;

public class demo {

	private static Logger logger = LogManager.getFormatterLogger(Demo.class);

	public static void main(String[] args) {
		logger.info("inside main method");
		int a = 10;
		logger.info("value of a{}",a);
		logger.info("value of a " + a);
		
		int b = 0;
		logger.warn("value of b{}",b);
		int result = a/b;
		logger.info("Result {}",result);

	}

}
