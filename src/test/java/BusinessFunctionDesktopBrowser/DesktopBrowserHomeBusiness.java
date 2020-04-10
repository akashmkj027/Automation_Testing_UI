package BusinessFunctionDesktopBrowser;

import java.util.ArrayList;
import HelperClass.ReadPropertyFile;
import PageClassDesktopBrowser.DesktopBrowserHomePageClass;
import PageClassDesktopBrowser.DesktopBrowserReviewAndSubmitPageClass;
import TestCaseDesktopBrowser.automation.HomeTestCases;
import Utility.ActionDesktopBrowser;
import Utility.UtilitiesWebDriver;
import Validation.AssertionsDesktopBrowser;


public class DesktopBrowserHomeBusiness {
	
	public static ReadPropertyFile readPropertyFileObject; 
	
	public static Boolean OpenUserCreatedRequest(String requestStatus) throws Exception
	{
		DesktopBrowserHomePageClass DesktopBrowserHomePageObj = new DesktopBrowserHomePageClass(HomeTestCases.driver);
		DesktopBrowserReviewAndSubmitPageClass DesktopBrowserReviewAndSubmitPageObj = new DesktopBrowserReviewAndSubmitPageClass(HomeTestCases.driver);
		ArrayList<String> requestID = new ArrayList<String>();
		DesktopBrowserHomePageObj.SelectShowTypeDropDown(requestStatus);
		ActionDesktopBrowser.ImplicitWait(3);
		requestID = DesktopBrowserHomePageObj.FetchAllRequestIDVisibleOnScreen(requestStatus);
		System.out.println("No. Of Request(s) having " + requestStatus + " Status: " + requestID.size());
		if(requestID.size()!=0)
		{
			if(requestStatus.equalsIgnoreCase("Completed"))
			{
				DesktopBrowserHomePageObj.ClickOnSpecificRequestID(requestID.get(0).toString());
				ActionDesktopBrowser.ImplicitWait(3);
				AssertionsDesktopBrowser.AssertBoolean(DesktopBrowserReviewAndSubmitPageObj.CheckRenewButtonExists(), true);
				AssertionsDesktopBrowser.AssertBoolean(DesktopBrowserReviewAndSubmitPageObj.CheckModifyButtonExists(), true);
				return true;
			}
			else if (requestStatus.equalsIgnoreCase("Pending Submission"))
			{
				DesktopBrowserHomePageObj.ClickOnSpecificRequestID(requestID.get(0).toString());
				ActionDesktopBrowser.ImplicitWait(3);
				AssertionsDesktopBrowser.ElementTextAssert("Your request is not ready for submission; please review the information", DesktopBrowserReviewAndSubmitPageObj.GetTextsubmissionErrorMessage());
				return true;
			}
			else
			{
				System.out.println("INVALID REQUEST TYPE SELECTED");
				return false;
			}
		}
		else
		{
			AssertionsDesktopBrowser.AssertFailMessage("No Request ID found with " + requestStatus +" Status: " + requestID.size());
			return false;
		}	
	}
	
	public static void CreateDiscountRequestViaExcelUpload(HelperClass.Enums.FileType fileType ) throws Exception 
	{
		
		DesktopBrowserHomePageClass DesktopBrowserHomePageObj = new DesktopBrowserHomePageClass(HomeTestCases.driver);
		
		DesktopBrowserHomePageObj.ClickOnImportRequestButton();
		UtilitiesWebDriver.ExplicitWait(2);
		switch (fileType) 
		{
			case XML:
					DesktopBrowserHomePageObj.UploadFileToImport(fileType);
					break;
			case EXCEL:
					DesktopBrowserHomePageObj.UploadFileToImport(fileType);
				    break;
			default:
					System.out.println("INVALID FILE TYPE");
					break;
		}
		UtilitiesWebDriver.ExplicitWait(2);
		DesktopBrowserHomePageObj.ClickOnImportRightNow();
		UtilitiesWebDriver.ExplicitWait(1);
		DesktopBrowserHomePageObj.ClickOnImportRequestUploadButton();
		UtilitiesWebDriver.ExplicitWait(3);
	}
	
	public static Boolean ValidateErrorMessage() throws Exception
	{
		DesktopBrowserHomePageClass DesktopBrowserHomePageObj = new DesktopBrowserHomePageClass(HomeTestCases.driver);
		if(AssertionsDesktopBrowser.ElementTextAssert("Please change the sheet name to 'Customer Specific' ....Your file could not be uploaded. Please try and re-upload the file. If the issue persists please contact your system administrator.", DesktopBrowserHomePageObj.FetchImportRequestErrorMessage()))
			return true;
		else if((AssertionsDesktopBrowser.ElementTextAssert("File format not supported, please upload a file with .xls or .xlsx extension....Your file could not be uploaded. Please try and re-upload the file. If the issue persists please contact your system administrator.", DesktopBrowserHomePageObj.FetchImportRequestErrorMessage())))
			return true;
		else 
			return false;
	}
}
