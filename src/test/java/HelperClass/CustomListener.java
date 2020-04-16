package HelperClass; 

import java.util.ArrayList;
import java.util.List;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestNG;
import Utility.Common;
import Utility.UtilitiesWebDriver;

public class CustomListener implements ITestListener{
	
	//private static int counter = 0;
	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {

	}

	public void onTestFailure(ITestResult result){
		String[] testID=null;
		String description = result.getMethod().getDescription();
		testID = description.trim().split(":");
		try {
			String sPath = UtilitiesWebDriver.TakeScreenshot(testID[0]);
			SmartLogger.PrintFailedTestCaseAndAttachScreenshot("FAILURE SCREENSHOT: ", sPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			
	}

	public void onStart(ITestContext context) {

		try {
			SmartLogger.InitiateSmartLogging();
			SmartLogger.ClearExecutionLogsAndReports();
			Common.CreateReportingFolders();	
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void onFinish(ITestContext context)
	{
//		if (counter == 0) 
//		{
//			counter++;
//			RetryFailedTestCases();
//		}
		
	}
	
	public static void RetryFailedTestCases()
	{
		TestNG runner = new TestNG();
		List<String> listOfFailedCases = new ArrayList<String>();
		listOfFailedCases.add(System.getProperty("user.dir") + "\\test-output\\testng-failed.xml");
		runner.setTestSuites(listOfFailedCases);
		runner.run();
	}
}
