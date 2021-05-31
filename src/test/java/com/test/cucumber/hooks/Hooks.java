package com.test.cucumber.hooks;



import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.test.cucumber.Logger.LoggerHelper;
import com.test.cucumber.stepDefinitions.StepDefinitions;
import com.test.cucumber.util.DriverManager;
import com.test.cucumber.util.HelperUtility;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	Logger log = LoggerHelper.getLogger(Hooks.class);
	HelperUtility helper = new HelperUtility();
	DriverManager manager = new DriverManager();
	public WebDriver driver;
	
	@Before
	public void initiateDriver() throws Exception {
		log.info("LOG: Before method and initializing driver session ....");
		Properties props = helper.getProps();
		String appURL = props.getProperty("ProdURL");
		String browser = helper.getValueFrommvnProperties("browser");
		log.info("LOG: got browser name from mvn command  ...."+browser);
		HelperUtility util = new HelperUtility();
		manager.getInstance().setDriver(util.initializeWebDriver(browser));
		driver=DriverManager.getInstance().getDriver();
		log.info("LOG: got driver instance succesffully  ....");
		driver.navigate().to(appURL);
		Thread.sleep(1000);
		
	}
	
	@After
	public void closeDriver() {
		log.info("LOG: After method and closing driver session ....");
		manager.getDriver().quit();
		
	}

}
