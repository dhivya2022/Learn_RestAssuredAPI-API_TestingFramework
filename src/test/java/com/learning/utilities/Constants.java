package com.learning.utilities;

public interface Constants {

	/**
	 * Variables used while sending the REport via Java Mail API
	 */
	public final String Project_Name = "RESTful webservices Automation Suite Report using Rest Assured";
	public final String Emailable_Report = ".\\target\\surefire-reports\\emailable-report.html";

	/**
	 * Files location in the Project
	 */
	public final String Properties_Config = ".\\src\\test\\resources\\properties\\config.properties";
	public final String REPORTS_Folder = ".\\reports\\";
	public final String ExcelSheet = ".\\src\\test\\resources\\excel\\testdata.xlsx";

	/**
	 * Column names in the Excel sheet
	 */

	// CreateCustomer
	public final String Excel_name = "name";
	public final String Excel_email = "email";
	public final String Excel_description = "description";
	// DeleteCustomer
	public final String Excel_customer_id = "customer_id";

	/**
	 * STRIPE API details
	 */

	public final String Stripe_BaseURI = "https://api.stripe.com";
	public final String Stripe_BasePath = "/v1";
	public final String Stripe_CustomerAPI_EndPoint = "/customers";

	public final String Stripe_Valid_API_Key = "Use valid API key";
	public final String Stripe_Invalid_API_Key = "dsakdjkahfjfd";

	/**
	 * PAYPAL API details
	 */
	public final String PayPal_BaseURI = "https://api.sandbox.paypal.com/";
	public final String PayPal_CustomerAPI_EndPoint = "/customers";

	public final String PayPal_Client_ID = "Use Client ID";
	public final String PayPal_Secret = "Use Secret Key";

}
