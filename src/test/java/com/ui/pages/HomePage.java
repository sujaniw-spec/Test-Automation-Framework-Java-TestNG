package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import com.constants.Env;
import com.utilitiy.BrowserUtility;
import com.utilitiy.JSONUtility;
import com.utilitiy.LoggerUtility;
import com.utilitiy.PropertiesUtil;

public final class HomePage extends BrowserUtility {
	
	private static final By SIGN_IN_LOCATOR = By.xpath("//a[contains(text(),'Sign in')]");
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	public HomePage(Browser browser,boolean headless) {
		super(browser,headless);
		//goToWebSite(PropertiesUtil.readProperites(Env.QA, "URL"));
		goToWebSite(JSONUtility.readJSON(Env.QA).getUrl());		
	}
	
	public HomePage(WebDriver driver) {
		super(driver);
		goToWebSite(JSONUtility.readJSON(Env.QA).getUrl());		
	}
	
	public LoginPage goToLoginPage() { // Page Functions
		logger.info("Trying to perform click go to sign in Page");
		clickOn(SIGN_IN_LOCATOR);
		
		return new LoginPage(getDriver());
	}

}
