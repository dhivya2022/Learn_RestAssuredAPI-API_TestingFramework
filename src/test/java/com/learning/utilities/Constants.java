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

	public final String Stripe_Valid_API_Key = "sk_test_51I5nXuBrNpPA76Mp7VEBMUPMN6pcx7IBjXUgZk2Z8Tg9k3hacOJwE5bXYsITPtHHB6H6FKF5zjMhJV1byueGzDdy00P0WaC6QW";
	public final String Stripe_Invalid_API_Key = "dsakdjkahfjfd";

	/**
	 * PAYPAL API details
	 */
	public final String PayPal_BaseURI = "https://api.sandbox.paypal.com/";
	public final String PayPal_CustomerAPI_EndPoint = "/customers";

	public final String PayPal_Client_ID = "ATnRbBi8HuuisE37n1uDHNcuCSSPRHHMBcgKiuX2pFOlld0IwnpU9ViRqHRRQnMcpLfYIIjeNJRI2jtt";
	public final String PayPal_Secret = "ENJx5BDbQI1V0ryP4JJwbIMLNVDdRyLiArG1C6LuegZa2BDmUM6di56w_B3ZnpZ62Mqp8A2FLpxyqFPm";

}
