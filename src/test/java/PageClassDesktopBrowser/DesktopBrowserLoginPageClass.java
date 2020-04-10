package PageClassDesktopBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import Utility.ActionDesktopBrowser;


public class DesktopBrowserLoginPageClass {
	
	WebDriver driver;
	public DesktopBrowserLoginPageClass(WebDriver Idriver)
	{
			this.driver=Idriver;
			PageFactory.initElements(driver, this);
	}
	ActionDesktopBrowser actionBrowserObj = new ActionDesktopBrowser(driver);
	
	@FindBy(how=How.ID, using =  "userInput")
	public WebElement userNameTextbox;
	
	@FindBy(how=How.ID, using =  "passwordInput")
	public WebElement passwordTextbox;
	
	@FindBy(how=How.ID, using =  "login-button")
	public WebElement loginButton;
	
	@FindBy(how=How.XPATH, using =  "//span[@class='loggedin']")
	public WebElement loggedInUser;
	
	@FindBy(how=How.XPATH, using =  "//p[@class='msg idNull']")
	public WebElement InvalidUserErrorMessage;
	
	@FindBy(how=How.XPATH, using =  "//h3[contains(text(),\"That login didn't work:\")]")
	public WebElement InvalidPasswordErrorMessage;
	
	public void SetValueForUserNameTextbox(String sUserName) throws Exception
	{
		actionBrowserObj.WaitForElementVisible(userNameTextbox, 10);
		actionBrowserObj.SendTextinTextbox(userNameTextbox, sUserName);
	}
		
	public void SetValueForpasswordTextbox(String sPassword) throws Exception
	{
		actionBrowserObj.WaitForElementVisible(passwordTextbox, 10);
		actionBrowserObj.SendTextinTextbox(passwordTextbox, sPassword);
	}
		
	public void ClickOnloginButton() throws Exception
	{
		actionBrowserObj.WaitForElementToBeClickable(loginButton, 10);
		actionBrowserObj.clickOnElement(loginButton);
	}
	
	public String FetchLoggedUserName() throws Exception
	{
		actionBrowserObj.WaitForElementVisible(loggedInUser, 10);
		return actionBrowserObj.GetTextOfElement(loggedInUser);	
	}
	
	public String FetchInvalidUserErrorMessage() throws Exception
	{
		actionBrowserObj.WaitForElementVisible(InvalidUserErrorMessage, 10);
		return actionBrowserObj.GetTextOfElement(InvalidUserErrorMessage);	
	}
	
	public String FetchInvalidPasswordErrorMessage() throws Exception
	{
		actionBrowserObj.WaitForElementVisible(InvalidPasswordErrorMessage, 10);
		return actionBrowserObj.GetTextOfElement(InvalidPasswordErrorMessage);
	}
	
	
	
}
