package com.test.cucumber.cucumberOptions;
import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features ="src/test/resources/Features/HomeLoanCalculator.feature",
glue = {"com/test/cucumber/stepDefinitions","com.test.cucumber.hooks"},
monochrome = true,
plugin={"pretty", "html:target/cucumber/cucumber-html-reports","json:target/cucumber.json","timeline:test-output-thread/"})
// "json:target/cucumber.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
public class RunJUnitTest {

}
