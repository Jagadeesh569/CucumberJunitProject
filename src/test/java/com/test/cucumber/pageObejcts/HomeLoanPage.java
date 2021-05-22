package com.test.cucumber.pageObejcts;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.test.cucumber.Logger.LoggerHelper;
import com.test.cucumber.stepDefinitions.StepDefinitions;

public class HomeLoanPage extends BasePage{
	
	WebDriver driver;
	
	private static String TitlePage="Home loan borrowing power calculator | ANZ";
	
	Logger log = LoggerHelper.getLogger(HomeLoanPage.class);
	
	public HomeLoanPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	
	}
	@FindBy(css="#application_type_single") // application_type_joint   application_type_single
	WebElement applicatioType;
	public void selectApplicationType() {
		String checked = applicatioType.getAttribute("checked");
		log.info("Checked   ..............."+checked);
		if(checked.equalsIgnoreCase("true")) {
			log.info("Checked already ...............");
			
		}else {
			log.info("application type is not selected bydefault ...............");
			this.clickOnElement(applicatioType);
		}
			
	}
	
	@FindBy(css="#borrow_type_home") // borrow_type_investment  borrow_type_home
	WebElement propertyType;
	public void selectProprtyType() {
		
		String checked = propertyType.getAttribute("checked");
		if(checked.equalsIgnoreCase("true")) {
			log.info("Checked already ...............");
		}else {
			log.info("property type is not selected bydefault ...............");
			this.clickOnElement(propertyType);
		}
		
	}
	
	@FindBy(css="#expenses")
	WebElement livingExpenses;
	public void enterLivingExpense(String val) {
		this.sendKeys(livingExpenses, val);
	}
	
	@FindBy(css="#homeloans")
	WebElement homeLoanRepay;
	public void enterhomeLoanRepay(String val) {
		this.sendKeys(homeLoanRepay, val);
	}
	
	@FindBy(css="#otherloans")
	WebElement otherLoan;
	public void enterOtherLoan(String val) {
		this.sendKeys(otherLoan, val);
	}
	
	@FindBy(css="#credit")
	WebElement creditCardLimit;
	public void entercreditCardLimit(String val) {
		this.sendKeys(creditCardLimit, val);
	}
	
	@FindBy(css="#btnBorrowCalculater")
	WebElement calculateButton;
	public void clickOnCalculateBtn() {
		this.clickOnElement(calculateButton);
	}
	
	@FindBy(xpath="//select[@title='Number of dependants']")
	WebElement noOfDependents;
	
	public Select getSelectOptions() {
		  return new Select(noOfDependents);
		}
	public void selectNoOfDependents(int val) {
		getSelectOptions().selectByIndex(val);

	}
	
	@FindBy(xpath="//input[@aria-labelledby='q2q1']")
	WebElement income;
	public void enterIncome(String val) {
		this.sendKeys(income, val);
	}
	
	@FindBy(xpath="//input[@aria-labelledby='q2q2']")
	WebElement otherIncome;
	public void enterOtherIncome(String val) {
		this.sendKeys(otherIncome, val);
	}
	
	@FindBy(xpath="//input[@aria-labelledby='q3q4']")
	WebElement otherCommimnt;
	public void enterOtherCommittment(String val) {
		this.sendKeys(otherCommimnt, val);
	}
	
	public void verifyLandingPage() {
		String title = driver.getTitle();
		Assert.assertEquals(TitlePage, title);
	}
	
	@FindBy(css="#borrowResultTextAmount")
	WebElement borrowAmt;
	public String verifyBorrowAmountCalculated() {
		String amountdisplayed = borrowAmt.getText();
		log.info("Amount displayed = > "+amountdisplayed);
		return amountdisplayed;
		
	}
	
	@FindBy(xpath="//span[contains(text(),'unable to give you an estimate')]")
	WebElement errorMsg;
	
	public void verifyErrorMsg(String msg) {
		String message = errorMsg.getText();
		assertEquals(message, msg);
	}
	
	@FindBy(xpath="(//SPAN[@class='icon icon_restart'])[2]") 
	WebElement startOver;
	public void selectStartOver() {
		this.waitForVisibility(startOver);
		this.clickOnElement(startOver);
	
	}
	
	public void verifyDataIsReset() {
		log.info("LOG: going to verify if the fields are reset after selecting start over");
		log.info("Living Expenses = "+livingExpenses.getText().isEmpty());
		log.info("homeLoanRepay = "+homeLoanRepay.getText().isEmpty());
		log.info("otherIncome = "+otherIncome.getText().isEmpty());
		log.info("creditCardLimit = "+creditCardLimit.getText().isEmpty());
		log.info("otherCommimnt = "+otherCommimnt.getText().isEmpty());	
		Assert.assertTrue(livingExpenses.getText().isEmpty(),"start over failed !");
		Assert.assertTrue(homeLoanRepay.getText().isEmpty(),"start over failed !");
		Assert.assertTrue(otherIncome.getText().isEmpty(),"start over failed !");
		Assert.assertTrue(creditCardLimit.getText().isEmpty(),"start over failed !");
		Assert.assertTrue(otherCommimnt.getText().isEmpty(),"start over failed !");
		
	}
	
	
	
	
	
	
	
	
}
