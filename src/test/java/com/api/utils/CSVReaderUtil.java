package com.api.utils;

import java.beans.beancontext.BeanContext;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {
	
	private static final Logger LOGGER = LogManager.getLogger(CSVReaderUtil.class);

	
	private CSVReaderUtil() {
		
	}
	
	public static <T> Iterator <T> loadCSV(String pathOFCSVFile,Class<T> bean)  {
		LOGGER.info("Loading the CSV file from the path {} ", pathOFCSVFile);
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOFCSVFile);
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr);

		// code to map the CSV to POJO class means UserBean
		LOGGER.info("Converting the csv bean class {} ", bean);

		CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(bean)
				.withIgnoreEmptyLine(true)
				.build();

		List<T> list = csvToBean.parse();
		return list.iterator();
		
		

	}

}

