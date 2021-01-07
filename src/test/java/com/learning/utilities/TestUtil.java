
package com.learning.utilities;

import org.json.JSONObject;

import com.learning.listeners.ExtentListeners;

public class TestUtil {

	public static boolean jsonHasKey(String json, String key) {
		JSONObject jsonObject = new JSONObject(json);
		ExtentListeners.testReport.get().info("Validating the presence of Key : "+key);
		return jsonObject.has(key);
	}

	public static String getJsonKeyValue(String json, String key) {
		JSONObject jsonObject = new JSONObject(json);
		ExtentListeners.testReport.get().info("Validating Value of Key : "+key);
		return jsonObject.get(key).toString();
	}
}
