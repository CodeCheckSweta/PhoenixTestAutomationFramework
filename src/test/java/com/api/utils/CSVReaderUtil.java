package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {
	
	private CSVReaderUtil() {
		// private constructor to prevent instantiation
	}
	
	public static <T> Iterator<T> loadCSV(String pathOfCSVFile, Class<T> userBean) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);	//"testData/LoginCreds.csv"
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr);
		CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(csvReader)
				.withType(userBean)
				.withIgnoreEmptyLine(true)
				.build();
		
		List<T> list= csvToBean.parse();
		return list.iterator();
	
	}
}
