package testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features",
	glue={"stepDefinitions"},
	tags=("@SAP_Add_New_Employee"),
	publish = true,
	monochrome=true,
	dryRun = false,
	plugin = { "pretty", "html:target/cucumber-pretty-report/PrettyReport.html",
		"json:target/cucumber.json",
		"junit:target/Junit-reports/JunitTestReport.xml",
		"rerun:target/rerun.txt"})
public class TestRunner {

}
