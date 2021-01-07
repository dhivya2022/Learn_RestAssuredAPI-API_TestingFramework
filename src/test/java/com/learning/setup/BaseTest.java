package com.learning.setup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.learning.utilities.Constants;
import com.learning.utilities.ExcelReader;

import io.restassured.RestAssured;

public class BaseTest {

	private FileInputStream fis;
	public Properties config = new Properties();
//	public ExcelReader excel = new ExcelReader(".\\src\\test\\java\\com\\learning\\utilities\\ExcelReader.java");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");

	@BeforeSuite
	public void setup() {
		// RestAssured.baseURI = config.getProperty("stripe_baseURI");
		RestAssured.baseURI = Constants.Stripe_BaseURI;
		RestAssured.basePath = Constants.Stripe_BasePath;

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			config.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@AfterSuite
	public void tearDown() {

	}
}
