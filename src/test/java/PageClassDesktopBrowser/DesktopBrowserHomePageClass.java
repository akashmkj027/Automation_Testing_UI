package PageClassDesktopBrowser;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Utility.ActionDesktopBrowser;
import Utility.UtilitiesWebDriver;


public class DesktopBrowserHomePageClass {
	
	WebDriver driver;
	ActionDesktopBrowser actionBrowserObj = new ActionDesktopBrowser(driver);
	public DesktopBrowserHomePageClass(WebDriver Idriver)
	{
			this.driver=Idriver;
			PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(how=How.XPATH, using =  "//input[@kdfid='btn_706']")
	public WebElement ImportRequestButton;
	
	@FindBy(how=How.XPATH, using =  "//input[@kdfid='txt_file']")
	public WebElement ChooseFileButton;
	
	@FindBy(how=How.XPATH, using =  "//input[@kdfid='chk_53']")
	public WebElement ImportRightNowCheckbox;
	
	@FindBy(how=How.XPATH, using =  "//input[@kdfid='btn_attchBttn']")
	public WebElement ImportRequestUploadButton;
	
	@FindBy(how=How.ID, using =  "errorMSG")
	public WebElement importRequestErrorMessage;
	
	public ArrayList<String> FetchAllRequestIDVisibleOnScreen(String requestStatus) throws Exception
	{
		
		List<WebElement> allRequestOnScreen = AllRequestVisibleOnScreen(requestStatus);
		ArrayList<String> requestID = new ArrayList<String>();
		if(allRequestOnScreen.size()!=0)
			{
			for (WebElement element : allRequestOnScreen) {
				requestID.add(element.getText());
				}
			return requestID;
			}
		else
			return requestID=null;
	}
	
	public void SelectShowTypeDropDown(String selectedValue) throws Exception
	{
		actionBrowserObj.WaitForElementVisible(driver.findElement(By.name("filterValue")), 10);
		Select selectShowTypeDropDown = new Select(driver.findElement(By.name("filterValue")));
		selectShowTypeDropDown.selectByVisibleText(selectedValue);
	}
	
		
	public void ClickOnSpecificRequestID(String requestID) throws Exception
	{
		actionBrowserObj.WaitForElementVisible(driver.findElement(By.xpath("//a[contains(text(),'" + requestID + "')]")), 10);
		actionBrowserObj.clickOnElement(driver.findElement(By.xpath("//a[contains(text(),'" + requestID + "')]")));
	}
	
	public List<WebElement> AllRequestVisibleOnScreen(String requestStatus)
	{
		return driver.findElements(By.xpath("//td[contains(text(),'" + requestStatus + "')]//parent::tr//td//span//a"));
	}
	
	public void ClickOnImportRequestButton() throws Exception
	{
		actionBrowserObj.WaitForElementVisible(ImportRequestButton, 10);
		actionBrowserObj.clickOnElement(ImportRequestButton);
	}
	
	public void UploadFileToImport(HelperClass.Enums.FileType fileType)
	{
		switch (fileType) 
		{
			case XML:
				ChooseFileButton.sendKeys(System.getProperty("user.dir") + "\\Resources\\TestDataFiles\\EndCustomer_Partner_Disocunt_L2N_req.xml");
					break;
			case EXCEL:
					ChooseFileButton.sendKeys(System.getProperty("user.dir") + "\\Resources\\TestDataFiles\\Oct MR and Q2FY20 Scopes.xlsx");
				    break;
			default:
					System.out.println("INVALID FILE TYPE");
					break;
		}
		
	}
	
	public void ClickOnImportRightNow() throws Exception
	{
		actionBrowserObj.clickOnElement(ImportRightNowCheckbox);
	}
	
	public void ClickOnImportRequestUploadButton() throws Exception
	{
		actionBrowserObj.clickOnElement(ImportRequestUploadButton);
	}
	
	public String FetchImportRequestErrorMessage() throws Exception
	{
		return actionBrowserObj.GetTextOfElement(importRequestErrorMessage);
	}	
}
