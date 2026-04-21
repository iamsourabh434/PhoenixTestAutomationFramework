package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {
	
	public static void loadCSV(String pathOFCSVFile)  {

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOFCSVFile);
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr);

		// code to map the CSV to POJO

		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(UserBean.class)
				.withIgnoreEmptyLine(true)
				.build();

		List<UserBean> userlist = csvToBean.parse();
		System.out.println(userlist);
		

	}

}

