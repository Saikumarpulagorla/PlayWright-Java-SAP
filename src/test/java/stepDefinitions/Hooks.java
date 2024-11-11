package stepDefinitions;

import org.zaproxy.clientapi.core.ApiResponse;

import Enums.Browsers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import testbase.TestBase;

public class Hooks {
	TestBase testBase;
	int count=0;
	String browser = null;
	String mobile = null;
	String api = null;
	String database = null;
	String security = null;

	@Before
	public void initializeTest(Scenario scenario) throws Exception {
		System.out.println("Hooks at Before Metod Executed");
		testBase = new TestBase();

		//Set Scenario as Global Variable
		testBase.currentScenario(scenario);
		
		String environment = System.getProperty("environment");

		//Load Configuration File Based on Execution Environment
		if(environment != null && !environment.isEmpty()) {
			testBase.loadConfigReader(environment);
		} else {
			testBase.loadConfigReader("dev");//dev //staging //release //production
		}

		//Execute Scripts on Browser based on System variables
		browser = System.getProperty("browser");
		mobile = System.getProperty("mobile");
		api = System.getProperty("api");
		database = System.getProperty("database"); 
		security = System.getProperty("security");
		System.out.println("browser="+browser);
		System.out.println("mobile="+mobile);
		System.out.println("api="+api);
		System.out.println("database="+database);
		System.out.println("security="+security);
		if(browser != null) {
			//testBase.startScenarioVideoRecording(scenario.getName());
			testBase.selectBrowser(browser.toUpperCase());
		} 
		if(mobile != null){
			//testBase.startScenarioVideoRecording(scenario.getName());
			testBase.selectBrowser(mobile.toUpperCase());
			testBase.setImplecitWait("MOBILE_IMPLICIT_WAIT");
		}
		if(api != null){
			testBase.selectBrowser(api.toUpperCase());
			System.out.println("api="+api);
		}
		if(database != null){
			testBase.getDBConnection();
			System.out.println("Connected To DataBase Successfully...");
			System.out.println("testBase.getDBConnection()"+testBase.getDBConnection());
		}
		if(security != null) {
			testBase.selectBrowser(security.toUpperCase());
			testBase.maximizeWindow();
			testBase.setImplecitWait("WEB_IMPLICIT_WAIT");
		}
		if(browser == null && mobile == null && api == null && database == null && security == null) {
			testBase.selectBrowser(Browsers.CHROME.name());
			testBase.maximizeWindow();
		}
		/*
		if(api == null && database == null) {
			testBase.setImplecitWait();
		}
		*/
		//testBase.startScenarioVideoRecording(scenario.getName());
	}

	@After
	public void closeBrower(Scenario scenario) throws Exception {
		
		testBase.captureScreenShot(scenario);
		TestBase.browser.close();
		TestBase.page.close();
		
//		//testBase.stopScenarioVideoRecording();
//		if(browser != null) {
//			//testBase.createIssueInJira(scenario);
//			testBase.captureScreenShot(scenario);
//			TestBase.browser.close();
//			TestBase.page.close();
//		}
//		if(mobile != null) {
//			testBase.createIssueInJira(scenario);
//			testBase.captureScreenShot(scenario);
//			//TestBase.driver.quit();
//			if(mobile.equalsIgnoreCase("android")) {
//				//((AppiumDriver)TestBase.driver).quit();
//			} else if (mobile.equalsIgnoreCase("ios")){
//				//TestBase.driver.quit();
//			}
//			
//			
//		}
//		if (database != null) {
//			testBase.closeDBConnection();
//			System.out.println("Data Base Connection Closed...");
//		}
//		if (api != null) {
//			System.out.println("API Connection Closed...");
//		}
//		if(security != null) {
//			try {
//				if(testBase.zapApi != null) {
//					String title = "DragonFlyTest - OG Money ZAP Security Test Report";
//					String template = "traditional-html";
//					String description = "This is DragonFlyTest - OG Money Security Test Report";
//					String reportfilename = "DragonFlyTest_OG_Money_ZAP_Report.html";
//					//String targetfolder = System.getProperty("user.dir")+"/target/";
//					String targetfolder = "/Users/mohan/.jenkins/workspace/07_OG_SECURITY_Automation/";
//					System.out.println("targetfolder="+targetfolder);
//					ApiResponse response = testBase.zapApi.reports.generate(title, template, null, description, null, null, null, null, null, reportfilename, null, targetfolder, null);
//					System.out.println("Zap report generated at this location: "+response.toString());
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			//testBase.createIssueInJira(scenario);
//			testBase.captureScreenShot(scenario);
//			//TestBase.driver.manage().deleteAllCookies();
//			//TestBase.driver.close();;
//			
//		}
//		
		
	}

}
