package HelperClass;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import Utility.Common;

public class TestBase {

	@BeforeSuite
	public void reportSetUp() throws Exception 
	{
		try{
			SmartLogger.InitiateSmartLogging();
			SmartLogger.ClearExecutionLogsAndReports();
			SmartReporter.ExtentReportHTML();
			Common.CreateReportingFolders();
		   }
		catch (Exception e1)
		   {
			e1.printStackTrace();
		   }
	}

	@AfterSuite
	public void reportFlush() throws Exception {
		try {
			SmartReporter.ExtentReportFlush();
			SmartLogger.MoveLogsToDestination();
			SmartReporter.MoveExtentReportToDestination();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
