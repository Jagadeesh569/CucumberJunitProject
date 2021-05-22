package com.test.cucumber.pageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.cucumber.util.DriverManager;
import com.test.cucumber.util.TestUtils;

public class BasePage {

	WebDriverWait wait;
	
	public void clickOnElement(WebElement element) {
		//waitForVisibility(element);
		element.click();
	}
	
	public void waitForVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getInstance().getDriver(),TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void sendKeys(WebElement element, String value) {
		waitForVisibility(element);
		element.click();
		element.sendKeys(value);
	}
}
