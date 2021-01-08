package com.learning.APIs.paypal;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import com.learning.pojo.Orders;
import com.learning.pojo.PurchaseUnits;
import com.learning.utilities.Constants;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OrderAPI {

	// static String access_token;
	final static String client_id = Constants.PayPal_Client_ID;
	final static String secret = Constants.PayPal_Secret;
	static String orderId;

	public static String getAccessToken() {

		String access_token = given().param("grant_type", "client_credentials").auth().preemptive()
				.basic(client_id, secret).post("v1/oauth2/token").jsonPath().get("access_token").toString();
		System.out.println("access_token: " + access_token);
		return access_token;
	}

	public static Response createOrder(String access_token_FromTC) {

		ArrayList<PurchaseUnits> listOfPurchaseUnits = new ArrayList<PurchaseUnits>();
		listOfPurchaseUnits.add(new PurchaseUnits("USD", "500.00"));
		Orders order = new Orders("CAPTURE", listOfPurchaseUnits);

		Response response = given().contentType(ContentType.JSON).auth().oauth2(access_token_FromTC).body(order)
				.post("v2/checkout/orders");

		// Using this orderId in getOrderDetails()
		orderId = response.jsonPath().get("id").toString();
		return response;
	}

	public static Response getOrderDetails(String access_token_FromTC) {
		Response response = given().contentType(ContentType.JSON).auth().oauth2(access_token_FromTC)
				.get("v2/checkout/orders/" + orderId);

		return response;
	}

}
