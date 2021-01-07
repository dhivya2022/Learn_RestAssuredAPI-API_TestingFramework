package com.learning.APIs;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import com.learning.utilities.Constants;

import io.restassured.response.Response;

public class DeleteCustomerAPI {

	public static Response send_Delete_RequestToDeleteCustomerAPI_WithValid_APIKeyAndValidID(
			Hashtable<String, String> data) {

		String customer_id = data.get(Constants.Excel_customer_id);

		Response response = given().auth().basic(Constants.Stripe_Valid_API_Key, "")
				.delete(Constants.Stripe_CustomerAPI_EndPoint + "/" + customer_id);

		return response;
	}

}
