package PageClass;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import HelperClass.SmartLogger;
import Utility.ActionDesktopBrowser;
import Utility.UtilitiesWebDriver;


public class PriceSearchPageClass {
	
	WebDriver driver;
	ActionDesktopBrowser actionBrowserObj = new ActionDesktopBrowser(driver);
	public PriceSearchPageClass(WebDriver Idriver)
	{
			this.driver=Idriver;
			PageFactory.initElements(driver, this);
	}
		
	@FindBy(how=How.XPATH, using =  "//a[@kdfid='currentPLProductpricesearchTab']//span")
	public WebElement priceSearchTab;
	
	@FindBy(how=How.XPATH, using =  "//label[@kdfid='productPriceGLWHLCat']")
	public WebElement catalogGlobal;
	
	@FindBy(how=How.XPATH, using =  "//label[@kdfid='productPriceDPPCat']")
	public WebElement catalogDistribution;
	
	@FindBy(how=How.XPATH, using =  "//label[@kdfid='productPriceOffCatPAS']")
	public WebElement OfferCatagoryProdServices;
	
	@FindBy(how=How.XPATH, using =  "//label[@kdfid='prdPriceDPPProdLbl']")
	public WebElement OfferCatagoryProductsOnly;
	
	@FindBy(how=How.XPATH, using =  "//label[@kdfid='productPriceOffCatPSM']")
	public WebElement OfferCatagoryProdServiceMappingEnterItemName;
	
	@FindBy(how=How.XPATH, using =  "//label[@kdfid='prdPriceUploadItemLbl']")
	public WebElement OfferCatagoryProdServiceMappingUploadItemList;
	
	@FindBy(how=How.XPATH, using =  "//label[@kdfid='productPriceOffCatSaaS']")
	public WebElement OfferCatagorySubscriptionItem;
	
	@FindBy(how=How.XPATH, using =  "//label[@kdfid='prdPriceDPPProdLbl']")
	public WebElement DistributionCatalogOfferCatagoryProductsOnly;
	
	@FindBy(how=How.XPATH, using =  "//label[@kdfid='prdPriceDPPSOLbl']")
	public WebElement DistributionCatalogOfferCatagoryServicesItem;
	
	@FindBy(how=How.XPATH, using =  "//label[@kdfid='prdPriceDPPSubMAndatoryLbl']")
	public WebElement DistributionCatalogOfferCatagorySubscriptionmandatory;
	
	@FindBy(how=How.XPATH, using =  "//label[@kdfid='prdPriceDPPSaaSLbl']")
	public WebElement DistributionCatalogOfferCatagorySubscriptionItem;
	
	@FindBy(how=How.ID, using =  "selectedPriceListSearch")
	public WebElement priceList;
	
	@FindBy(how=How.XPATH, using =  "//select[@id='selectedDPPPriceListSearch']")
	public WebElement priceFile;
	
	@FindBy(how=How.XPATH, using =  "//label[@kdfid='productPriceOffCatSubMandatoryAttachFlag']")
	public WebElement OfferCatagorySubscriptionmandatory;
	
	@FindBy(how=How.XPATH, using =  "//label[@kdfid='prdPricesearchByProduct']")
	public WebElement contentSearchByProduct;
	
	@FindBy(how=How.XPATH, using =  "//label[@kdfid='prdPriceProdFamily']")
	public WebElement contentProductFamily;
	
	@FindBy(how=How.CSS, using =  "#searchSKUDPP")
	public WebElement offerCatagoryProductsItemNumberTextbox;
	
	@FindBy(how=How.CSS, using =  "#searchDescDPP")
	public WebElement offerCatagoryProductsItemDescriptionTextbox;
	
	@FindBy(how=How.CSS, using =  "#searchSKUDPPSO")
	public WebElement offerCatagoryServiceItemNumberTextbox;
	
	@FindBy(how=How.CSS, using =  "#searchDescDPPSO")
	public WebElement offerCatagoryServiceItemDescriptionTextbox;
	
	@FindBy(how=How.CSS, using =  "#searchSKUDPPSaaS")
	public WebElement offerCatagorySaaSonPremItemNumberTextbox;
	
	@FindBy(how=How.CSS, using =  "#searchDescDPPSaaS")
	public WebElement offerCatagorySaaSonPremItemDescriptionTextbox;
	
	@FindBy(how=How.CSS, using =  "#searchSKUDPP")
	public WebElement offerCatagorySubsMandItemNumberTextbox;
	
