package TestCaseDesktopBrowser.automation;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import BusinessFunctionDesktopBrowser.DesktopBrowserLoginBusiness;
import BusinessFunctionDesktopBrowser.SearchBusinessDesktopBrowser;
import HelperClass.SmartLogger;
import Utility.MasterDataBase;
import Utility.TestDataBase;
import Utility.UtilitiesWebDriver;

/**
 * This class implements Test Cases for Search Customer And Discounts on EDMS UI
 * @author akmukhop
 * @version 2.0
 */
@Listeners(HelperClass.CustomListener.class)
public class SearchTestCases{

	public static WebDriver driver;
	public static Statement statement;
	public static String sPartyID = null;
	public static String sAccountName = null;
	public static String sBusinessEntityID = null;
	public static String sCustomerAccountNumber = null;
	public static String sPriceLists = null;
	public static String sEndCustomerCountry = null;
	public static String sIntendedUse = null;
	public static String sMultipleProductSku = null;
	public static String sMasterTableQuerries = null;
	public static String[] sTestID=null;
	public ArrayList<String> sTestData = new ArrayList<String>();
	public SmartLogger logger = new SmartLogger();
	
	/**
	 * Method Sets up the test data values particular to TestID
	 * @param dbFetchedTestData hold the data fetched from Test dataBase
	 */
	public void SetTestDataValue(ArrayList<String> dbFetchedTestData)
	{
		String[] miscellaneousData=null;
		if(dbFetchedTestData.get(1)!=null)
		{
			miscellaneousData = dbFetchedTestData.get(1).toString().split("\\|");
			sPartyID = dbFetchedTestData.get(0);
			sAccountName = dbFetchedTestData.get(0);
			sBusinessEntityID = dbFetchedTestData.get(0);
			sCustomerAccountNumber = dbFetchedTestData.get(0);
			sPriceLists = miscellaneousData[0];
			sEndCustomerCountry = miscellaneousData[1];
			sIntendedUse = miscellaneousData[2];
			sMultipleProductSku = miscellaneousData[3];
			sMasterTableQuerries=dbFetchedTestData.get(2);
		}
		else
		{
			sPartyID = dbFetchedTestData.get(0);
			sAccountName = dbFetchedTestData.get(0);
			sBusinessEntityID = dbFetchedTestData.get(0);
			sCustomerAccountNumber = dbFetchedTestData.get(0);
			sMasterTableQuerries=dbFetchedTestData.get(2);
		}
			
	}
	
	/**
	 * This methods implements Connection of Master and Test database, as part of Test Preparation
	 * @throws SQLException return an exception failure cause from Oracle JDBC and MySQL connection failure
	 * @throws IOException Signals that an I/O exception of some sort has occurred.
	 * @throws ClassNotFoundException return an exception failure cause from Oracle JDBC and MySQL Class string mismacth
	 */
	@BeforeClass
	public void DataSetup() throws SQLException, IOException, ClassNotFoundException
	{
		TestDataBase.ConnectToTestDB();
		logger.PrintInfo("Connected to MasterDB");
		MasterDataBase.ConnectToMasterDB();
		logger.PrintInfo("Connected to TestDB");
	}
	
	
	/**
	 * Method implements the opening of the browser and fetch Test data from Test dataase 
	 * @param result holds the instance of the test execution
	 * @throws Exception
	 */
	@BeforeMethod
	public void SetPropertyForWebDriver(ITestResult result) throws Exception
	{	
		String description = result.getMethod().getDescription();
		SearchTestCases.driver = UtilitiesWebDriver.OpenBrowser(driver);
		LoginTestCases.driver = SearchTestCases.driver;
		UtilitiesWebDriver.GetApplicationURL(SearchTestCases.driver);
		sTestID = description.trim().split(":");
		sTestData = TestDataBase.FetchSearchCustomerData(sTestID[0]);
		SetTestDataValue(sTestData);
	}
	
	/**
	 * Method implements the killing of Web driver instance once the test case is completed
	 */
	@AfterMethod
	public void TearDown()
	{
		UtilitiesWebDriver.KillWebDriverInstance(SearchTestCases.driver);	
	}
	
	/**
	 * Method implements the killing of Master and Test DataBase drivers.
	 */
	@AfterClass
	public void DataCleanUp() throws SQLException
	{
		TestDataBase.KillMySQLDriverInstance();
		MasterDataBase.KillMasterDBdriverInstance();
	}
	
