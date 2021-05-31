package com.test.cucumber.cucumberOptions;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features ="src/test/resources/Features/HomeLoanCalculator.feature",
glue = {"com/test/cucumber/stepDefinitions","com.test.cucumber.hooks"},
monochrome = true,
plugin={"pretty", "html:target/cucumber/cucumber-html-reports","json:target/cucumber.json","json:target/cucumber.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","timeline:test-output-thread/"})

@Test
public class TestRunner extends AbstractTestNGCucumberTests{
	
	@DataProvider(parallel=true)
	public Object[][] scenarios(){
		return super.scenarios();
	}

}