	@FindBy(how=How.CSS, using =  "#searchDescDPP")
	public WebElement offerCatagorySubsMandItemDescriptionTextbox;
	
	@FindBy(how=How.ID, using =  "pdtNoRecords")
	public WebElement priceSearchError;
	
	@FindBy(how=How.NAME, using =  "_id20")
	public WebElement backButton;

	@FindBy(how=How.XPATH, using =  "//input[@kdfid='prdPriceSearchviewButton']")
	public WebElement viewButton;
	
	@FindBy(how=How.XPATH, using =  "//div[@class='sbHolder']/a[contains(text(),'Select a Price File')]")
	public WebElement selectMenu;
	
	@FindBy(how=How.XPATH, using =  "//div[@class='sbHolder']//a[contains(text(),'Distribution Pricing Plus US')]")
	public WebElement  priceFileOption;
	
	@FindBy(how=How.XPATH, using =  "//tr[@class='mat-row']/td")
	public List<WebElement> AllRowResultForPriceSearch;
	
	@FindBy(how=How.XPATH, using =  "//tr[@class='mat-row']/td[1]/a")
	public WebElement PriceSearchSubscriptionProductName;
	
	@FindBy(how=How.XPATH, using =  "//iframe[@id='lpcNgPriceSearchIframe']")
	public WebElement iFrameSearchResult;
	
	@FindBy(how=How.XPATH, using =  "//tr[contains(@class,'ev_dhx_skyblue')]/td")
	public List<WebElement> allVisibleSearchResults;
	
	
	
	public void SelectPriceList(HelperClass.Enums.PriceListPriceFile ePriceList)
	{
		Select priceListDropDown = new Select(priceList);
		switch (ePriceList) {
		case GLOBAL_PRICE_LIST_US:
			priceListDropDown.selectByVisibleText("Global Price List US Availability");
			break;
		case WHOLESALE_PRICE_LIST_US:
			priceListDropDown.selectByVisibleText("Wholesale US Price List in US Dollars, 2-tier only");
			break;
		default:
			SmartLogger.PrintError("Wrong Price List Selected");
			break;
		}
	}
	
	public void SetValueForItemNumberAndDescription(String sItemNumber, String sItemDescription) throws Exception
	{
		ActionDesktopBrowser.ImplicitWait(1);
		if(actionBrowserObj.IsElementAvailable(offerCatagoryProductsItemNumberTextbox) && actionBrowserObj.IsElementAvailable(offerCatagoryProductsItemDescriptionTextbox))
		{
			actionBrowserObj.SendTextinTextbox(offerCatagoryProductsItemNumberTextbox, sItemNumber);
			SmartLogger.PrintInfo("Searching Price for Item Number: " + sItemNumber);
			actionBrowserObj.SendTextinTextbox(offerCatagoryProductsItemDescriptionTextbox, sItemDescription);
			SmartLogger.PrintInfo("Item Description Entered: " + sItemDescription);
		}
		else if (actionBrowserObj.IsElementAvailable(offerCatagoryServiceItemNumberTextbox) && actionBrowserObj.IsElementAvailable(offerCatagoryServiceItemDescriptionTextbox))
		{
			actionBrowserObj.SendTextinTextbox(offerCatagoryServiceItemNumberTextbox, sItemNumber);
			SmartLogger.PrintInfo("Searching Price for Item Number: " + sItemNumber);
			actionBrowserObj.SendTextinTextbox(offerCatagoryServiceItemDescriptionTextbox, sItemDescription);
			SmartLogger.PrintInfo("Item Description Entered: " + sItemDescription);
		}
		else if (actionBrowserObj.IsElementAvailable(offerCatagorySaaSonPremItemNumberTextbox) && actionBrowserObj.IsElementAvailable(offerCatagorySaaSonPremItemDescriptionTextbox))
		{
			actionBrowserObj.SendTextinTextbox(offerCatagorySaaSonPremItemNumberTextbox, sItemNumber);
			SmartLogger.PrintInfo("Searching Price for Item Number: " + sItemNumber);
			actionBrowserObj.SendTextinTextbox(offerCatagorySaaSonPremItemDescriptionTextbox, sItemDescription);
			SmartLogger.PrintInfo("Item Description Entered: " + sItemDescription);
		}
		else if (actionBrowserObj.IsElementAvailable(offerCatagorySubsMandItemNumberTextbox) && actionBrowserObj.IsElementAvailable(offerCatagorySubsMandItemDescriptionTextbox))
		{
			actionBrowserObj.SendTextinTextbox(offerCatagorySubsMandItemNumberTextbox, sItemNumber);
			SmartLogger.PrintInfo("Searching Price for Item Number: " + sItemNumber);
			actionBrowserObj.SendTextinTextbox(offerCatagorySubsMandItemDescriptionTextbox, sItemDescription);
			SmartLogger.PrintInfo("Item Description Entered: " + sItemDescription);
		}
		else
			SmartLogger.PrintError("Couldnt set value for Item number and Item Description");
	}
	
