package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//run feature files presenting in this path "src/test/java/cucumber" then to map them here  there is stepdefenitions path ,
// use monochrome=true to print the results in readable format and generate report in html plugin

@CucumberOptions(features = "src/test/java/cucumber", glue = "EAEnterprise.stepDefenitions", monochrome = true, plugin = {
		"html:target/cucumber.html" }, tags = "@Regression")

//extending wrapper parent class called "AbstractTestNGCucumberTests"
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
