package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UserCredentials;
import com.dataproviders.api.bean.UserBean;
import com.poiji.bind.Poiji;

public class ExcelReaderUtil {
	
	private static final Logger LOGGER = LogManager.getLogger(ExcelReaderUtil.class);
	
	private ExcelReaderUtil() {
		
	}

	public static <T> Iterator<T> loadTestData(String xlsxFile ,String sheetName, Class<T> clazz)  {
		// apache poi-oox
		LOGGER.info("Reading the test data from .xlsx file {} and sheet name is {}", xlsxFile,sheetName);
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(xlsxFile);

		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			LOGGER.error("cannot read the Excel {}", xlsxFile);
			e.printStackTrace();
		}
		XSSFSheet mySheet = myWorkBook.getSheet(sheetName);//LoginTestData
		
		LOGGER.info("covverting the XSSFSheet {} to POJO class type {}",sheetName,clazz);
		List<T> dataList = Poiji.fromExcel(mySheet, clazz);
		return dataList.iterator();

	}
}
