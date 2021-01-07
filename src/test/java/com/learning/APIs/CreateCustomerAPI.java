package com.learning.APIs;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import com.learning.utilities.Constants;

import io.restassured.response.Response;

public class CreateCustomerAPI {

	public static Response send_POST_RequestToCreateCustomerAPI_WithValid_APIKey(Hashtable<String, String> data) {

		String name = data.get(Constants.Excel_name);
		String email = data.get(Constants.Excel_email);
		String description = data.get(Constants.Excel_description);

		Response response = given().auth().basic(Constants.Stripe_Valid_API_Key, "").formParam("name", name)
				.formParam("email", email).formParam("description", description)
				.formParam("preferred_locales[0]", "Hindi").formParam("preferred_locales[1]", "English")
				.formParam("address[line1]", "563").formParam("address[city]", "Hisar")
				.formParam("address[postal_code]", "125001").formParam("address[state]", "Haryana")
				.post(Constants.Stripe_CustomerAPI_EndPoint);

		return response;

		/*
		 * RestAssured.baseURI = "https://api.stripe.com"; RestAssured.basePath = "/v1";
		 */

		/*
		 * Response response = given().auth().basic(Constants.Stripe_Valid_API_Key,
		 * "").formParam("name", "Shreya") .formParam("email",
		 * "shreya@gmail.com").formParam("description",
		 * "POST Request from Rest Assured API") .formParam("preferred_locales[0]",
		 * "Hindi").formParam("preferred_locales[1]", "English")
		 * .formParam("address[line1]", "563").formParam("address[city]", "Hisar")
		 * .formParam("address[postal_code]", "125001").formParam("address[state]",
		 * "Haryana") .post(Constants.Stripe_CustomerAPI_EndPoint);
		 */

		/*
		 * Response response = given().auth().basic(API_Testing_Base.VALID_API_KEY,
		 * "").formParam("name", "Shreya") .formParam("email",
		 * "shreya@gmail.com").formParam("description",
		 * "POST Request from Rest Assured API") .formParam("preferred_locales[0]",
		 * "Hindi").formParam("preferred_locales[1]", "English")
		 * .formParam("address[line1]", "563").formParam("address[city]", "Hisar")
		 * .formParam("address[postal_code]", "125001").formParam("address[state]",
		 * "Haryana") .post(API_Testing_Base.URL);
		 */

	}

	public static Response send_POST_RequestToCreateCustomerAPI_WithInValid_APIKey(Hashtable<String, String> data) {

		String name = data.get(Constants.Excel_name);
		String email = data.get(Constants.Excel_email);
		String description = data.get(Constants.Excel_description);

		Response response = given().auth().basic(Constants.Stripe_Invalid_API_Key, "").formParam("name", name)
				.formParam("email", email).formParam("description", description)
				.formParam("preferred_locales[0]", "Hindi").formParam("preferred_locales[1]", "English")
				.formParam("address[line1]", "563").formParam("address[city]", "Hisar")
				.formParam("address[postal_code]", "125001").formParam("address[state]", "Haryana")
				.post(Constants.Stripe_CustomerAPI_EndPoint);

		return response;

		/*
		 * Response response = given().auth().basic(Constants.Stripe_Invalid_API_Key,
		 * "").formParam("name", "Shreya") .formParam("email",
		 * "shreya@gmail.com").formParam("description",
		 * "POST Request from Rest Assured API") .formParam("preferred_locales[0]",
		 * "Hindi").formParam("preferred_locales[1]", "English")
		 * .formParam("address[line1]", "563").formParam("address[city]", "Hisar")
		 * .formParam("address[postal_code]", "125001").formParam("address[state]",
		 * "Haryana") .post(Constants.Stripe_CustomerAPI_EndPoint);
		 */

	}

}
