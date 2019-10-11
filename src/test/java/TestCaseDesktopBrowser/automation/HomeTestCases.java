
package TestCaseDesktopBrowser.automation;



import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BusinessFunctionDesktopBrowser.DesktopBrowserHomeBusiness;
import BusinessFunctionDesktopBrowser.DesktopBrowserLoginBusiness;
import Utility.TestDataBase;
import Utility.UtilitiesWebDriver;

public class HomeTestCases{

	public static WebDriver driver;
	ArrayList<String> CredentialData = new ArrayList<String>();
	@BeforeClass
	public void DataSetup() throws SQLException, IOException
	{
		CredentialData = TestDataBase.FetchUsernameAndPassword("TC001");
		System.out.println("DB Data: " + CredentialData.get(0) + CredentialData.get(1));
	}
	
	@BeforeMethod
	public void SetPropertyForWebDriver() throws Exception
	{
		HomeTestCases.driver = UtilitiesWebDriver.OpenBrowser(driver);
		UtilitiesWebDriver.GetApplicationURL(driver);
		LoginTestCases.driver = HomeTestCases.driver;
	}
	
	@Test(testName="TC004", description="Open an exixsting Completed Request from Home Page And Assert the modify/Renew option")
	public void OpenCompletedRequestFromHomePage()
	{
		try {
			if(DesktopBrowserLoginBusiness.LoginInApplication()) 
			{
				if(DesktopBrowserHomeBusiness.OpenUserCreatedRequest("Completed")) 
				{
					System.out.println("TestCase Passed");
				}
			}
		}
		catch(Exception ex){
 			System.out.println("Exception Caused: " + ex.getLocalizedMessage());
 			Validation.AssertionsDesktopBrowser.AssertFailMessage("Exception Caused: " + ex.getLocalizedMessage());
			}
	}
	
	@Test(testName="TC005", description="Open an exixsting Pending Submission Request from Home Page And Assert if its editable")
	public void OpenPendingSubmissionRequestFromHomePage()
	{
		try {
			if(DesktopBrowserLoginBusiness.LoginInApplication()) 
			{
				if(DesktopBrowserHomeBusiness.OpenUserCreatedRequest("Pending Submission")) 
				{
					System.out.println("TestCase Passed");
				}
			}
		}
		catch(Exception ex){
 			System.out.println("Exception Caused: " + ex.getLocalizedMessage());
			Validation.AssertionsDesktopBrowser.AssertFailMessage("Exception Caused: " + ex.getLocalizedMessage());
			}
	}
	
	@Test(testName="TC006", description="Import request from Excel Upload: Invalid Template")
	public void ExcelUploadinImportRequestFeatureInvalidTemplate()
	{
		try {
			if(DesktopBrowserLoginBusiness.LoginInApplication()) 
			{
				DesktopBrowserHomeBusiness.CreateDiscountRequestViaExcelUpload(Utility.Enums.FileType.EXCEL);
				if(DesktopBrowserHomeBusiness.ValidateErrorMessage())
					System.out.println("Testcase Passed");
				else
					Validation.AssertionsDesktopBrowser.AssertFailMessage("Testcase Failed");
			}
		}
		catch(Exception ex){
 			System.out.println("Exception Caused: " + ex.getLocalizedMessage());
			}
	}
	
	@Test(testName="TC007", description="Import request from Excel Upload: Invalid File Type")
	public void ExcelUploadinImportRequestFeatureInvalidFileType()
	{
		try {
			if(DesktopBrowserLoginBusiness.LoginInApplication()) 
			{
				DesktopBrowserHomeBusiness.CreateDiscountRequestViaExcelUpload(Utility.Enums.FileType.XML);
				if(DesktopBrowserHomeBusiness.ValidateErrorMessage())
					System.out.println("Testcase Passed");
				else
					Validation.AssertionsDesktopBrowser.AssertFailMessage("Testcase Failed");
			}
		}
		catch(Exception ex){
 			System.out.println("Exception Caused: " + ex.getLocalizedMessage());
			}
	}
	
	@AfterMethod
	public void TerminateDriverInstance()
	{
		UtilitiesWebDriver.KillDriverInstance(HomeTestCases.driver);
		
	}
	
	@AfterClass
	public void DataCleanUp()
	{
		TestDataBase.KillDriverInstanceMySQL();
	}

}
