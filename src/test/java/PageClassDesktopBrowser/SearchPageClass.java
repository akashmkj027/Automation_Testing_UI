package PageClassDesktopBrowser;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import Utility.ActionDesktopBrowser;


public class SearchPageClass {
	
	WebDriver driver;
	ActionDesktopBrowser actionBrowserObj = new ActionDesktopBrowser(driver);
	public SearchPageClass(WebDriver Idriver)
	{
			this.driver=Idriver;
			PageFactory.initElements(driver, this);
	}
		
	@FindBy(how=How.ID, using =  "srchTyp")
	public WebElement selectCustomerSearchBy;
	
	@FindBy(how=How.ID, using =  "srchTrm")
	public WebElement searchTextbox;
	
	@FindBy(how=How.XPATH, using =  "//input[@kdfid='btn_basicSearchButton']")
	public WebElement searchButton;
	
	@FindBy(how=How.XPATH, using =  "//span[@kdfid='tpTgglBtn2']")
	public WebElement searchTab;
	
	@FindBy(how=How.ID, using =  "accntPrcLstFltr")
	public WebElement selectPriceList;
	
	@FindBy(how=How.ID, using =  "accntCntryDrpDwnFltr")
	public WebElement selectEndCustomerCountry;
	
	@FindBy(how=How.ID, using =  "accntSearchIntUse")
	public WebElement selectIntendedUse;
	
	@FindBy(how=How.ID, using =  "accntSearchSku")
	public WebElement multipleProductSku;
	
	@FindBy(how=How.ID, using =  "accntNameSrchCntry")
	public WebElement selectSearchCountryAccountName;
	
	@FindBy(how=How.XPATH, using =  "//tr[contains(@class,'ev_dhx_skyblue')]/td")
	public List<WebElement> allVisibleSearchResults;
	
	public void SelectCountrySearchForAccountName(String sCountryName)
	{
		Select CountrySearchAccountNameDropDown = new Select(selectSearchCountryAccountName);
		CountrySearchAccountNameDropDown.selectByVisibleText(sCountryName);
	}

	public void SelectPriceList(String sPriceList)
	{
		Select priceListDropDown = new Select(selectPriceList);
		priceListDropDown.selectByVisibleText(sPriceList);
	}
	
	public void SelectEndCustomerCountry(String sEndCustomerCountry)
	{
		Select endCustomerCountryDropDown = new Select(selectEndCustomerCountry);
		endCustomerCountryDropDown.selectByVisibleText(sEndCustomerCountry);
	}
	
	public void SelectIntendedUse(String sIntendedUse)
	{
		Select intendedUseDropDown = new Select(selectIntendedUse);
		intendedUseDropDown.selectByVisibleText(sIntendedUse);
	}
	
	public void SetValueMultipleProductSku(String sProductSku) throws Exception
	{
		actionBrowserObj.SendTextinTextbox(multipleProductSku, sProductSku);
	}
	
	public void SelectCustomerSearchBy(HelperClass.Enums.SearchCustomers searchBy) throws Exception
	{
		Select selectShowTypeDropDown = new Select(selectCustomerSearchBy);
		ActionDesktopBrowser.ImplicitWait(1);
		switch (searchBy) 
		{
			case PARTY_ID:
				selectShowTypeDropDown.selectByVisibleText("Party ID (Site ID)");
					break;
			case ACCOUNT_NAME:
				selectShowTypeDropDown.selectByVisibleText("Account Name");
				    break;
			case BUSINESS_ENTITY_ID:
				selectShowTypeDropDown.selectByVisibleText("Business Entity ID (BE ID)");
					break;
			case CUSTOMER_ACCOUNT_NUMBER:
				selectShowTypeDropDown.selectByVisibleText("Customer Account Number");
			case BEGEO_ID:
				selectShowTypeDropDown.selectByVisibleText("BEGEO ID");
				    break;	 
			default:
					System.out.println("INVALID SEARCH BY VALUE SELECTED");
					break;
		}
	}
	
	public void SetValueSearchTextbox(String searchKeyword) throws Exception
	{
		actionBrowserObj.SendTextinTextbox(searchTextbox, searchKeyword);
	}
	
	public void ClickOnSearchButton() throws Exception
	{
		actionBrowserObj.clickOnElement(searchButton);
	}
	
	public void ClickOnSearchTabOnHomePage() throws Exception
	{
		actionBrowserObj.WaitForElementVisible(searchTab, 10);
		actionBrowserObj.clickOnElement(searchTab);
	}
		
	public ArrayList<String> FetchAllVisibleSearchResult()
	{

		ArrayList<String> sAllSearchResults = new ArrayList<String>();
		if(allVisibleSearchResults.size()!=0)
			{
			for (WebElement element : allVisibleSearchResults) {
				sAllSearchResults.add(element.getText());
				}
			return sAllSearchResults;
			}
		else
			return sAllSearchResults=null;
	}
}
