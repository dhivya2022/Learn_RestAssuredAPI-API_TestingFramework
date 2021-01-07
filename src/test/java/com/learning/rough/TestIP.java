package com.learning.rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestIP {

	private static final String ExtentReport = "Extent_Thu_Jan_07_17_40_04_IST_2021.html";

	public static void main(String[] args) {

		try {
			System.out.println("http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/API_TestingFramework%20Using%20RestAssuredAPI_GitHub/Extent_5fReport/"+ExtentReport);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
