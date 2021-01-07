package com.learning.utilities;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.learning.setup.BaseTest;

public class DataUtil extends BaseTest {

	/**
	 * (name = "dp", parallel = true)
	 * 
	 * name = "dp" - name of the DataProvider
	 * 
	 * parallel = true -> Data will run parallely
	 * 
	 */
	@DataProvider(name = "dp", parallel = true)
	public Object[][] getData(Method method) {

		String sheetName = method.getName();
		System.out.println("Sheet Name: " + sheetName);
		// String sheetName = "validateCreateCustomerAPI";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];

		Hashtable<String, String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				// data[0][0]
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}
		}
		return data;
	}

	public static void main(String[] args) {

		// String sheetName = m.getName();

		String sheetName = "validateCreateCustomerAPI";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];

		Hashtable<String, String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				// data[0][0]
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
				System.out.println(data[rowNum - 2][0]);
			}
		}

		// return data;
	}

}
