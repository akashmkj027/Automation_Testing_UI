package PageClass;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import HelperClass.SmartLogger;
import Utility.ActionDesktopBrowser;


public class LoginPageClass {
	
	WebDriver driver;
	public LoginPageClass(WebDriver Idriver)
	{
			this.driver=Idriver;
			PageFactory.initElements(driver, this);
	}
	ActionDesktopBrowser actionBrowserObj = new ActionDesktopBrowser(driver);
	
	@FindBy(how=How.ID, using =  "userInput")
	public WebElement userNameTextbox;
	
	@FindBy(how=How.ID, using =  "password")
	public WebElement passwordTextbox;
	
	@FindBy(how=How.ID, using =  "login-button")
	public WebElement loginButton;
	
	@FindBy(how=How.ID, using =  "kc-login")
	public WebElement SignInButton;
	
	@FindBy(how=How.XPATH, using =  "//span[@id='cdc-loggedin-msg']")
	public WebElement loggedInUser;
	
	@FindBy(how=How.XPATH, using =  "//p[@class='msg idNull']")
	public WebElement InvalidUserErrorMessage;
	
	@FindBy(how=How.XPATH, using =  "//h3[contains(text(),\"That login didn't work:\")]")
	public WebElement InvalidPasswordErrorMessage;
	
	@FindBy(how=How.CSS, using =  "#cdc-loggedin-msg")
	public List<WebElement> allLoggedInUser;
	
	
	public void SetValueForUserNameTextbox(String sUserName) throws Exception
	{
		actionBrowserObj.WaitForElementVisible(userNameTextbox, 20);
		actionBrowserObj.SendTextinTextbox(userNameTextbox, sUserName);
		SmartLogger.PrintInfo("Logging via Username: " + sUserName);
	}
		
	public void SetValueForpasswordTextbox(String sPassword) throws Exception
	{
		actionBrowserObj.WaitForElementVisible(passwordTextbox, 35);
		actionBrowserObj.SendTextinTextbox(passwordTextbox, sPassword);
		SmartLogger.PrintInfo("Password Entered: ********");
	}
		
	public void ClickOnloginButton() throws Exception
	{
		actionBrowserObj.WaitForElementToBeClickable(loginButton, 20);
		actionBrowserObj.clickOnElement(loginButton);
		SmartLogger.PrintInfo("Clicked on Login Button");
	}
	
	public void ClickOnSignInButton() throws Exception
	{
		actionBrowserObj.WaitForElementToBeClickable(SignInButton, 20);
		actionBrowserObj.clickOnElement(SignInButton);
		SmartLogger.PrintInfo("Clicked on Sign in Button");
	}
	
	public String FetchLoggedUserName() throws Exception
	{
		actionBrowserObj.WaitForElementVisible(loggedInUser, 30);
		return actionBrowserObj.GetTextOfElement(loggedInUser);	
	}
	
	public Boolean IsApplicationLogged()
	{
		if(actionBrowserObj.IsElementDisplayed(allLoggedInUser))
			return true;
		else
			return false;
	}
	
	public String FetchInvalidUserErrorMessage() throws Exception
	{
		actionBrowserObj.WaitForElementVisible(InvalidUserErrorMessage, 20);
		return actionBrowserObj.GetTextOfElement(InvalidUserErrorMessage);	
	}
	
	public String FetchInvalidPasswordErrorMessage() throws Exception
	{
		actionBrowserObj.WaitForElementVisible(InvalidPasswordErrorMessage, 20);
		return actionBrowserObj.GetTextOfElement(InvalidPasswordErrorMessage);
	}
	
	
	
}
