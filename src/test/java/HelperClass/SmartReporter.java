package HelperClass;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import Utility.Common;

public class SmartReporter {

	public static ExtentHtmlReporter htmlReport=null; 
	public static ExtentReports extent=null;
	public static ExtentTest test=null;
	public static String extentReportDestinationPath = null;
	public static String extentReportDefaultPath = null;
	
	public static void ExtentReportHTML()
	{
		htmlReport=new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Resources\\BufferLogs\\ExecutionReport.html");
		htmlReport.loadXMLConfig(System.getProperty("user.dir") + "\\Resources\\ConfigurationalFiles\\SmartReport_Config.xml");
		extent = new ExtentReports();
		extent.attachReporter(htmlReport);
	}
	
	public static void ExtentReportNewTest(String testDescription)
	{
		test = extent.createTest(testDescription);
	}
	
	public static void ExtentReportPrintInfo(String sMessage)
	{
		if (test != null)
			test.log(Status.INFO, sMessage);
	}
	
	public static void ExtentReportPrintDebug(String sMessage)
	{
		if (test != null)
			test.log(Status.DEBUG, sMessage);
	}
	
	public static void ExtentReportPrintError(String sMessage)
	{
		if (test != null)
			test.log(Status.ERROR, sMessage);
	}
	
	public static void ExtentReportStepsPass(String sMessage)
	{
		if (test != null)
			test.pass(sMessage);
	}
	
	public static void ExtentReportStepsFail(String sMessage)
	{
		if (test != null)
			test.fail(sMessage);
	}
	
	public static void ExtentReportFailedScreenshotAttach(String sMessage, String sPath) throws Exception
	{
		if (test != null)
			test.debug(sMessage, MediaEntityBuilder.createScreenCaptureFromPath(sPath).build());
	}
	
	public static void ExtentReportFlush()
	{
		extent.flush();
	}
	
	public static void MoveExtentReportToDestination() throws IOException
	{
		extentReportDefaultPath = System.getProperty("user.dir") + "\\Resources\\BufferLogs\\ExecutionReport.html";
		extentReportDestinationPath=Common.GetExtentReportPath() + "\\ExecutionReport.html";
		File srcExtentReportFile = new File(extentReportDefaultPath);
		File destExtentReportFile = new File(extentReportDestinationPath);
		//FileUtils.cleanDirectory(destExtentReportFile); 
		Files.copy(srcExtentReportFile.toPath(), destExtentReportFile.toPath());
	}
}
