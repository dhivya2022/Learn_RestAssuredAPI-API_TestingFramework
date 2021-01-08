package com.learning.testcases.stripe;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.learning.APIs.stripe.CreateCustomerAPI;
import com.learning.listeners.ExtentListeners;
import com.learning.setup.stripe.BaseTest_Stripe;
import com.learning.utilities.DataUtil;

import io.restassured.response.Response;

public class CreateCustomerTest extends BaseTest_Stripe {

	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void validateCreateCustomerAPI(Hashtable<String, String> data) {

		// validateCreateCustomerAPI - Sheet name

		Response response = CreateCustomerAPI.send_POST_RequestToCreateCustomerAPI_WithValid_APIKey(data);

		ExtentListeners.testReport.get().info(data.toString());

		response.prettyPrint();

		System.out.println("Status code: " + response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);
	}

	@Test
	public void skipTestIntentionally() {
		throw new SkipException("Skipping the test intentionally");
	}

//	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void invalidCreateCustomerAPI(Hashtable<String, String> data) {

		// invalidCreateCustomerAPI - Sheet name
		Response response = CreateCustomerAPI.send_POST_RequestToCreateCustomerAPI_WithInValid_APIKey(data);
		ExtentListeners.testReport.get().info(data.toString());
		response.prettyPrint();

		System.out.println("Status code: " + response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);
	}
}
