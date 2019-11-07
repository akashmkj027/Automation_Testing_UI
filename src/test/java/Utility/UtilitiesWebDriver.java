package Utility;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import HelperClass.ReadPropertyFile;
import HelperClass.SmartLogger;


public class UtilitiesWebDriver {
	
	public static WebDriver driver1;
	public static ReadPropertyFile readPropertyFile;
	public static SmartLogger logger = new SmartLogger();
	public static WebDriver OpenBrowser(WebDriver driver) throws Exception{
	readPropertyFile= new ReadPropertyFile();
	if(readPropertyFile.GetBrowserName().equalsIgnoreCase("firefox"))
	{
			System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "\\Resources\\Drivers\\geckodriver.exe"); 
			driver = new FirefoxDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			driver1=driver;
			return driver;
	}
	else if(readPropertyFile.GetBrowserName().equalsIgnoreCase("chrome"))
	{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\Resources\\Drivers\\chromedriver_v78.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			driver1=driver;
			logger.PrintInfo("Browser Selected: " + driver);
			return driver;
	}
		else if(readPropertyFile.GetBrowserName().equalsIgnoreCase("Edge"))
		{
			System.setProperty("webdriver.edge.driver",System.getProperty("user.dir") + "\\Resources\\Drivers\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			driver1=driver;
			logger.PrintInfo("Browser Selected: " + driver);
			return driver;
		}
		else if(readPropertyFile.GetBrowserName().equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir") + "\\Resources\\Drivers\\IEDriverServer.exe");
			driver = new EdgeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			driver1=driver;
			logger.PrintInfo("Browser Selected: " + driver);
			return driver;
		}
		else{
			
			throw new Exception("Browser name is not correct");
		}
		
	}
	
	public static void GetApplicationURL(WebDriver driver) throws InterruptedException, IOException
	{
		readPropertyFile= new ReadPropertyFile();
		ActionDesktopBrowser actionDesktopBrowser = new ActionDesktopBrowser(GetWebDriverInstance());
		String environment = readPropertyFile.GetExecutionEnvironment();
		if(environment.equalsIgnoreCase("TS1"))
		{
			driver.get(readPropertyFile.GetTS1BrowserURL());
			logger.PrintInfo("Environment Name: " + environment);
			actionDesktopBrowser.HardWait(3);
		}
		else if (environment.equalsIgnoreCase("TS3"))
		{
			driver.get(readPropertyFile.GetTS3BrowserURL());
			logger.PrintInfo("Environment Name: " + environment);
			Thread.sleep(2000);
		}
	}
	
	public static void KillWebDriverInstance(WebDriver driver)
	{
		driver.close();
		logger.PrintInfo("Closing Driver: " + driver.toString());
		driver.quit();
		logger.PrintInfo("Closing Browser");
	}
	
	public static void ExplicitWait(int seconds) throws InterruptedException
	{
		Thread.sleep(seconds*1000);
	}
	
	public String GetCurrentUrl(WebDriver driver)
    {
        return driver.getCurrentUrl();
    }

	public static void TakeScreenshot(String sTestcaseID) throws IOException
	{
		File screenshot =((TakesScreenshot)driver1).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(Common.GetScreenshotPath() + "\\" + sTestcaseID + ".png"));
	}
	
	public static WebDriver GetWebDriverInstance()
	{
		return driver1;
	}

}
