package com.utilitiy;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

public abstract class BrowserUtility {
	
	//private WebDriver driver; //instance variable - heap
	
	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(); //instance variable - heap
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	
	public WebDriver getDriver() {
		//return driver;
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		//this.driver = driver; //Initialize the instance variable
		this.driver.set(driver);
	}
	
	public BrowserUtility(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			//driver = new ChromeDriver();
			driver.set(new ChromeDriver());
		}
		if(browserName.equalsIgnoreCase("edge")) {
		//	driver = new EdgeDriver();
			driver.set(new EdgeDriver());
		}
		else {
			System.err.print("Invalid browser");
			
		}
			
	}
	
	public BrowserUtility(Browser browserName) {
		logger.info("Launching Browser for "+browserName);
		WebDriver webDriver = null;
		if(browserName == Browser.CHROME) {
			//driver = new ChromeDriver();
			//driver.set(new ChromeDriver());
			webDriver = new ChromeDriver();
		}
		else if(browserName == Browser.EDGE) {
			//driver = new EdgeDriver();
			//driver.set(new EdgeDriver());
			webDriver = new EdgeDriver();
		}
		else if(browserName == Browser.FIREFOX) {
			//driver = new FirefoxDriver();
			//driver.set(new FirefoxDriver());
			webDriver = new FirefoxDriver();
		}
		else {
			logger.error("Invalid browser");
		}
		driver.set(webDriver);
	}
	
	
	public BrowserUtility(Browser browserName,boolean isHeadless) {
		logger.info("Launching Browser for "+browserName);
		WebDriver webDriver = null;
		if(browserName == Browser.CHROME) {
			if(isHeadless) {
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--headless=old");
			 options.addArguments("--headless=new"); // use new headless mode
			    options.addArguments("--window-size=1920,1080");
			    options.addArguments("--disable-gpu");
			    options.addArguments("--no-sandbox");
			    options.addArguments("--disable-extensions");
			    options.addArguments("--remote-allow-origins=*"); // often required for ChromeDriver 112+
			//driver = new ChromeDriver();
			//driver.set(new ChromeDriver());
			webDriver = new ChromeDriver(options);
			}
			else {
				webDriver = new ChromeDriver();
			}
		}
		else if(browserName == Browser.EDGE) {
			//driver = new EdgeDriver();
			//driver.set(new EdgeDriver());
			if(isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable-gpu");
				options.addArguments("--window-size=1920,1080");
				//driver = new ChromeDriver();
				//driver.set(new ChromeDriver());
				webDriver = new EdgeDriver(options);
			}
			else {
				webDriver = new EdgeDriver();
			}
			
		}
		else if(browserName == Browser.FIREFOX) {
			//driver = new FirefoxDriver();
			//driver.set(new FirefoxDriver());
						
			if(isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old");				
				options.addArguments("--window-size=1920,1080");
				//driver = new ChromeDriver();
				//driver.set(new ChromeDriver());
				webDriver = new FirefoxDriver(options);
			}
			else {
				webDriver = new FirefoxDriver();
			}			
		}
		else {
			logger.error("Invalid browser");
		}
		driver.set(webDriver);
	}
	
	
	public void goToWebSite(String url) {
		logger.info("Visisting the website "+url);
		getDriver().get(url);
	}
	
	public void maximizeBrowser() {
		logger.info("maximizing window");
		getDriver().manage().window().maximize();
	}
	
	public void clickOn(By locator) {
		logger.info("Finding Element with the locator "+locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and performing click");
		element.click();		
	}
	
	public void enterText(By locator, String textToEnter) {
		WebElement emailTextboxElement = driver.get().findElement(locator);
		emailTextboxElement.sendKeys(textToEnter);
		
	}
	
	public String getVisibleTest(By locator) {
		WebElement extboxElement = driver.get().findElement(locator);
		return extboxElement.getText();
	}
	

	public String takeScreenshot(String methodName) {
	    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String filePath = System.getProperty("user.dir") + "/screenshots/" + methodName + "_" + timestamp + ".png";
	    File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
	    File dest = new File(filePath);
	    try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return filePath;
	}
	
	public void quitBrowser() {
	    //try {
		WebDriver webDriver = getDriver();
	        if (webDriver != null) {
	            logger.info("Quitting browser for thread: " + Thread.currentThread().getId());
	            webDriver.quit();
	            driver.remove(); // removes ThreadLocal reference to avoid memory leaks
	        }
	   // } catch (Exception e) {
	    //    logger.error("Error while quitting browser: " + e.getMessage());
	   // }
	}

}