	//@Test(testName ="TC008", description="TC008: Search Customer by Party ID")
	public void SearchCustomerPartyID() throws SQLException
	{
		try {
			if(DesktopBrowserLoginBusiness.LoginInApplication()) 
			{
				if(SearchBusinessDesktopBrowser.SearchCustomerDetails(HelperClass.Enums.SearchCustomers.PARTY_ID, sPartyID, sPriceLists, sEndCustomerCountry, sIntendedUse, sMultipleProductSku, sMasterTableQuerries)) 
				{
					logger.PrintInfo("TestCase Passed");
				}
			}
		}
		catch(Exception ex){
			logger.PrintError("");
 			Validation.AssertionsDesktopBrowser.AssertFailMessage("Exception Caused: " + ex.getLocalizedMessage());
			}
	}
	
	//@Test(testName ="TC009", description="TC009: Search Customer by Account Number")
	public void SearchCustomerAccountNumber() throws IOException
	{
		try {
			if(DesktopBrowserLoginBusiness.LoginInApplication()) 
			{
				if(SearchBusinessDesktopBrowser.SearchCustomerDetails(HelperClass.Enums.SearchCustomers.CUSTOMER_ACCOUNT_NUMBER, sCustomerAccountNumber, sPriceLists, sEndCustomerCountry, sIntendedUse, sMultipleProductSku, sMasterTableQuerries)) 
				{
					logger.PrintInfo("TestCase Passed");
				}
			}
		}
		catch(Exception ex){
 			Validation.AssertionsDesktopBrowser.AssertFailMessage("Exception Caused: " + ex.getLocalizedMessage());
 			logger.PrintError(ex.getLocalizedMessage());
			}
	}
	
	//@Test(testName ="TC010", description="TC010: Search Customer by Business Entity ID (BE ID)")
	//Testing Git Commit
	public void SearchCustomerBusinessEntityID()
	{
		try {
			if(DesktopBrowserLoginBusiness.LoginInApplication()) 
			{
				if(SearchBusinessDesktopBrowser.SearchCustomerDetails(HelperClass.Enums.SearchCustomers.BUSINESS_ENTITY_ID, sBusinessEntityID, sPriceLists, sEndCustomerCountry, sIntendedUse, sMultipleProductSku, sMasterTableQuerries)) 
				{
					logger.PrintInfo("TestCase Passed");
				}
			}
		} 	
		catch(Exception ex){
 			Validation.AssertionsDesktopBrowser.AssertFailMessage("Exception Caused: " + ex.getLocalizedMessage());
 			logger.PrintError(ex.getLocalizedMessage());
			}
	}
	
	//@Test(testName ="TC011", description="TC011: Search Customer by Account Name")
	public void SearchCustomerAccountName()
	{
		try {
			if(DesktopBrowserLoginBusiness.LoginInApplication()) 
			{
				if(SearchBusinessDesktopBrowser.SearchCustomerDetails(HelperClass.Enums.SearchCustomers.ACCOUNT_NAME, sAccountName, sPriceLists, sEndCustomerCountry, sIntendedUse, sMultipleProductSku, sMasterTableQuerries)) 
				{
					logger.PrintInfo("TestCase Passed");
				}
			}
		}
		catch(Exception ex){
 			Validation.AssertionsDesktopBrowser.AssertFailMessage("Exception Caused: " + ex.getLocalizedMessage());
 			logger.PrintError(ex.getLocalizedMessage());
			}
	}
	
	@Test(testName ="TC012", description="TC012: Search Customer by BEGEO ID")
		public void SearchCustomerBeGeoID() throws SQLException
		{
			try {
				if(DesktopBrowserLoginBusiness.LoginInApplication()) 
				{
					if(SearchBusinessDesktopBrowser.SearchCustomerDetails(HelperClass.Enums.SearchCustomers.BEGEO_ID, sAccountName, sPriceLists, sEndCustomerCountry, sIntendedUse, sMultipleProductSku, sMasterTableQuerries)) 
					{
						logger.PrintInfo("TestCase Passed");
					}
				}
			}
			catch(Exception ex){
	 			Validation.AssertionsDesktopBrowser.AssertFailMessage("Exception Caused: " + ex.getLocalizedMessage());
	 			logger.PrintError(ex.getLocalizedMessage());
				}
		}
}
