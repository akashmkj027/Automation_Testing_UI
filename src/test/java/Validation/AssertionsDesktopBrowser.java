package Validation;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import HelperClass.SmartLogger;
import Utility.UtilitiesWebDriver;

public class AssertionsDesktopBrowser {

	
	public static boolean ElementTextAssert(String expectedText, String actualText, String errorMessage) throws Exception
	{	
		if(expectedText.equalsIgnoreCase(actualText))
			return true;
		else
			return false;
	}
	
	public static boolean ElementTextAssert(String expectedText, String actualText) throws Exception
	{
		if(expectedText.equalsIgnoreCase(actualText))
			return true;
		else
			return false;
	}
	
	public static void AssertFail() throws Exception
	{
		Assert.fail();
	}
	
	public static void AssertFailMessage(String message) throws Exception
	{
		Assert.fail(message);
	}
	
	public static void AssertBoolean(boolean statusActual, boolean statusExpected) throws Exception
	{
		Assert.assertEquals(statusActual, statusExpected);
	}
	
	public static void AssertTrue(boolean condition) throws Exception
	{
		Assert.assertTrue(condition);
	}
	
	public static boolean AssertValueFromArrayStringList(ArrayList<String> sDataList, String sSearchData) throws Exception
	{
		if (sDataList.contains(sSearchData))
			return true;
		else
			return false;
	}
	
	/**
	 * Compare and Asserts the UI results and MasterData from Master data Base
	 * @param sMasterDBdata holds the List of data in String type, which is fetched from Master databse
	 * @param sResultUIdata holds the List of Data in String type, which is fetched from UI
	 * @return boolean if all values fetched from UI matches with Back end data
	 */
	public static boolean AssertValueFromMasterDataBase(ArrayList<String> sMasterDBdata, ArrayList<String> sResultUIdata, String sErrorMessage) throws Exception
	{
		int iAssertCount=0;
		for(int i=0;i<sResultUIdata.size();i++)
		{
			if(sMasterDBdata.contains(sResultUIdata.get(i)) && sResultUIdata.get(i)!=null)
				iAssertCount++;
		}
		if(iAssertCount==sMasterDBdata.size())
			return true;
		else
		{
			SmartLogger.PrintError(sErrorMessage);
			return false;
		}	
	}
	
	public static boolean AssertMasterDBvalueFromUI(ArrayList<String> sMasterDBdata, ArrayList<String> sResultUIdata, String sErrorMessage) throws Exception
	{
		boolean status=false;
		for(int i=0;i<sMasterDBdata.size();i++)
		{
			if(sResultUIdata.contains(sMasterDBdata.get(i)) && sMasterDBdata.get(i)!="" && sMasterDBdata.get(i)!=" ")
				status = true;
			else
				{
					status=false;
					SmartLogger.PrintError(sResultUIdata.get(i) + " in UI doesnt match with Backend Data: " + sMasterDBdata.get(i));
					break;
				}
		}
		return status;
	}
	
	public static boolean AssertElementDisplayed(WebElement element)
	{
		if(element.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 * Method to assert String contains SubString
	 * @param sFullString stores full string, which contains Sub String 
	 * @param sSubString stores the sub String 
	 * @return true if sFullString contains sSubString
	 */
	public static boolean AssertStringContains(String sFullString, String sSubString)
	{
		if(sFullString.contains(sSubString))
			return true;
		else
			return false;
	}
	
	public static ArrayList<String> AssertValueAndStoreTheMissing(ArrayList<String> sStringList1, ArrayList<String> sStringList2, String sErrorMessage) throws Exception
	{
		ArrayList<String> sStringList3= new ArrayList<String>();
		for(int i=0;i<sStringList2.size();i++)
		{
			if(sStringList1.contains(sStringList2.get(i)) && sStringList2.get(i)!=null)
				sStringList3.add(sStringList2.get(i));
			else
				sStringList3.add(sStringList2.get(i));
		}
		return sStringList3;
	}
}
