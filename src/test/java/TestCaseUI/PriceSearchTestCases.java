package TestCaseUI;

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

import BusinessFunction.LoginBusiness;
import BusinessFunction.PriceSearchBusiness;
import HelperClass.SmartLogger;
import HelperClass.SmartReporter;
import HelperClass.TestBase;
import Utility.ActionDesktopBrowser;
import Utility.MasterDataBase;
import Utility.TestDataBase;
import Utility.UtilitiesWebDriver;

/**
 * This class implements Test Cases for Search Customer And Discounts on EDMS UI
 * @author akmukhop
 * @version 2.0
 */
@Listeners(HelperClass.CustomListener.class)
public class PriceSearchTestCases extends TestBase{

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
	public void DataSetup() throws Exception
	{
		//TestDataBase.ConnectToTestDB();
		//SmartLogger.PrintInfo("Connected to TestDB");
		//MasterDataBase.ConnectToMasterDB();
		//SmartLogger.PrintInfo("Connected to MasterDB");
		PriceSearchTestCases.driver = UtilitiesWebDriver.OpenBrowser(driver);
		UtilitiesWebDriver.GetApplicationURL(PriceSearchTestCases.driver);
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
		SmartReporter.ExtentReportNewTest(description);
		SmartLogger.InitiateSmartLogging();
		SmartLogger.PrintInfo("Initiating TestCase " + description);	
		//sTestID = description.trim().split(":");
		//sTestData = TestDataBase.FetchSearchCustomerData(sTestID[0]);
		//SetTestDataValue(sTestData);
	}
	
	/**
	 * Method implements the killing of Web driver instance once the test case is completed
	 */
	@AfterMethod
	public void TearDown() throws Exception
	{
		PriceSearchBusiness.GoHomePage();
		ActionDesktopBrowser.ImplicitWait(3);
	}
	
	/**
	 * Method implements the killing of Master and Test DataBase drivers.
	 */
	@AfterClass
	public void DataCleanUp() throws Exception
	{	
		//TestDataBase.KillMySQLDriverInstance();
		//MasterDataBase.KillMasterDBdriverInstance();
		UtilitiesWebDriver.KillWebDriverInstance(PriceSearchTestCases.driver);
	}
	
	@Test(testName ="TC001", description="TC001: Search Services Item of Distribution Catalog")
	public void SearchValidPriceList() throws Exception
	{
		try {
			if(LoginBusiness.LoginInApplication()) 
			{
				PriceSearchBusiness.SelectPriceCatalogAndOfferCatagory(HelperClass.Enums.Catalog.DISTRIBUTION_PRICING, HelperClass.Enums.OfferCatagory.SERVICE_ITEMS);
				PriceSearchBusiness.SelectPriceListPriceFile(HelperClass.Enums.PriceListPriceFile.DISTRIBUTION_PRICING_PLUS_US);
				PriceSearchBusiness.SetAndSearchItemNameItemDescription("CON-SNT-C14451K9", "SNTC-8X5XNBD C1 ISR 4451 (4GE,3NIM,2SM,8G FLASH)");
				if(PriceSearchBusiness.ValidatePriceSearchDistributionCatalog(HelperClass.Enums.OfferCatagory.SERVICE_ITEMS, "CON-SNT-C14451K9", "SNTC-8X5XNBD C1 ISR 4451 (4GE,3NIM,2SM,8G FLASH)"))
					SmartLogger.PrintPassStepLog("Results in UI Asserted");
				else
					SmartLogger.PrintFailStepLog("Results in UI Mismatched");
			}
		}
		catch(Exception ex){
			SmartLogger.PrintError(ex.getLocalizedMessage());
 			Validation.AssertionsDesktopBrowser.AssertFailMessage("Exception Caused: " + ex.getLocalizedMessage());
			}
	}
	