	public void SelectPriceFile() throws Exception
	{
		actionBrowserObj.WaitForElementToBeClickable(selectMenu, 15);
		actionBrowserObj.clickOnElement(selectMenu);
		ActionDesktopBrowser.ImplicitWait(1);
		actionBrowserObj.clickOnElement(priceFileOption);
		

	}

	public void ClickOnViewButton() throws Exception
	{
		actionBrowserObj.WaitForElementVisible(viewButton, 20);
		actionBrowserObj.clickOnElement(viewButton);
		SmartLogger.PrintInfo("Clicked on View Button");
	}
	
	public void ClickOnPriceSearchTab() throws Exception
	{
		actionBrowserObj.WaitForElementVisible(priceSearchTab, 30);
		actionBrowserObj.ScrollHorizentalTillElementFound(priceSearchTab);
		actionBrowserObj.clickOnElement(priceSearchTab);
		SmartLogger.PrintInfo("Clicked on Price Search Tab");
	}
	
	public void ClickOnBackButton() throws Exception
	{
		actionBrowserObj.ScrollTillEndOfPage();
		actionBrowserObj.WaitForElementVisible(backButton, 20);
		actionBrowserObj.clickOnElement(backButton);
		SmartLogger.PrintInfo("Clicked on Back Button");
	}
	
	public String GetErrorMessage() throws Exception
	{
		actionBrowserObj.WaitForElementVisible(priceSearchError, 20);
		return actionBrowserObj.GetTextOfElement(priceSearchError);
	}
	
	public void SelectCatalog(HelperClass.Enums.Catalog eCatalog) throws Exception
	{
			switch (eCatalog) {
			case GLOBAL_PRICE_LIST:
				actionBrowserObj.WaitForElementToBeClickable(catalogGlobal, 30);
				actionBrowserObj.clickOnElement(catalogGlobal);
				SmartLogger.PrintInfo("Selected catalog value: " + eCatalog.name());
				break;
			case DISTRIBUTION_PRICING:
				actionBrowserObj.WaitForElementToBeClickable(catalogDistribution, 30);
				actionBrowserObj.clickOnElement(catalogDistribution);
				SmartLogger.PrintInfo("Selected catalog value: " + eCatalog.name());
				break;
			default:
				SmartLogger.PrintError("Wrong Catalog value selected");
				break;
			}
	}
	
