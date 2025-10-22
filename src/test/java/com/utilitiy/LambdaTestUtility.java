package com.utilitiy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdaTestUtility {
	public static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
	private static ThreadLocal<WebDriver> webDriverLocal = new ThreadLocal<WebDriver>();
	private static ThreadLocal <DesiredCapabilities> capabilitiesLocal = new ThreadLocal<DesiredCapabilities>();
	
	public static WebDriver initializelambdaTestSessions(String testName, String bowserName) {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", bowserName);
        capabilities.setCapability("browserVersion", "127");
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user","sujani75");
        ltOptions.put("accessKey", "tVGXMjV3b8qY5DEtbTMLuDGhDlcvsEI8riKMZrGzBYt3NF493O");
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", testName);
        //ltOptions.put("name", this.getClass().getName());
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.23.0");
        capabilities.setCapability("LT:Options", ltOptions);
        
        capabilitiesLocal.set(capabilities);

        WebDriver driver =null;
		try {
			driver = new RemoteWebDriver(new URL(HUB_URL), capabilitiesLocal.get());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        webDriverLocal.set(driver);
        
        return webDriverLocal.get();
	}
	
	public static void quitSession() {
		if(webDriverLocal.get() !=null) {
			webDriverLocal.get().quit();
		}
	}
	
}
