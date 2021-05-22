package com.test.cucumber.Logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import com.test.cucumber.util.HelperUtility;

public class LoggerHelper {

	
	private static boolean root = false;
	public static Logger getLogger(Class clas) {
		if(root) {
			return Logger.getLogger(clas);
		}
		PropertyConfigurator.configure(HelperUtility.getProjectPath()+"\\src\\main\\resources\\log4j.properties");
		root = true;
		return Logger.getLogger(clas);
	}
}
