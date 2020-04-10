
package TestCaseDesktopBrowser.automation;



import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import BusinessFunctionDesktopBrowser.DesktopBrowserLoginBusiness;
import HelperClass.ReadPropertyFile;
import Utility.UtilitiesWebDriver;

public class LoginTestCases{

	public static WebDriver driver;
	public ReadPropertyFile readPropertyFile;
	
	@BeforeClass
	public void DataSetup() throws IOException
	{
		readPropertyFile = new ReadPropertyFile();
	}
	
	@BeforeMethod
	public void SetPropertyForWebDriver() throws Exception
	{
		LoginTestCases.driver = UtilitiesWebDriver.OpenBrowser(driver);
		UtilitiesWebDriver.GetApplicationURL(driver);
	}
	
	@Test(testName="TC001", description="TC001: Login with valid Username and password")
	public void LoginValidCredentials()
	{
		try {
				if(DesktopBrowserLoginBusiness.LoginInApplication(readPropertyFile.GetBrowserLoginValidUserName(),readPropertyFile.GetBrowserLoginValidPassword())) 
				{
					System.out.println("TestCase Passed");
				}
			}
		catch(Exception ex){
 			System.out.println("Exception Caused: " + ex.getLocalizedMessage());
 			Validation.AssertionsDesktopBrowser.AssertFailMessage("Exception Caused: " + ex.getLocalizedMessage());
			}
	}
	
	@Test(testName="TC002", description="TC002: Login with Invalid Username")
	public void LoginInValidUserName()
	{
		System.out.println("Executing InValid Username");
		try {
			if(DesktopBrowserLoginBusiness.LoginInApplication(readPropertyFile.GetBrowserLoginInvalidUserName(),readPropertyFile.GetBrowserLoginValidPassword())) {
			System.out.println("TestCase Passed");}
			}
		catch(Exception ex){
			System.out.println("Exception Caused: " + ex.getLocalizedMessage());
			Validation.AssertionsDesktopBrowser.AssertFailMessage("Failing Invalid UserName");
			}
	}
	
	@Test(testName="TC003", description="TC003: Login with valid Username but invalid password")
	public void LoginInValidPassword()
	{
		System.out.println("Executing InValid Password");
		try {
			if(DesktopBrowserLoginBusiness.LoginInApplication(readPropertyFile.GetBrowserLoginValidUserName(),readPropertyFile.GetBrowserLoginInvalidPassword())) {
				System.out.println("TestCase Passed");}	
			}
		catch(Exception ex){
			System.out.println("Exception Caused: " + ex.getLocalizedMessage());
			Validation.AssertionsDesktopBrowser.AssertFailMessage("Failing Invalid Password");
			}
	}

	
	@AfterMethod
	public void TearDown() throws Exception
	{
		UtilitiesWebDriver.KillWebDriverInstance(LoginTestCases.driver);
		
	}

}
