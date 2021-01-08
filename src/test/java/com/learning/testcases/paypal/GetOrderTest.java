package com.learning.testcases.paypal;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.learning.APIs.paypal.OrderAPI;
import com.learning.setup.paypal.BaseTest_Paypal;

import io.restassured.response.Response;

/**
 * Do not run this GetOrderTest individually
 * 
 * Run after CreateCustomerTest as we are setting the order value inside this
 */
public class GetOrderTest extends BaseTest_Paypal {

	@Test
	public void getOrder() {
		String accessToken = OrderAPI.getAccessToken();
		Response response = OrderAPI.getOrderDetails(accessToken);
		response.prettyPrint();
		Assert.assertEquals(response.jsonPath().get("status"), "CREATED");

	}

}