	public void SelectOffercatagory(HelperClass.Enums.OfferCatagory eOfferCatagory) throws Exception
	{
		if(actionBrowserObj.getAttributeValue(catalogGlobal, "class").equals("label_radio r_on"))
		{
			switch (eOfferCatagory) {
			case PRODUCT_AND_SERVICES:
				actionBrowserObj.WaitForElementToBeClickable(OfferCatagoryProdServices, 20);
				actionBrowserObj.clickOnElement(OfferCatagoryProdServices);
				SmartLogger.PrintInfo("Selected Offer Catagory value: " + eOfferCatagory.name());
				break;
			case PRODUCT_SERVICE_MAPPING_ENTER_ITEM_NAME:
				actionBrowserObj.WaitForElementToBeClickable(OfferCatagoryProdServiceMappingEnterItemName, 20);
				actionBrowserObj.clickOnElement(OfferCatagoryProdServiceMappingEnterItemName);
				SmartLogger.PrintInfo("Selected Offer Catagory value: " + eOfferCatagory.name());
				break;
			case PRODUCT_SERVICE_MAPPING_UPLOAD_ITEM_LIST:
				actionBrowserObj.WaitForElementToBeClickable(OfferCatagoryProdServiceMappingUploadItemList, 20);
				actionBrowserObj.clickOnElement(OfferCatagoryProdServiceMappingUploadItemList);
				SmartLogger.PrintInfo("Selected Offer Catagory value: " + eOfferCatagory.name());
				break;
			case PRODUCTS_ONLY:
				actionBrowserObj.WaitForElementToBeClickable(OfferCatagoryProductsOnly, 20);
				actionBrowserObj.clickOnElement(OfferCatagoryProductsOnly);
				SmartLogger.PrintInfo("Selected Offer Catagory value: " + eOfferCatagory.name());
				break;
			case SUBSCRIPTION_ITEM:
				actionBrowserObj.WaitForElementToBeClickable(OfferCatagorySubscriptionItem, 20);
				actionBrowserObj.clickOnElement(OfferCatagorySubscriptionItem);
				SmartLogger.PrintInfo("Selected Offer Catagory value: " + eOfferCatagory.name());
				break;
			case SUBSCRIPTION_MANDATORY:
				actionBrowserObj.WaitForElementToBeClickable(OfferCatagorySubscriptionmandatory, 20);
				actionBrowserObj.clickOnElement(OfferCatagorySubscriptionmandatory);
				SmartLogger.PrintInfo("Selected Offer Catagory value: " + eOfferCatagory.name());
				break;
			default:
				SmartLogger.PrintError("Wrong Catagory Selected");
				break;
			}
		}
		else if(actionBrowserObj.getAttributeValue(catalogDistribution, "class").equals("label_radio r_on"))
		{
			switch (eOfferCatagory) {
			case SERVICE_ITEMS:
				actionBrowserObj.WaitForElementToBeClickable(DistributionCatalogOfferCatagoryServicesItem, 20);
				actionBrowserObj.clickOnElement(DistributionCatalogOfferCatagoryServicesItem);
				SmartLogger.PrintInfo("Selected Offer Catagory value: " + eOfferCatagory.name());
				break;
			case PRODUCTS_ONLY:
				actionBrowserObj.WaitForElementToBeClickable(DistributionCatalogOfferCatagoryProductsOnly, 20);
				actionBrowserObj.clickOnElement(DistributionCatalogOfferCatagoryProductsOnly);
				SmartLogger.PrintInfo("Selected Offer Catagory value: " + eOfferCatagory.name());
				break;
			case SUBSCRIPTION_ITEM:
				actionBrowserObj.WaitForElementToBeClickable(DistributionCatalogOfferCatagorySubscriptionItem, 20);
				actionBrowserObj.clickOnElement(DistributionCatalogOfferCatagorySubscriptionItem);
				SmartLogger.PrintInfo("Selected Offer Catagory value: " + eOfferCatagory.name());
				break;
			case SUBSCRIPTION_MANDATORY:
				actionBrowserObj.WaitForElementToBeClickable(DistributionCatalogOfferCatagorySubscriptionmandatory, 20);
				actionBrowserObj.clickOnElement(DistributionCatalogOfferCatagorySubscriptionmandatory);
				SmartLogger.PrintInfo("Selected Offer Catagory value: " + eOfferCatagory.name());
				break;
			default:
				SmartLogger.PrintError("Wrong Catagory Selected");
				break;
			}
		}
		else
			SmartLogger.PrintError("Valid Catalog not selected for Offer Catagory");
	}
		
