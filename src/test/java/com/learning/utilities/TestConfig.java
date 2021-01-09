package com.learning.utilities;

/**
 * Data for Sending EMail after execution
 */
public class TestConfig {

	public static String server = "smtp.gmail.com";
	/* public static String port = "465"; */
	public static String port = "587";

	public static String from = "abc@gmail.com";
	public static String password = "password";

	public static String[] to = { "abc@gmail.com","jkl@gmail.com","xyz@gmail.com" };

	public static String new_to = "def95@gmail.com";

	public static String subject = "Extent Project Report - API_TestingFramework";
	public static String messageBody = "TestMessage";

}
