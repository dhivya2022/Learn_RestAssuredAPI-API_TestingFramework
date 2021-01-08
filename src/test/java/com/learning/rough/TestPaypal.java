package com.learning.rough;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.learning.pojo.Orders;
import com.learning.pojo.PurchaseUnits;
import com.learning.utilities.Constants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestPaypal {

	static String access_token;
	static String client_id = "ATnRbBi8HuuisE37n1uDHNcuCSSPRHHMBcgKiuX2pFOlld0IwnpU9ViRqHRRQnMcpLfYIIjeNJRI2jtt";
	static String secret = "ENJx5BDbQI1V0ryP4JJwbIMLNVDdRyLiArG1C6LuegZa2BDmUM6di56w_B3ZnpZ62Mqp8A2FLpxyqFPm";
	static String orderId;

	@Test(priority = 1)
	public void getAuthKey() {
		RestAssured.baseURI = Constants.PayPal_BaseURI;

		Response response = given().param("grant_type", "client_credentials").auth().preemptive()
				.basic(client_id, secret).post("v1/oauth2/token");

		response.prettyPrint();

		System.out.println("==============================================================");
		access_token = response.jsonPath().get("access_token");
		System.out.println("access_token: " + access_token);

	}

	@Test(priority = 2, dependsOnMethods = "getAuthKey")
	public void createOrder() {
		RestAssured.baseURI = Constants.PayPal_BaseURI;
		Orders order = get_JSON_Object();
		Response response = given().contentType(ContentType.JSON).auth().oauth2(access_token).body(order)
				.post("v2/checkout/orders");

		response.prettyPrint();
		Assert.assertEquals(response.jsonPath().get("status"), "CREATED");

		orderId = response.jsonPath().get("id").toString();

	}

	@Test(priority = 3, dependsOnMethods = { "getAuthKey", "createOrder" })
	public void getOrderDetails() {

		RestAssured.baseURI = Constants.PayPal_BaseURI;

		Response response = given().contentType(ContentType.JSON).auth().oauth2(access_token)
				.get("v2/checkout/orders/" + orderId);
		System.out.println("Get Order Detials---------------------------------------------------");
		response.prettyPrint();

		Assert.assertEquals(response.getStatusCode(), 200);

	}

	private Orders get_JSON_Object() {

		ArrayList<PurchaseUnits> listOfPurchaseUnits = new ArrayList<PurchaseUnits>();
		listOfPurchaseUnits.add(new PurchaseUnits("USD", "500.00"));
		Orders order = new Orders("CAPTURE", listOfPurchaseUnits);

		return order;
		/*
		 * String jsonBody = "{\r\n" + "  \"intent\": \"CAPTURE\",\r\n" +
		 * "  \"purchase_units\": [\r\n" + "    {\r\n" + "      \"amount\": {\r\n" +
		 * "        \"currency_code\": \"USD\",\r\n" +
		 * "        \"value\": \"100.00\"\r\n" + "      }\r\n" + "    }\r\n" + "  ]\r\n"
		 * + "}"; return jsonBody;
		 */
	}

}
