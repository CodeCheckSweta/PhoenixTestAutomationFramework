package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

public class ExcelReaderUtil {

	private ExcelReaderUtil() {
	}

	public static <T> Iterator<T> loadTestData(String fileName, String sheetName, Class<T> clazz) {
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(fileName);
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
        List<T> dataList = Poiji.fromExcel(sheet, clazz);
        return dataList.iterator();
	}

}
