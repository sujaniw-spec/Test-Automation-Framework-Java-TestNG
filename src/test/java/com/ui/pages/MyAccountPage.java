package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utilitiy.BrowserUtility;

public final class MyAccountPage extends BrowserUtility {
	private static final By USER_NAME_LOCATOR = By.className("account");
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public String getUserName() {
		return getVisibleTest(USER_NAME_LOCATOR);
	}

}
