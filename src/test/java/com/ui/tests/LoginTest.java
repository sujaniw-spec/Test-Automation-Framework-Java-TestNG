package com.ui.tests;

import static com.constants.Browser.*;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pages.MyAccountPage;
import com.ui.pojo.User;
import com.utilitiy.BrowserUtility;
import com.utilitiy.LoggerUtility;

import org.apache.logging.log4j.Logger;

@Listeners(com.ui.listerners.TestListener.class)
public class LoginTest extends TestBase{
	
	//HomePage homePage;
	Logger logger = LoggerUtility.getLogger(this.getClass());
	//HomePage homePage = (HomePage) getInstance();
	
	
	@Test(description="Verify with the valid user is able to login into application", groups = {"e2e","sanity"}
	,dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, 
	dataProvider="LoginTestDataProvider")
	public void loginTest(User user) {
		/*
		 * Test Method!!
		 * 1. Test script small
		 * 2. You cannot have conditional statements, loops, try catch in your test methods
		 * 3. TestScripts ----> Only have tests steps
		 * 4. Reduce the use of local variables
		 * 5. Should have atleast one assertion
		 * */
		HomePage homePage = (HomePage) getInstance();		
		homePage.maximizeBrowser();
		
//		assertEquals(homePage.goToLoginPage().doLoginWith("yopes21588@gddcorp.com", "password")
//				.getUserName(),"Sujani Wijesundera");	
//		
		MyAccountPage myAccount = homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword());
	/*	try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		assertEquals(myAccount.getUserName()
				,"Sujani Wijesundera");	
			
	}
	/*
	@Test(description="Verify with the valid user is able to login into application using CSV data", groups = {"e2e","sanity"}
	,dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, 
	dataProvider="loginCSVDataProvider")
	public void loginCSVTest(User user) {
		HomePage homePage = (HomePage) getInstance();		
		homePage.maximizeBrowser();
		
		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName()
				,"Sujani Wijesundera");				
	}
	
	@Test(description="Verify with the valid user is able to login into application using XLSX data", groups = {"e2e","sanity"}
	,dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, 
	dataProvider="loginXlsxDataProvider", retryAnalyzer = com.ui.listerners.MyRetryAnalyzer.class)
	public void loginXlsxTest(User user) {
		HomePage homePage = (HomePage) getInstance();		
		homePage.maximizeBrowser();
		//logger.info("Started my login execel test");
		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName()
				,"Sujani Wijesundera");	
		//logger.info("login execel test completed!!");
			
	}*/

}
