package BusinessFunction;

import org.openqa.selenium.WebDriver;
import HelperClass.ReadPropertyFile;
import HelperClass.SmartLogger;
import PageClass.LoginPageClass;
import TestCaseUI.PriceSearchTestCases;
import Utility.UtilitiesWebDriver;
import Validation.AssertionsDesktopBrowser;

public class LoginBusiness {

	public static ReadPropertyFile readProprtyFile;
	public static Boolean LoginInApplication() throws Exception
	{
		readProprtyFile = new ReadPropertyFile();
		LoginPageClass LoginPageObj = new LoginPageClass(PriceSearchTestCases.driver);
		if(!AssertIsApplicationLoggedIn())
		{
			LoginPageObj.SetValueForUserNameTextbox(readProprtyFile.GetBrowserLoginValidUserName());
			LoginPageObj.ClickOnloginButton();
			LoginPageObj.SetValueForpasswordTextbox(readProprtyFile.GetBrowserLoginValidPassword());
			LoginPageObj.ClickOnSignInButton();
			if(AssertionsDesktopBrowser.ElementTextAssert("Welcome  Chris Stokes", LoginPageObj.FetchLoggedUserName()))
				return true;
			else
				return false;
		}
		else
		{
			SmartLogger.PrintInfo("Application is already logged in");
			return true;
		}
	}
	
	public static Boolean AssertIsApplicationLoggedIn() throws Exception
	{
		WebDriver driver = UtilitiesWebDriver.GetWebDriverInstance();
		readProprtyFile = new ReadPropertyFile();
		String environment = readProprtyFile.GetExecutionEnvironment();
		if(environment.equalsIgnoreCase("TS1"))
		{
			if(AssertionsDesktopBrowser.AssertStringContains(UtilitiesWebDriver.GetCurrentUrl(driver), readProprtyFile.GetTS1BrowserURL()))
				return true;
			else
				return false;
		}
		else if (environment.equalsIgnoreCase("TS3"))
		{
			if(AssertionsDesktopBrowser.AssertStringContains(UtilitiesWebDriver.GetCurrentUrl(driver), readProprtyFile.GetTS3BrowserURL()))
				return true;
			else
				return false;
		}
		else
			throw new Exception("Environment Name is not correct");
	}
	
	
}
