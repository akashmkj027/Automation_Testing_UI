package HelperClass;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import Utility.Common;

public class SmartLogger {

	private static Logger logger;
	private static String logDefaultPath=null;
	private static String logDestinationPath=null;
	private static String logHTMLdefaultPath=null;
	private static String logHTMLdestinationPath=null;
	private static String extentLogHTMLdefaultPath=null;
		
	public static void InitiateSmartLogging()
	{
		logger = Logger.getLogger(SmartLogger.class);
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\Resources\\ConfigurationalFiles\\log4j.properties");
	}

	public static void PrintInfo(String sMessage)
	{
		logger.info(sMessage);
		SmartReporter.ExtentReportPrintInfo(sMessage);
		
	}
	
	public static void PrintDebug(String sMessage)
	{
		logger.debug(sMessage);
		SmartReporter.ExtentReportPrintDebug(sMessage);
	}
	
	public static void PrintError(String sMessage)
	{
		logger.error("Description: " + sMessage);
		SmartReporter.ExtentReportPrintError("Error Description: " + sMessage);
		
	}
	
	public static Logger GetLogger()
	{
		return logger;
	}
	
	public static void PrintPassStepLog(String sMessage)
	{
		logger.info(sMessage);
		SmartReporter.ExtentReportStepsPass(sMessage);
	}
	
	public static void PrintFailStepLog(String sMessage)
	{
		logger.error(sMessage);
		SmartReporter.ExtentReportStepsFail(sMessage);
	}
	
	public static void PrintFailedTestCaseAndAttachScreenshot(String sMessage, String sScreenshotPath) throws Exception
	{
		SmartReporter.ExtentReportFailedScreenshotAttach(sMessage, sScreenshotPath);
	}
	
	public static void MoveLogsToDestination() throws Exception
	{
		logDefaultPath = System.getProperty("user.dir") + "\\Resources\\BufferLogs\\Execution.log";
		logDestinationPath=Common.GetLogPath() + "\\Execution.log";
		logHTMLdefaultPath = System.getProperty("user.dir") + "\\Resources\\BufferLogs\\ExecutionLog.html";
		logHTMLdestinationPath=Common.GetLogPath() + "\\ExecutionLog.html";
		File srcLogFile = new File(logDefaultPath);
		File destLogFile = new File(logDestinationPath);
		File srcHTMLlogFile = new File(logHTMLdefaultPath);
		File destHTMLlogFile = new File(logHTMLdestinationPath);
		Files.copy(srcLogFile.toPath(), destLogFile.toPath());
		Files.copy(srcHTMLlogFile.toPath(), destHTMLlogFile.toPath());
	}
	
	public static void ClearExecutionLogsAndReports() throws Exception
	{
		logDefaultPath = System.getProperty("user.dir") + "\\Resources\\BufferLogs\\Execution.log";
		logHTMLdefaultPath = System.getProperty("user.dir") + "\\Resources\\BufferLogs\\ExecutionLog.html";
		extentLogHTMLdefaultPath = System.getProperty("user.dir") + "\\Resources\\BufferLogs\\ExecutionReport.html";
		FileWriter fileWriterObj = new FileWriter(logHTMLdefaultPath);
		PrintWriter printWriterObj = new PrintWriter(fileWriterObj);
		printWriterObj.write("");
		printWriterObj.flush(); 
		printWriterObj.close();
		FileWriter fileWriter = new FileWriter(logDefaultPath);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.write("");
		printWriter.flush(); 
		printWriter.close();
		FileWriter logFileWriter = new FileWriter(extentLogHTMLdefaultPath);
		PrintWriter logPrintWriter = new PrintWriter(logFileWriter);
		logPrintWriter.write("");
		logPrintWriter.flush(); 
		logPrintWriter.close();
	}

}
