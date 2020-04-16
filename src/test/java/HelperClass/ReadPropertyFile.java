package HelperClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

public class ReadPropertyFile {
	
	Properties prop = new Properties();
	FileInputStream propertyFile;
	Base64.Decoder decoder = Base64.getDecoder();
	public ReadPropertyFile() throws IOException
	{
		try {
			propertyFile=new FileInputStream(System.getProperty("user.dir") + "\\Resources\\ConfigurationalFiles\\EnvConfig.properties");			
			prop.load(propertyFile);
		}
		catch (Exception ex)
		{
			System.out.println("Exception Caused: ");
			ex.printStackTrace();
		}
	}
	public String GetTS1BrowserURL() throws IOException
	{
		String sBrowserURL = new String(decoder.decode(prop.getProperty("TS1_LOGIN_URL")));
		return sBrowserURL;
		
	}
	public String GetBrowserLoginValidUserName() 
	{
		String sUserName = new String(decoder.decode(prop.getProperty("VALID_USERNAME")));
		return sUserName;
	}
	public String GetBrowserLoginValidPassword() 
	{
		String sPassword = new String(decoder.decode(prop.getProperty("VALID_PASSWORD")));
		return sPassword;
	}
	public String GetBrowserLoginInvalidUserName() 
	{
		return prop.getProperty("INVALID_USERNAME");
	}
	public String GetBrowserLoginInvalidPassword() 
	{
		return prop.getProperty("INVALID_PASSWORD");
	}
	public String GetTS3BrowserURL() 
	{
		String sBrowserURL = new String(decoder.decode(prop.getProperty("TS3_LOGIN_URL")));
		return sBrowserURL;
	}
	public String GetServiceBaseURI() 
	{
		return prop.getProperty("BASE_URI");
	}
	public String GetBrowserName()
	{
		return prop.getProperty("BROWSER");
	}
	public String GetExecutionEnvironment()
	{
		return prop.getProperty("EXECUTION_ENV");
	}
	public String GetDriverRegistrationMySQL()
	{
		String sBrowserURL = new String(decoder.decode(prop.getProperty("TEST_DB_REGISTER_DRIVER")));
		return sBrowserURL;
	}
	public String GetDBConnectionURL()
	{
		String dbURL = new String(decoder.decode(prop.getProperty("TEST_DB_CONNECTION_URL")));
		return dbURL;
	}
	public String GetUserNameDB()
	{
		String sUserName = new String(decoder.decode(prop.getProperty("TEST_DB_USERNAME")));
		return sUserName;
	}
	public String GetPasswordDB()
	{
		String sPassword = new String(decoder.decode(prop.getProperty("TEST_DB_PASSWORD")));
		return sPassword;
	}
	public String GetEnvironmentMasterDB()
	{
		return prop.getProperty("MASTER_DB_NAME");
	}
	public String GetUserNameMasterDB()
	{
		String sUserName = new String(decoder.decode(prop.getProperty("MASTER_DB_USERNAME")));
		return sUserName;
	}
	public String GetPasswordMasterDB()
	{
		String sPassword = new String(decoder.decode(prop.getProperty("MASTER_DB_PASSWORD")));
		return sPassword;
	}
	public String GetMasterDBdriverRegistration()
	{
		String sBrowserURL = new String(decoder.decode(prop.getProperty("MASTER_DB_REGISTER_DRIVER")));
		return sBrowserURL;
	}
	public String GetMasterDBconnectionURL()
	{
		String dbURL = new String(decoder.decode(prop.getProperty("MASTER_DB_CONNECTION_URL")));
		return dbURL;
	}
	public String GetMasterDBtnsURL()
	{
		String sTns = new String(decoder.decode(prop.getProperty("MASTER_DB_TNS_URL")));
		return sTns;
	}
}
