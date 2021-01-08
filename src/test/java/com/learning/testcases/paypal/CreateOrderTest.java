package com.learning.testcases.paypal;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.learning.APIs.paypal.OrderAPI;
import com.learning.setup.paypal.BaseTest_Paypal;

import io.restassured.response.Response;

public class CreateOrderTest extends BaseTest_Paypal {

	@Test
	public void createOrder() {
		String accessToken = OrderAPI.getAccessToken();
		Response response = OrderAPI.createOrder(accessToken);
		response.prettyPrint();
		Assert.assertEquals(response.jsonPath().get("status"), "CREATED");

	}

}
