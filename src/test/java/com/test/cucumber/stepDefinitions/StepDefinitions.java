package com.test.cucumber.stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.io.FileReader;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.test.cucumber.Logger.LoggerHelper;
import com.test.cucumber.pageObejcts.HomeLoanPage;
import com.test.cucumber.util.DriverManager;
import com.test.cucumber.util.HelperUtility;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
	
	Logger log = LoggerHelper.getLogger(StepDefinitions.class);
	HelperUtility helper = new HelperUtility();
	JSONParser jsonParser = new JSONParser();
	HomeLoanPage homepage = new HomeLoanPage(DriverManager.getInstance().getDriver());
	@Given("^I am on the homeloan calculator page$")
    public void i_am_on_the_homeloan_calculator_page() throws Exception  {
		log.info("LOG: I am on the homeloan page step ");
		homepage.verifyLandingPage();
    }

    @When("^I provide all the required input from data file (.+)$")
    public void i_provide_all_the_required_input_from_data_file(String testdata) throws Exception  {
    	  log.info("LOG: Provide all the input for home loan calculator  "+testdata);
    	  JSONObject user = (JSONObject) helper.readJson(testdata);
    	  log.info(user);
    	  int dependents =  Integer.parseInt((String) user.get("noOfDependents"));
    	  homepage.selectApplicationType();     
          homepage.selectProprtyType();
          homepage.selectNoOfDependents(dependents);
          homepage.enterIncome((String) user.get("income"));   
          homepage.enterOtherIncome((String) user.get("otherIncome"));   
          homepage.enterLivingExpense((String) user.get("livingExpenses"));       
          homepage.enterhomeLoanRepay((String) user.get("homeLoanExpenses"));       
          homepage.enterOtherLoan((String) user.get("otherLoanExpenses"));   
          homepage.enterOtherCommittment((String) user.get("otherCommitments"));        
          homepage.entercreditCardLimit((String) user.get("totalCreditCardLimit"));   
          homepage.clickOnCalculateBtn();
          Thread.sleep(2000);
    }

    @Then("^I see the (.+) that can be borrowed$")
    public void i_see_the_amount_that_can_be_borrowed(String amount)  {
    	log.info("LOG: amount that can be borrowed ");
    	String borrowAmount = homepage.verifyBorrowAmountCalculated();
    	log.info("LOG: Borrow amount expected = "+amount);
    	assertEquals(borrowAmount, amount);
    }
    
  
    @Then("^I see the message (.+)$") 
    public void i_see_the_message_something(String message) {
    	homepage.verifyErrorMsg(message);
        
    }
    
    @Then("^I select start over$")
    public void i_select_start_over() throws Exception  {
    	
    	homepage.selectStartOver();
    	
       
    }

    @Then("^I see that data is reset for all the fields$")
    public void i_see_that_data_is_reset_for_all_the_fields() {
   homepage.verifyDataIsReset();
    }

}
