package Utility;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionDesktopBrowser {

	WebDriver driver;
	
	public ActionDesktopBrowser(WebDriver Idriver)
	{
			this.driver=Idriver;
	}
	
	public void clickOnElement(WebElement element) throws Exception
	{
		if(element.isDisplayed())
		    {
			element.click();
		    }
		else
		{
			WaitForElementVisible(element, 10);
			if(element.isDisplayed())
		    {
			element.click();
		    }
		}
	}
	
	
	public void SendTextinTextbox(WebElement element, String text) throws Exception
	{
		if(IsElementAvailable(element))
		{
			ClearTextBox(element);
			element.sendKeys(text);
		}
		else 
		{
			WaitForElementVisible(element, 10);
			if(IsElementAvailable(element))
			{
				ClearTextBox(element);
				element.sendKeys(text);
			}
		}
	}
	
	public String GetTextOfElement(WebElement element) throws Exception
	{
		if(IsElementAvailable(element))
		{
			return element.getText();
		}
		else {
			WaitForElementVisible(element, 10);
			if(IsElementAvailable(element))
			{
				return element.getText();
			}
			System.out.println("Element not found");
			return "XXXX-----JUNK -----XXXX";
		}
	}
	
	public boolean IsElementAvailable(WebElement element) throws Exception
    {
        try
        {
            if (element.isDisplayed() && element.isEnabled())
                return true;
        }
        catch (Exception e)
        {
        	System.out.println("Exception caused: " + e);
            return false;
        }
        return false;
    }
	
	public boolean ElementEnabled(WebElement element) throws Exception
    {
        try
        {
            if (element.isEnabled())
                return true;
        }
        catch (Exception e)
        {
            return false;
        }
        return false;
    }
	
		public String getAttributeValue(WebElement element, String attribute) throws Exception
	    {
	        return element.getAttribute(attribute);
	    }
		
	 public String GetCssValue(WebElement element, String attribute) throws Exception
     {
         return element.getCssValue(attribute);
     }
	 
	 public void ClearTextBox(WebElement element)
     {
         element.sendKeys(Keys.END);
         element.sendKeys(Keys.DELETE);
         element.clear();
     }
	 
	  public String GetSelectedValueFromDropDown(WebElement element) throws Exception
      {
          Select selectedValue = new Select(element);
          String SelectedText = selectedValue.getAllSelectedOptions().toString();
          return SelectedText;
      }
	  
	  public void MoveToElementIfElementAvailable(WebElement element) throws Exception
      {
                  Actions actions = new Actions(driver);
                  actions.moveToElement(element);
                  actions.perform();
      }
	  
	  public void DragAndDropElement(WebElement sourceElement, WebElement destinationElement) throws Exception
	  {
		  Actions action = new Actions(driver);
		  if(sourceElement.isDisplayed() && destinationElement.isDisplayed())
		    {
			  action.dragAndDrop(sourceElement, destinationElement).perform();
		    }
		else
		{
			WaitForElementVisible(sourceElement, 10);
			 if(sourceElement.isDisplayed() && destinationElement.isDisplayed())
			    {
				  action.dragAndDrop(sourceElement, destinationElement).perform();
			    }

		}
		  
	  }
	  
		public void WaitForElementVisible(WebElement element, int seconds) throws Exception
		{
			driver = UtilitiesWebDriver.GetWebDriverInstance();
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		
		public void WaitForElementToBeClickable(WebElement element, int seconds) throws Exception
		{
			driver = UtilitiesWebDriver.GetWebDriverInstance();
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
	  
		public static void ImplicitWait(int seconds) throws Exception
		{
			UtilitiesWebDriver.GetWebDriverInstance().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
		}
}