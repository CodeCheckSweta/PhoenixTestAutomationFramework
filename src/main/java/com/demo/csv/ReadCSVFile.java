package com.demo.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {
	public static void main(String[] args) throws IOException, CsvException {
		// Code to read the CSV file in Java
		InputStream is = Thread.currentThread().getContextClassLoader().getSystemResourceAsStream("testData/LoginCreds.csv");
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr); // CSVReader constructor is going to acquire a reader

		List<String[]> dataList = csvReader.readAll(); // This is going to read the first line of the CSV file
		for (String[] dataArray : dataList) {
			for (String data : dataArray) {
				System.out.print(data + " ");
			}
			System.out.println("");
		}
	}
}
