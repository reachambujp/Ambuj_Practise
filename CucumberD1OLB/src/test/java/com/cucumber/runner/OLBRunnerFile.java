package com.cucumber.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Feature/OLBApplication.feature",
              glue= {"com.cucumber.stepdefinitions"},
              monochrome = true,
              dryRun = false,
              plugin= {"pretty","html:target/cucumber-report-html/report.html",
      	                  	"json:target/cucumber.json",
      	                  	"junit:target/JUnitReports/report.xml"}
)


public class OLBRunnerFile {

}
