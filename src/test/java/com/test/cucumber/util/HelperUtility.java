package com.test.cucumber.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.test.cucumber.Logger.LoggerHelper;
import com.test.cucumber.stepDefinitions.StepDefinitions;

public class HelperUtility {
	
	public WebDriver driver;
	Logger log = LoggerHelper.getLogger(HelperUtility.class);
	private static final String chromeBrowser = "Chrome";
	private static final String fireFoxBrowser = "Firefox";
	
	JSONParser jsonParser = new JSONParser();
	JSONObject loneInput;
	
	private static Properties props = new Properties(); 
	
	public WebDriver initializeWebDriver(String browserType) {
		System.out.println("BrowserType is = "+browserType);
		
		try {
		if(browserType.equalsIgnoreCase(chromeBrowser)) {
			String driverPath =HelperUtility.getProjectPath()+"\\drivers\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver= new ChromeDriver();
			//driver.manage().window().maximize();
			Thread.sleep(1000);
			
			
		}else {
			
		}
		return driver;
		}catch(Exception ex) {
			System.out.println("Exception occurred when initializing driver .. ");
			ex.printStackTrace();
			return null;
		}
		
	}
	
	public static void closeDriver() {
		//driver.close();
		//driver.quit();
	}
	
	
	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}
	
	public Properties getProps() {
		log.info("LOG: start for get properties >>>>>> ");
		InputStream is;
		String propsFileName = System.getProperty("user.dir")+"\\src"+"\\test"+"\\resources"+"\\configs"+"\\env.properties";
		try {
		if(props.isEmpty()) {
			try {
				//is = getClass().getClassLoader().getResourceAsStream(propsFileName);
				is = new FileInputStream(propsFileName);
				if(is != null) {
				props.load(is);}else {
					throw new FileNotFoundException("property file '" + propsFileName + "' not found in the classpath");
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		}catch(Exception ex) {
			System.out.println("Caught exception ... ");
			ex.printStackTrace();
		}
		log.info("LOG:Before returning props >>>>>>>>");
		return props;
	}
	
	public JSONObject readJson(String data) {
		try {
			
			   FileReader reader = new FileReader(HelperUtility.getProjectPath()+"\\src\\test\\resources\\TestData\\LoanInputData.json");
			   Object obj = jsonParser.parse(reader);
	    	   JSONArray usersList = (JSONArray) obj;
	    	   JSONObject user1 = null;
	    	   for(int i=0;i<usersList.size();i++) 
	    	   {
	    		   JSONObject user = (JSONObject) usersList.get(i);	
	    		   JSONObject userObj = (JSONObject) user.get("loanInputData");
	    		   String dataset = (String) userObj.get("dataSet");
	    		   if(dataset.equalsIgnoreCase(data)) {
    			   user1 = userObj;
    			   break;
    		   }
	    		   
	    	   }
	    	   return user1;
		}catch(Exception ex) {
			System.out.println("Exception caught >> ");
			ex.printStackTrace();
			return null;
		}
	}
	
	public String getValueFrommvnProperties(String param) {
		String value="";
		try {
			value = System.getProperty(param);
			
		}catch(Exception ex) {
			log.info("Exception occurred while getting mvn cmd values");
			return null;
		}
		return value;
	}

}
