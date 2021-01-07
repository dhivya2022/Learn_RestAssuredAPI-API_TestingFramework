
package com.learning.utilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.json.JSONObject;

import com.learning.listeners.ExtentListeners;

public class TestUtil {

	public static boolean jsonHasKey(String json, String key) {
		JSONObject jsonObject = new JSONObject(json);
		ExtentListeners.testReport.get().info("Validating the presence of Key : " + key);
		return jsonObject.has(key);
	}

	public static String getJsonKeyValue(String json, String key) {
		JSONObject jsonObject = new JSONObject(json);
		ExtentListeners.testReport.get().info("Validating Value of Key : " + key);
		return jsonObject.get(key).toString();
	}

}
