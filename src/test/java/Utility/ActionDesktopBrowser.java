package Utility;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import HelperClass.SmartLogger;

public class ActionDesktopBrowser {

	public WebDriver driver;
	
	public ActionDesktopBrowser(WebDriver Idriver)
	{
			this.driver=Idriver;
	}
	
	public void clickOnElement(WebElement element) throws Exception
	{
			element.click();
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
        	SmartLogger.PrintError("Exception caused: " + e);
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

		public void ScrollVerticalTillElementFound(WebElement element)
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		}
		
		public void ScrollTillEndOfPage()
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		}
		
		public void ScrollHorizentalTillElementFound(WebElement element)
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		}
		
		public void SwitchDriverControlToFrame(String iFrameID)
		{
			driver.switchTo().frame(iFrameID);
		}

		public void SwitchBackDriverContrlFromFrame()
		{
			driver.switchTo().defaultContent();
		}
		
		public boolean IsElementDisplayed(List<WebElement> element)
		{
			if(element.size()==0)
				return true;
			else
				return false;
		}
}