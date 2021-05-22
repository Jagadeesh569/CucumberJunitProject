package com.test.cucumber.util;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	
	private static DriverManager instance = new DriverManager();
	
	public static DriverManager getInstance() {
		return instance;
	}
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public WebDriver getDriver() {
		return driver.get();
	}

	public void setDriver(WebDriver driverparam) {
		driver.set(driverparam);
	}
	
	public void initializeDriver() {
		WebDriver driver = null;
		
		if(driver == null) {
			try {
				
				driver = HelperUtility.initializeWebDriver("Chrome");
			}catch(Exception ex) {}
		}
	}
	
	public void closeBrowser() {
		driver.get().quit();
		driver.remove();
	}
}
