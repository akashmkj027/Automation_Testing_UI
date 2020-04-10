package Utility;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import HelperClass.ReadPropertyFile;
import HelperClass.SmartLogger;

public class Common{

	public static Connection connection=null; 
	public static Statement statement = null;
	public static ReadPropertyFile readPropertyFile;
	public static String sExecutionFolderPath=System.getProperty("user.dir") + "\\Results\\ExecutionReport(" + DateTimeExctract() +")";
	public static String sLogPath= sExecutionFolderPath + "\\SmartLogs";
	public static String sScreenshotPath= sExecutionFolderPath + "\\FailedCasesScreenshots";
	public static String sExtentReportPath= sExecutionFolderPath + "\\SmartReport";
	
	public static Statement ConnectToTestDataBase(Statement statement)
	{
		try{
			readPropertyFile = new ReadPropertyFile();
			Class.forName(readPropertyFile.GetDriverRegistrationMySQL().toString());
			connection = DriverManager.getConnection(readPropertyFile.GetDBConnectionURL(),readPropertyFile.GetUserNameDB(),readPropertyFile.GetPasswordDB());
			statement = connection.createStatement();
			return statement;
			}
		catch (Exception e) {
			return statement;
		 	}
	}
	
	public static void TerminateDriverInstanceDB(Statement statement1) throws SQLException
	{
	    if (statement1 != null)
	    {
	    	statement1.close();
	    } 
	    if (connection !=null)
	    {
	    	connection.close();
	    	//SmartLogger.PrintInfo("Closed Test Data Base Connection");
	    }
	}
	
	public static void TerminateDataBaseResultSetInstance(ResultSet resultSet) throws SQLException
	{
		if (resultSet!= null)
			resultSet.close();
	}
	
	public static void CreateReportingFolders() throws IOException
	{
		File file = new File(sExecutionFolderPath);
		if(file.mkdir())
		{
			file = new File(sLogPath);
			if(file.mkdir())
			{
				file = new File(sScreenshotPath);	
				if(file.mkdir())
				{
					file = new File(sExtentReportPath);
					file.mkdir();
				}
			}
		}
	}
	
	public static String GetLogPath()
	{
		return sLogPath;
	}
	
	public static String GetScreenshotPath()
	{
		return sScreenshotPath;
	}
	
	public static String GetExtentReportPath()
	{
		return sExtentReportPath; 
	}
	
	public static String DateTimeExctract()
	{
		Date date = new Date();
		return date.toString().replace(":","-");
	}
}