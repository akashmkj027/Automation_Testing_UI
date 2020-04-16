package BusinessFunction;

import java.util.ArrayList;
import java.util.List;
import HelperClass.ReadPropertyFile;
import HelperClass.SmartLogger;
import PageClass.PriceSearchPageClass;
import TestCaseUI.PriceSearchTestCases;
import Utility.ActionDesktopBrowser;
import Utility.UtilitiesWebDriver;

public class PriceSearchBusiness {
	
	public static ReadPropertyFile readPropertyFileObject; 
	public static String sFetchedProductDescription = null;
	public static String sFetchedProductPrice = null;
	public static PriceSearchPageClass PriceSearchPageClassObj = null;
	
	public static boolean ValidatePriceSearchDistributionCatalog(HelperClass.Enums.OfferCatagory eOfferCatagory, String sItemNumber, String sProductDescription) throws Exception
	{
		PriceSearchPageClassObj = new PriceSearchPageClass(PriceSearchTestCases.driver);
		List<String> sFetchedProductNumber = new ArrayList<String>();
		SmartLogger.PrintInfo("All data in 1st Row is fetched from UI");
		sFetchedProductNumber = PriceSearchPageClassObj.GetAllPriceSearchResults(eOfferCatagory);
		ActionDesktopBrowser.ImplicitWait(2);
		if(sItemNumber.equals(sFetchedProductNumber.get(0)) && sProductDescription.equals(sFetchedProductNumber.get(1)))
		{
			SmartLogger.PrintInfo("Product Number and Description of 1st Row in UI are asserted");
			return true;
		}
		else
		{
			SmartLogger.PrintError("Assertion falied for Product Number and Description of 1st Row in UI");
			return false;
		}
	}
	
	public static void SelectPriceCatalogAndOfferCatagory(HelperClass.Enums.Catalog eCatalog, HelperClass.Enums.OfferCatagory eOfferCategory) throws Exception
	{
		PriceSearchPageClassObj = new PriceSearchPageClass(PriceSearchTestCases.driver);
		PriceSearchPageClassObj.ClickOnPriceSearchTab();
		PriceSearchPageClassObj.SelectCatalog(eCatalog);
		PriceSearchPageClassObj.SelectOffercatagory(eOfferCategory);
	}
	
	public static void SelectPriceListPriceFile(HelperClass.Enums.PriceListPriceFile ePriceList) throws Exception
	{
		PriceSearchPageClassObj = new PriceSearchPageClass(PriceSearchTestCases.driver);
		switch (ePriceList) {
		case GLOBAL_PRICE_LIST_US:
			PriceSearchPageClassObj.SelectPriceList(ePriceList);
			SmartLogger.PrintInfo("Price File Selected As: " + ePriceList.toString());
			break;
		case WHOLESALE_PRICE_LIST_US:
			PriceSearchPageClassObj.SelectPriceList(ePriceList);
			SmartLogger.PrintInfo("Price File Selected As: " + ePriceList.toString());
			break;
		case DISTRIBUTION_PRICING_PLUS_US:
			PriceSearchPageClassObj.SelectPriceFile();
			SmartLogger.PrintInfo("Price File Selected As: " + ePriceList.toString());
			break;
		default:
			break;
		}
	}
	
	public static void SetAndSearchItemNameItemDescription(String sItemNumber, String sItemDescription) throws Exception
	{
		PriceSearchPageClassObj = new PriceSearchPageClass(PriceSearchTestCases.driver);
		PriceSearchPageClassObj.SetValueForItemNumberAndDescription(sItemNumber, sItemDescription);
		PriceSearchPageClassObj.ClickOnViewButton();
	}
	
	public static void GoHomePage() throws Exception
	{
		UtilitiesWebDriver.GetApplicationURL(PriceSearchTestCases.driver);
	}
}
