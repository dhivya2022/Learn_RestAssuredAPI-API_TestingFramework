package com.learning.listeners;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.learning.utilities.Constants;
import com.learning.utilities.EmailAttachmentsSender;
import com.learning.utilities.MonitoringMail;
import com.learning.utilities.TestConfig;
import com.learning.utilities.TestUtil;

public class ExtentListeners implements ITestListener, ISuiteListener {

	static int count_passedTCs;
	static int count_skippedTCs;
	static int count_failedTCs;
	static int count_totalTCs;

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	private static ExtentReports extent = ExtentManager
			.createInstance(System.getProperty("user.dir") + "\\reports\\" + fileName);

	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	static String messageBody;

	public void onTestStart(ITestResult result) {
		count_totalTCs = count_totalTCs + 1;
		ExtentTest test = extent
				.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
		testReport.set(test);

	}

	public void onTestSuccess(ITestResult result) {
		count_passedTCs = count_passedTCs + 1;
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);

	}

	public void onTestFailure(ITestResult result) {
		count_failedTCs = count_failedTCs + 1;
		testReport.get().fail(result.getThrowable().getMessage().toString());

		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");

		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
		count_skippedTCs = count_skippedTCs + 1;
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}

	public void onStart(ISuite suite) {

	}

	public void onFinish(ISuite suite) {

		// Zipping reports folder and putting it under root directory
		zip(".\\reports");

		// sendEmail_Tutor();
		// sendEmail_WithAttachment_Rajat();
		// sendEmail_WithAttachmentsAndFormattedBodyText_Rajat();
		sendEmail_WithAttachmentsAndFormattedBodyText_ToManyUsersRajat();

	}

	// make zip of reports
	private void zip(String filepath) {
		try {
			File inFolder = new File(filepath);
			File outFolder = new File("Reports.zip");
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
			BufferedInputStream in = null;
			byte[] data = new byte[1000];
			String files[] = inFolder.list();
			for (int i = 0; i < files.length; i++) {
				in = new BufferedInputStream(new FileInputStream(inFolder.getPath() + "/" + files[i]), 1000);
				out.putNextEntry(new ZipEntry(files[i]));
				int count;
				while ((count = in.read(data, 0, 1000)) != -1) {
					out.write(data, 0, count);
				}
				out.closeEntry();
			}
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendEmail_WithAttachmentsAndFormattedBodyText_ToManyUsersRajat() {

		System.out.println("File name: " + fileName);

		// String messageBody = "Test Message Body";
		String messageBody = getTestCasesCountInFormat();

		String attachmentFile_ExtentReport = ".\\reports\\" + fileName;
		String attachmentFile_EMailableReport = Constants.Emailable_Report;

		System.out.println(messageBody);

		try {
			EmailAttachmentsSender.sendEmailWithAttachments(TestConfig.server, TestConfig.port, TestConfig.from,
					TestConfig.password, TestConfig.to, TestConfig.subject, messageBody, attachmentFile_ExtentReport,
					attachmentFile_EMailableReport);

			System.out.println("Email sent successfully.");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	private void sendEmail_WithAttachmentsAndFormattedBodyText_Rajat() {

		MonitoringMail mail = new MonitoringMail();
		System.out.println("File name: " + fileName);

		// String messageBody = "Test Message Body";
		String messageBody = getTestCasesCountInFormat();

		// String attachmentFile = ".\\test-output\\emailable-report.html;
		String attachmentFile = ".\\reports\\" + fileName;

		System.out.println(messageBody);

		mail.sendMailWithAttachmentAndFormattedBodyText(TestConfig.server, TestConfig.from, TestConfig.to,
				TestConfig.subject, messageBody, attachmentFile);
	}

	private void sendEmail_WithAttachment_Rajat() {

		MonitoringMail mail = new MonitoringMail();
		System.out.println("File name: " + fileName);

		String messageBody = "Test Message Body";
		// String messageBody = getTestCasesCountInFormat();

		// String attachmentFile = ".\\test-output\\emailable-report.html;
		String attachmentFile = ".\\reports\\" + fileName;

		System.out.println(messageBody);

		mail.sendMailWithAttachments(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody,
				attachmentFile);
	}

	private String getTestCasesCountInFormat() {
		System.out.println("count_totalTCs: " + count_totalTCs);
		System.out.println("count_passedTCs: " + count_passedTCs);
		System.out.println("count_failedTCs: " + count_failedTCs);
		System.out.println("count_skippedTCs: " + count_skippedTCs);

		String messageBodyInFormat = "<html>\r\n" + "\r\n" + " \r\n" + "\r\n"
				+ "        <body> \r\n<table class=\"container\" align=\"center\" style=\"padding-top:20px\">\r\n<tr align=\"center\"><td colspan=\"4\"><h2>"
				+ Constants.Project_Name + "</h2></td></tr>\r\n<tr><td>\r\n\r\n"
				+ "       <table style=\"background:#67c2ef;width:120px\" >\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
				+ count_totalTCs + "</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Total</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
				+ "               \r\n" + "                 <table style=\"background:#79c447;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
				+ count_passedTCs + "</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Passed</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
				+ "                <table style=\"background:#ff5454;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
				+ count_failedTCs + "</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Failed</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
				+ "                <td>\r\n" + "                <table style=\"background:#fabb3d;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
				+ count_skippedTCs + "</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Skipped</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
				+ "                </tr>\r\n" + "               \r\n" + "                \r\n"
				+ "            </table>\r\n" + "       \r\n" + "    </body>\r\n" + "</html>";

		return messageBodyInFormat;
	}

	private void sendEmail_Tutor() {
		try {
			System.out.println("IP address: " + InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		MonitoringMail mail = new MonitoringMail();
		String messageBody = null;
		System.out.println("File name: " + fileName);

		try {

			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/API_TestingFramework%20Using%20RestAssuredAPI_GitHub/Extent_5fReport/";

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println(messageBody);

		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
