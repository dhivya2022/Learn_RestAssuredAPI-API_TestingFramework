package com.learning.testcases;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.learning.APIs.DeleteCustomerAPI;
import com.learning.listeners.ExtentListeners;
import com.learning.setup.BaseTest;
import com.learning.utilities.Constants;
import com.learning.utilities.DataUtil;
import com.learning.utilities.TestUtil;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeleteCustomerTest extends BaseTest {

	/**
	 * 1. This TC is dependent on test data coming from excel sheet
	 */
	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void validateDeleteCustomerAPI(Hashtable<String, String> data) {

		// validateDeleteCustomerAPI - Sheet name

		Response response = DeleteCustomerAPI.send_Delete_RequestToDeleteCustomerAPI_WithValid_APIKeyAndValidID(data);
		ExtentListeners.testReport.get().info(data.toString());

		response.prettyPrint();
		System.out.println("-----------------------------");

		// doValidationUsingJSONPath(data, response);
		doValidationUsingJSONObject(data, response);
		doValidationForResponseCode(response);
	}

	private void doValidationForResponseCode(Response response) {
		System.out.println("-----------------------------");
		System.out.println("Status code: " + response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);
	}

	private void doValidationUsingJSONObject(Hashtable<String, String> data, Response response) {
		System.out.println("Validate (using jsonObject) that service reponse has \"id\" as a field");

		boolean result_For_Field_ID = TestUtil.jsonHasKey(response.asString(), "id");
		Assert.assertTrue(result_For_Field_ID, "Response doesn't have ID as a field");

		String expectedID_FromExcelSheet = data.get(Constants.Excel_customer_id);
		String actual_id = TestUtil.getJsonKeyValue(response.asString(), "id");
		Assert.assertEquals(actual_id, expectedID_FromExcelSheet, "Customer ID not matching");
		System.out.println("ID: " + TestUtil.getJsonKeyValue(response.asString(), "id"));
		System.out.println("Object key value: " + TestUtil.getJsonKeyValue(response.asString(), "object"));
		System.out.println("deleted key value: " + TestUtil.getJsonKeyValue(response.asString(), "deleted"));

		/*
		 * JSONObject jsonObject = new JSONObject(response.asString()); boolean result =
		 * jsonObject.has("id"); System.out.println("Response has field \"id\": " +
		 * result); Assert.assertTrue(result, "Response doesn't have ID as a field");
		 * 
		 * String actual_id_JsonObject = jsonObject.get("id").toString();
		 * Assert.assertEquals(actual_id_JsonObject, expectedID_FromExcelSheet,
		 * "Customer ID not matching");
		 */
	}

	private void doValidationUsingJSONPath(Hashtable<String, String> data, Response response) {

		System.out.println(
				"VAlidate (using jsonPath) that Customer ID from Delete Customer API is same as expected in Excel sheet");
		JsonPath json = response.jsonPath();
		String actual_ID = json.get("id").toString();
		System.out.println("Customer deleted having ID: " + actual_ID);
		String expectedID_FromExcelSheet = data.get(Constants.Excel_customer_id);
		Assert.assertEquals(actual_ID, expectedID_FromExcelSheet, "Customer ID not matching");
		System.out.println("-------------------------------------------------");
	}

	/**
	 * 1. This TC is creating a customer using POST request to createCustomer API
	 * 
	 * 2. then, Deleting the same customer using DELETE request to deleteCustomer
	 * API
	 */
//	@Test
	public void validatePostAndDeleteCustomerAPI() {

		Response response_POST = sendPostRequest_CreateCustomerAPI();

		String customer_ID = getID_OfNewCreatedCustomer(response_POST);

		Response response_Delete = sendDeleteRequest_DeleteCustomerAPI(customer_ID);

		System.out.println("Status code: " + response_Delete.statusCode());
		Assert.assertEquals(response_Delete.statusCode(), 200);
	}

	private Response sendDeleteRequest_DeleteCustomerAPI(String customer_ID) {
		Response response = given().auth().basic(Constants.Stripe_Valid_API_Key, "")
				.delete(Constants.Stripe_CustomerAPI_EndPoint + "/" + customer_ID);
		System.out.println("===========================Delete Customer API response==========================");
		response.prettyPrint();
		return response;
	}

	private String getID_OfNewCreatedCustomer(Response response_POST) {
		JsonPath json = response_POST.jsonPath();
		System.out.println("==========================================");

		String customer_id = json.get("id");
		System.out.println("customer_id: " + customer_id);
		return customer_id;

	}

	private Response sendPostRequest_CreateCustomerAPI() {
		Response response = given().auth().basic(Constants.Stripe_Valid_API_Key, "").formParam("name", "New User")
				.formParam("description", "User to be deleted just after creation")
				.formParam("preferred_locales[0]", "Hindi").formParam("preferred_locales[1]", "English")
				.formParam("address[line1]", "563").formParam("address[city]", "Hisar")
				.formParam("address[postal_code]", "125001").formParam("address[state]", "Haryana")
				.post(Constants.Stripe_CustomerAPI_EndPoint);
		System.out.println("===========================Create Customer API response==========================");
		response.prettyPrint();
		return response;
	}

}
