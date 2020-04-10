package BusinessFunctionDesktopBrowser;

import java.sql.SQLException;
import java.util.ArrayList;
import HelperClass.ReadPropertyFile;
import PageClassDesktopBrowser.SearchPageClass;
import TestCaseDesktopBrowser.automation.SearchTestCases;
import Utility.ActionDesktopBrowser;
import Validation.AssertionsDesktopBrowser;
import Validation.QueryMasterDB;


public class SearchBusinessDesktopBrowser {
	
	public static ReadPropertyFile readPropertyFileObject; 
	/**
	 * Method implements Searching of a Customer with different search criteria 
	 * @param searchType holds the search Type from dropDown (PartyID/BegeoID/CustomerAccount Number/AccountName/BussEntityID)
	 * @param sSearchData holds the data String that is to be searched in the search box 
	 * @param priceList holds for which PriceList the Customer is to be searched for
	 * @param endCustomerCountry holds for which EndCustomer the Customer is to be searched for
	 * @param intendedUse holds for which Intended Use the Customer is to be searched for
	 * @param multipleProductSku holds for which SKU(s) the Customer is to be searched for
	 * @param sMasterTableQuerries holds the SQl Query that is to be executed on Master DB for result verification
	 * @return true if the search result in UI matched with backend results.
	 * @throws InterruptedException Signals that an Interrupted exception of some sort has occurred.
	 * @throws SQLException return an exception failure cause from Oracle JDBC and MySQL connection failure
	 */
	public static boolean SearchCustomerDetails(HelperClass.Enums.SearchCustomers searchType, String sSearchData, String priceList, String endCustomerCountry, String intendedUse, String multipleProductSku, String sMasterTableQuerries ) throws Exception 
	{
		boolean status=false;
		SearchPageClass SearchCustomerPageClassObj = new SearchPageClass(SearchTestCases.driver);
		ArrayList<String> sResultUIdata = new ArrayList<String>();
		ArrayList<String> sMasterDBdata = new ArrayList<String>();		
		ActionDesktopBrowser.ImplicitWait(1);
		SearchCustomerPageClassObj.ClickOnSearchTabOnHomePage();
		ActionDesktopBrowser.ImplicitWait(2);
		SearchCustomerPageClassObj.SelectCustomerSearchBy(searchType);
		ActionDesktopBrowser.ImplicitWait(1);
		switch (searchType) {
		case PARTY_ID:
			SearchCustomerPageClassObj.SetValueSearchTextbox(sSearchData);
			ActionDesktopBrowser.ImplicitWait(1);
			SearchCustomerPageClassObj.ClickOnSearchButton();
			ActionDesktopBrowser.ImplicitWait(3);
			sResultUIdata = SearchCustomerPageClassObj.FetchAllVisibleSearchResult();
			if(AssertionsDesktopBrowser.AssertValueFromArrayStringList(sResultUIdata, sSearchData))
				status=true;
			else
				status=false;
			break;
		case ACCOUNT_NAME:
			SearchCustomerPageClassObj.SelectCustomerSearchBy(searchType);
			ActionDesktopBrowser.ImplicitWait(1);
			SearchCustomerPageClassObj.SelectCountrySearchForAccountName(endCustomerCountry);
			ActionDesktopBrowser.ImplicitWait(1);
			SearchCustomerPageClassObj.SetValueSearchTextbox(sSearchData);
			ActionDesktopBrowser.ImplicitWait(1);
			SearchCustomerPageClassObj.ClickOnSearchButton();
			ActionDesktopBrowser.ImplicitWait(3);
			sResultUIdata = SearchCustomerPageClassObj.FetchAllVisibleSearchResult();
			if(AssertionsDesktopBrowser.AssertValueFromArrayStringList(sResultUIdata, sSearchData))
				status=true;
			else
				status=false;
			break;
		case BUSINESS_ENTITY_ID:
			SearchCustomerPageClassObj.SelectCustomerSearchBy(searchType);
			ActionDesktopBrowser.ImplicitWait(1);
			SearchCustomerPageClassObj.SetValueSearchTextbox(sSearchData);
			ActionDesktopBrowser.ImplicitWait(1);
			SearchCustomerPageClassObj.ClickOnSearchButton();
			ActionDesktopBrowser.ImplicitWait(9);
			sResultUIdata = SearchCustomerPageClassObj.FetchAllVisibleSearchResult();
			if(AssertionsDesktopBrowser.AssertValueFromArrayStringList(sResultUIdata, "45932817"))
				status=true;
			else
				status=false;
			break;
		case CUSTOMER_ACCOUNT_NUMBER:
			SearchCustomerPageClassObj.SelectCustomerSearchBy(searchType);
			ActionDesktopBrowser.ImplicitWait(1);
			SearchCustomerPageClassObj.SetValueSearchTextbox(sSearchData);
			ActionDesktopBrowser.ImplicitWait(1);
			SearchCustomerPageClassObj.SelectPriceList(priceList);
			ActionDesktopBrowser.ImplicitWait(1);
			SearchCustomerPageClassObj.SelectEndCustomerCountry(endCustomerCountry);
			ActionDesktopBrowser.ImplicitWait(1);
			SearchCustomerPageClassObj.SelectIntendedUse(intendedUse);
			ActionDesktopBrowser.ImplicitWait(1);
			SearchCustomerPageClassObj.SetValueMultipleProductSku(multipleProductSku);
			ActionDesktopBrowser.ImplicitWait(1);
			SearchCustomerPageClassObj.ClickOnSearchButton();
			ActionDesktopBrowser.ImplicitWait(3);
			sResultUIdata = SearchCustomerPageClassObj.FetchAllVisibleSearchResult();
			if(AssertionsDesktopBrowser.AssertValueFromArrayStringList(sResultUIdata, "111045541"))
				status=true;
			else
				status=false;
			break;
		case BEGEO_ID:
			SearchCustomerPageClassObj.SelectCustomerSearchBy(searchType);
			ActionDesktopBrowser.ImplicitWait(1);
			SearchCustomerPageClassObj.SetValueSearchTextbox(sSearchData);
			ActionDesktopBrowser.ImplicitWait(1);
			SearchCustomerPageClassObj.ClickOnSearchButton();
			ActionDesktopBrowser.ImplicitWait(3);
			sResultUIdata = SearchCustomerPageClassObj.FetchAllVisibleSearchResult();
			sMasterDBdata = QueryMasterDB.FetchBeGeoIDdetailsFromMasterTable(sMasterTableQuerries);
			if(AssertionsDesktopBrowser.AssertValueFromMasterDataBase(sMasterDBdata, sResultUIdata, "Mimatch in UI and Backend Data for BEGEO ID Search Results"))
				status=true;
			else
				status=false;
			break;
		default:
			AssertionsDesktopBrowser.AssertFailMessage("INVALID SEARCH BY SELECTED");
			break;
		}	
		return status;
	}	
}