	@Test(testName ="TC002", description="TC002: Search Product Item of Distribution Catalog")
	public void SearchInvalidPriceList() throws Exception
	{
		try {
			if(LoginBusiness.LoginInApplication()) 
			{
				PriceSearchBusiness.SelectPriceCatalogAndOfferCatagory(HelperClass.Enums.Catalog.DISTRIBUTION_PRICING, HelperClass.Enums.OfferCatagory.PRODUCTS_ONLY);
				PriceSearchBusiness.SelectPriceListPriceFile(HelperClass.Enums.PriceListPriceFile.DISTRIBUTION_PRICING_PLUS_US);
				PriceSearchBusiness.SetAndSearchItemNameItemDescription("C1-LIC-VCM-1N", "Cisco ONE Promo license to manage one WAAS node");
				if(PriceSearchBusiness.ValidatePriceSearchDistributionCatalog(HelperClass.Enums.OfferCatagory.PRODUCTS_ONLY, "C1-LIC-VCM-1N", "Cisco ONE Promo license to manage one WAAS node"))
					SmartLogger.PrintPassStepLog("Results in UI Asserted");
				else
					SmartLogger.PrintFailStepLog("Results in UI Mismatched");
			}
		}
		catch(Exception ex){
			SmartLogger.PrintError(ex.getLocalizedMessage());
 			Validation.AssertionsDesktopBrowser.AssertFailMessage("Exception Caused: " + ex.getLocalizedMessage());
			}
	}
	
	@Test(testName ="TC003", description="TC003: Search Subscription Item of Distribution Catalog")
	public void SearchSubscriptionItemDistributionCatalog() throws Exception
	{
		try {
			if(LoginBusiness.LoginInApplication()) 
			{
				PriceSearchBusiness.SelectPriceCatalogAndOfferCatagory(HelperClass.Enums.Catalog.DISTRIBUTION_PRICING, HelperClass.Enums.OfferCatagory.SUBSCRIPTION_ITEM);
				PriceSearchBusiness.SelectPriceListPriceFile(HelperClass.Enums.PriceListPriceFile.DISTRIBUTION_PRICING_PLUS_US);
				PriceSearchBusiness.SetAndSearchItemNameItemDescription("E2-N-HXDP", "Cisco Data Center EA for Hyperflex");
				if(PriceSearchBusiness.ValidatePriceSearchDistributionCatalog(HelperClass.Enums.OfferCatagory.SUBSCRIPTION_ITEM, "E2-N-HXDP", "Cisco Data Center EA for Hyperflex"))
					SmartLogger.PrintPassStepLog("Results in UI Asserted");
				else
					SmartLogger.PrintFailStepLog("Results in UI Mismatched");
			}
		}
		catch(Exception ex){
			SmartLogger.PrintError(ex.getLocalizedMessage());
 			Validation.AssertionsDesktopBrowser.AssertFailMessage("Exception Caused: " + ex.getLocalizedMessage());
			}
	}
	
	@Test(testName ="TC004", description="TC004: Search Subscription Mandatory Item of Distribution Catalog")
	public void SearchSubscriptionMandaotryDistributionCatalog() throws Exception
	{
		try {
			if(LoginBusiness.LoginInApplication()) 
			{
				PriceSearchBusiness.SelectPriceCatalogAndOfferCatagory(HelperClass.Enums.Catalog.DISTRIBUTION_PRICING, HelperClass.Enums.OfferCatagory.SUBSCRIPTION_MANDATORY);
				PriceSearchBusiness.SelectPriceListPriceFile(HelperClass.Enums.PriceListPriceFile.DISTRIBUTION_PRICING_PLUS_US);
				PriceSearchBusiness.SetAndSearchItemNameItemDescription("E2SC-A5508-TAMC-5Y", "");//ELA 2 ASA5508 FirePOWER IPS, AMP and URL 5YR Subs
				if(PriceSearchBusiness.ValidatePriceSearchDistributionCatalog(HelperClass.Enums.OfferCatagory.SUBSCRIPTION_MANDATORY, "E2-N-HXDP", "ELA 2 ASA5508 FirePOWER IPS, AMP and URL 5YR Subs"))
					SmartLogger.PrintPassStepLog("Results in UI Asserted");
				else
					SmartLogger.PrintFailStepLog("Results in UI Mismatched");
			}
		}
		catch(Exception ex){
			SmartLogger.PrintError(ex.getLocalizedMessage());
 			Validation.AssertionsDesktopBrowser.AssertFailMessage("Exception Caused: " + ex.getLocalizedMessage());
			}
	}
}