	public void SelectContent(HelperClass.Enums.Content eContent) throws Exception
	{
		if(actionBrowserObj.getAttributeValue(catalogGlobal, "class").equals("label_radio r_on") && actionBrowserObj.getAttributeValue(OfferCatagoryProdServices, "class").equals("label_radio r_on"))
		{
			switch (eContent) {
			case SEARCH_BY_PRODUCT:
				actionBrowserObj.WaitForElementToBeClickable(contentSearchByProduct, 20);
				actionBrowserObj.clickOnElement(contentSearchByProduct);
				SmartLogger.PrintInfo("Selected Content: " + eContent.name());
				break;
			case SEARCH_BY_PRODUCT_FAMILY:
				actionBrowserObj.WaitForElementToBeClickable(contentProductFamily, 20);
				actionBrowserObj.clickOnElement(contentProductFamily);
				SmartLogger.PrintInfo("Selected Content: " + eContent.name());
				break;
			default:
				SmartLogger.PrintError("Wrong Content Selected");
				break;
			}
		}
		else if (actionBrowserObj.getAttributeValue(catalogGlobal, "class").equals("label_radio r_on") && actionBrowserObj.getAttributeValue(OfferCatagoryProductsOnly, "class").equals("label_radio r_on"))
		{
			switch (eContent) {
			case SEARCH_BY_PRODUCT:
				actionBrowserObj.WaitForElementToBeClickable(contentSearchByProduct, 20);
				actionBrowserObj.clickOnElement(contentSearchByProduct);
				SmartLogger.PrintInfo("Selected Content: " + eContent.name());
				break;
			case SEARCH_BY_PRODUCT_FAMILY:
				actionBrowserObj.WaitForElementToBeClickable(contentProductFamily, 20);
				actionBrowserObj.clickOnElement(contentProductFamily);
				SmartLogger.PrintInfo("Selected Content: " + eContent.name());
				break;
			default:
				SmartLogger.PrintError("Wrong Content Selected");
				break;
			}
		}
		else if(actionBrowserObj.getAttributeValue(catalogGlobal, "class").equals("label_radio r_on") && actionBrowserObj.getAttributeValue(OfferCatagorySubscriptionItem, "class").equals("label_radio r_on"))
		{
			switch (eContent) {
			case SEARCH_BY_PRODUCT:
				actionBrowserObj.WaitForElementToBeClickable(contentSearchByProduct, 20);
				actionBrowserObj.clickOnElement(contentSearchByProduct);
				SmartLogger.PrintInfo("Selected Content: " + eContent.name());
				break;
			case SEARCH_BY_PRODUCT_FAMILY:
				actionBrowserObj.WaitForElementToBeClickable(contentProductFamily, 20);
				actionBrowserObj.clickOnElement(contentProductFamily);
				SmartLogger.PrintInfo("Selected Content: " + eContent.name());
				break;
				default:
					SmartLogger.PrintError("Wrong Content Selected");
					break;
				}
			}
			else
				SmartLogger.PrintError("Correct Offer Catagory not selected for Content Selection");
		}
		

	public String GetPriceCatalogSelection() throws Exception
	{
		if(actionBrowserObj.getAttributeValue(catalogGlobal, "class").equals("label_radio r_on"))
			return "Global";
		else if(actionBrowserObj.getAttributeValue(OfferCatagoryProductsOnly, "class").equals("label_radio r_on"))
			return "Distribution";
		else
			return null;
	}
	
	public String GetGlobalCatalogOfferCatagorySelection() throws Exception
	{
		if(actionBrowserObj.getAttributeValue(OfferCatagoryProdServiceMappingEnterItemName, "class").equals("label_radio r_on"))
			return "Product Service Mapping - Enter Item Name";
		else if(actionBrowserObj.getAttributeValue(OfferCatagoryProdServiceMappingUploadItemList, "class").equals("label_radio r_on"))
			return "Product Service Mapping - Upload Item List";
		else if(actionBrowserObj.getAttributeValue(OfferCatagoryProdServices, "class").equals("label_radio r_on"))
			return "Product & Services";
		else if(actionBrowserObj.getAttributeValue(OfferCatagoryProductsOnly, "class").equals("label_radio r_on"))
			return "Products Only";
		else if(actionBrowserObj.getAttributeValue(OfferCatagorySubscriptionItem, "class").equals("label_radio r_on"))
			return "Subscription Items";
		else if(actionBrowserObj.getAttributeValue(OfferCatagorySubscriptionmandatory, "class").equals("label_radio r_on"))
			return "Subscription Mandatory";
		else
			return null;
	}
	
	public List<String> GetAllPriceSearchResults(HelperClass.Enums.OfferCatagory eOfferCatagory) throws Exception
	{
		List<String> SingleRowResults = new ArrayList<String>();
		actionBrowserObj.WaitForElementVisible(iFrameSearchResult, 10);
		actionBrowserObj.SwitchDriverControlToFrame("lpcNgPriceSearchIframe");
		switch (eOfferCatagory) {
		case PRODUCTS_ONLY:
			for (WebElement element : AllRowResultForPriceSearch) {
				SingleRowResults.add(element.getText());
				}
			break;
		case SERVICE_ITEMS:
			for (WebElement element : AllRowResultForPriceSearch) {
				SingleRowResults.add(element.getText());
				}
			break;
		case SUBSCRIPTION_ITEM:
			for (WebElement element : AllRowResultForPriceSearch) {
				SingleRowResults.add(element.getText());
				}
			SingleRowResults.set(0, PriceSearchSubscriptionProductName.getText());
			break;
		case SUBSCRIPTION_MANDATORY:
			for (WebElement element : AllRowResultForPriceSearch) {
				SingleRowResults.add(element.getText());
				}
			break;
		default:
			SmartLogger.PrintInfo("Wrong Offer Catagory is Selected");
			break;
		}
		actionBrowserObj.SwitchBackDriverContrlFromFrame();
		return SingleRowResults;
	}
}