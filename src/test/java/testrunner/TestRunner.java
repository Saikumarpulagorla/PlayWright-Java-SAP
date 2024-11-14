package testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features",
	glue={"stepDefinitions"},
<<<<<<< HEAD
	tags=("@SAP"),
=======
	tags=("@SAP_Add_New_Employee"),
>>>>>>> 1d7cbf40634d2f87b67250e919accf942559d4c9
	publish = true,
	monochrome=true,
	dryRun = false,
	plugin = { "pretty", "html:target/cucumber-pretty-report/PrettyReport.html",
		"json:target/cucumber.json",
		"junit:target/Junit-reports/JunitTestReport.xml",
		"rerun:target/rerun.txt"})
public class TestRunner {

}
