package com.ui.tests;

import static com.constants.Browser.CHROME;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utilitiy.BrowserUtility;
import com.utilitiy.LambdaTestUtility;
import com.utilitiy.LoggerUtility;

public class TestBase {
	//protected HomePage homePage;
	private static ThreadLocal<HomePage> homePageThreadLocal = new ThreadLocal<>();
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private boolean isLambdaTest;
	//private boolean isHeadless = true;

	@Parameters({"browser","isLambdaTest","isHeadless"})
	@BeforeMethod(description="Load the Homepage of the website")
	public void setup(
			@Optional("chrome") String browser,
			@Optional("false") boolean isLambdaTest,
			@Optional("true") boolean isHeadless, 
			ITestResult result) {
		
		this.isLambdaTest = isLambdaTest;
		WebDriver lambdaDriver;
		if(isLambdaTest) { //Test run on cloud
			lambdaDriver = LambdaTestUtility.initializelambdaTestSessions(result.getMethod().getMethodName(), browser);
			HomePage homePage = new HomePage(lambdaDriver);
		}
		
		else {//Running test on local machine
		logger.info("Load the Homepage setup");
		//homePage = new HomePage(CHROME);		
		HomePage homePage = new HomePage(Browser.valueOf(browser.toUpperCase()),isHeadless);
        homePageThreadLocal.set(homePage);
		}
	}
	
	public BrowserUtility getInstance() { //Parent class reference
		//return homePage;
		 return homePageThreadLocal.get();
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if(isLambdaTest) {
			LambdaTestUtility.quitSession();
		}else {
			 HomePage homePage = homePageThreadLocal.get(); //quit or close browser session on lambda test
			if (homePage != null) {
				logger.info("Closing the browser for thread " + Thread.currentThread().getId());
		        homePage.quitBrowser();
		        homePageThreadLocal.remove();
		    }
		    else {
	        logger.warn("HomePage was null for thread: " + Thread.currentThread().getId());
	       }
	}
	}		
}
