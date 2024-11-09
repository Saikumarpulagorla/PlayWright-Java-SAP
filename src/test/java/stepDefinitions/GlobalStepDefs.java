package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.GlobalPageObjects;
import pageObjects.GlobalXpathGenerator;
import pageObjects.SAP_Homepage;
import testbase.TestBase;

public class GlobalStepDefs extends TestBase {

	GlobalPageObjects globalPageObjects = new GlobalPageObjects(page);
	GlobalXpathGenerator xpathGenerator = new GlobalXpathGenerator();
	//Utilities util = new Utilities(driver);

	HashMap<String, String> appointmentDetails;

	//public static Page application;

	@Given("I navigate to web application")
	public void i_navigate_to_web_application() {
		try {
			//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			//int width = (int)screenSize.getWidth();
			//int height = (int)screenSize.getHeight();
			//context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
			//context.setDefaultTimeout(30_000);
			//context.setDefaultNavigationTimeout(30_000);
			//page = context.newPage();
			page.navigate(CONFIG.getProperty("TEST_URL"));
		} catch (Exception e) {
			TestBase.writeResponse("Unable to Load the URL"+CONFIG.getProperty("TEST_URL"));
		}
	}

	@Given("I wait for page to load")
	public void i_wait_for_page_to_load() {
		page.waitForLoadState(LoadState.NETWORKIDLE, new Page.WaitForLoadStateOptions().setTimeout(60_000));
	}

	@Then("I should see page title as {string}")
	public void i_should_see_page_title_as(String string) {

	}
	@Then("I enter {string} as {string}")
	public void i_enter_as(String string, String string2) {
		String[] splitText = string.split("=");
		String locator = splitText[0];
		String locatorText = splitText[1];
		if(locator.equalsIgnoreCase("LABEL")) {
			globalPageObjects.enterTextByLabel(locatorText, string2);
		} else if (locator.equalsIgnoreCase("PH")) {
			globalPageObjects.enterTextByPlaceHolder(locatorText, string2);
		}
	}
	@When("I click on {string}")
	public void i_click_on(String string) {
		String[] splitText = string.split("=");
		String locator = splitText[0];
		String locatorText = splitText[1];
		if(locator.equalsIgnoreCase("text")) {
			globalPageObjects.clickOnTextByVisibleText(locatorText);
		}else if(locator.equalsIgnoreCase("text2")) {
			globalPageObjects.clickOnTextByVisibleTextFromAutoSuggestion(locatorText);
		}else if(locator.equalsIgnoreCase("ID")) {
			//globalPageObjects.clickOn(locatorText);
		}
	}
	@Then("I see {string} web element")
	public void i_see_web_element(String string) {

	}
	@When("I press {string}")
	public void i_press(String string) {
		String[] splitText = string.split("=");
		String locator = splitText[0];
		String locatorText = splitText[1];
		if(locator.equalsIgnoreCase("Action")) {
			globalPageObjects.clickOnEnter(locatorText);
		}
	}

	public String getXpath(String locatorText)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String xpath = null;

		Method[] method = globalPageObjects.getClass().getMethods();
		System.out.println("Method Length="+method.length);
		for (int i = 0; i < (method.length); i++) {
			System.out.println("method[i].getName()"+method[i].getName());
			if (method[i].getName().equals(locatorText)) {
				xpath = (String) method[i].invoke(globalPageObjects);
			}
		}
		return xpath;
	}




	//	@Then("I should see page title as {string}")
	//	public void i_should_see_page_title_as(String expectedPageTitle) throws InterruptedException {
	//		//util.waitUtil.WaitForExpectedTitle(expectedPageTitle, globalWebExplecitWaitTime());
	//		//String title = driver.getTitle();
	//		//Thread.sleep(10000);
	//		// Wait for the page title to be set
	//		//page.waitForLoadState(LoadState.NETWORKIDLE);
	//		page.waitForLoadState(LoadState.NETWORKIDLE, new Page.WaitForLoadStateOptions().setTimeout(60000));
	//		String title = page.title();
	//		TestBase.writeResponse("Expected Page Title == "+expectedPageTitle);
	//	    TestBase.writeResponse("Actual Page Title == "+ title);
	//	    if(expectedPageTitle.equalsIgnoreCase(title)) {
	//	    	TestBase.writeResponse("Actual and Expected Page Titles Are Same");
	//	    } else {
	//	    	TestBase.writeResponse("Actual and Expected Page Titles Not Are Same");
	//	    }
	//	    assertEquals(expectedPageTitle, title);
	//	}
	//	
	//	@Then("I see {string} web element")
	//	public void i_see_button_web_element(String string) throws InterruptedException {
	//		String xpath = "";
	//		boolean visibleStatus = false;
	//		String[] splitText = string.split("=");
	//	    String locator = splitText[0];
	//	    String locatorText = splitText[1];
	//	    if(locator.equals("VT")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByVisibleTextContains(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//		} else if(locator.equals("VTEQ")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByVisibleTextEquals(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    } else if(locator.equals("ID")) {
	//	    	xpath = xpathGenerator.generateXpathForElementById(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    } else if(locator.equalsIgnoreCase("LINK")) {
	//	    	xpath = xpathGenerator.generateXpathForLinkByVisibleTextContains(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    } else if(locator.equalsIgnoreCase("IOSNAME")) {
	//			xpath = xpathGenerator.generateXpathForElementByName(locatorText);
	//			visibleStatus = WaitForElementVisibility(xpath);
	//		} 
	//	    if(visibleStatus) {
	//	    	TestBase.writeResponse("I See ->"+locatorText+" On Web Page");
	//		} else {
	//			TestBase.writeFailedResponse("FAILED:: "+locatorText+" Not Visible on Web Page");
	//		}
	//	    assertTrue("FAILED:: "+locatorText+" Not Visible on Web Page", visibleStatus);
	//	}
	//	
	//	@Then("I see {string} with {string}")
	//	public void i_see_with(String expectedString, String string) {
	//		String xpath = "";
	//		boolean visibleStatus = false;
	//		String actualString = "";
	//		String[] splitText = string.split("=");
	//	    String locator = splitText[0];
	//	    String locatorText = splitText[1];
	//	    if(locator.equals("VT")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByVisibleTextContains(locatorText);
	//	    	//visibleStatus = WaitForElementVisibility(xpath, globalWebExplecitWaitTime());
	//	    	actualString = globalPageObjects.getText(xpath);
	//	   } else if(locator.equals("ID")) {
	//		   xpath = xpathGenerator.generateXpathForElementById(locatorText);
	//		   //visibleStatus = WaitForElementVisibility(xpath, globalWebExplecitWaitTime());
	//		   actualString = globalPageObjects.getText(xpath);
	//	    } else if(locator.equals("NAME")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByName(locatorText);
	//			//visibleStatus = WaitForElementVisibility(xpath, globalWebExplecitWaitTime());
	//			actualString = globalPageObjects.getText(xpath);
	//	    } else if(locator.equals("CLASS")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByClass(locatorText);
	//	    	//visibleStatus = WaitForElementVisibility(xpath, globalWebExplecitWaitTime());
	//			actualString = globalPageObjects.getText(xpath);
	//	    } else if(locator.equals("TYPE")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByType(locatorText);
	//	    	//visibleStatus = WaitForElementVisibility(xpath, globalWebExplecitWaitTime());
	//			actualString = globalPageObjects.getText(xpath);
	//	    } else if(locator.equals("TITLE")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByTitle(locatorText);
	//	    	//visibleStatus = WaitForElementVisibility(xpath, globalWebExplecitWaitTime());
	//			actualString = globalPageObjects.getText(xpath);
	//	    }
	//	    TestBase.writeResponse("Actual String = "+actualString);
	//	    TestBase.writeResponse("Expected String = "+expectedString);
	//	    if(actualString.equalsIgnoreCase(expectedString)) {
	//	    	TestBase.writeResponse("Actual And Expected String Are Same");
	//		} else {
	//			TestBase.writeFailedResponse("FAILED:: Actual And Expected Text Are NOT Same");
	//		}
	//	    assertEquals(expectedString, actualString);
	//	}
	//	
	//	@Then("I see {string} as {string}")
	//	public void i_see_as(String string, String expectedString) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	//		String xpath = "";
	//		boolean visibleStatus = false;
	//		String actualString = "";
	//		String[] splitText = string.split("=");
	//	    String locator = splitText[0];
	//	    String locatorText = splitText[1];
	//	    if(locator.equals("path")) {
	//	    	xpath = getXpath(locatorText);
	//	    	//visibleStatus = WaitForElementVisibility(xpath, globalWebExplecitWaitTime());
	//	    	actualString = globalPageObjects.getText(xpath);
	//	   } else if(locator.equals("text")) {
	//	    	xpath = xpathGenerator.generateXpathForAndroidElementByTextEquals(locatorText);
	//	    	//visibleStatus = WaitForElementVisibility(xpath, globalWebExplecitWaitTime());
	//	    	actualString = globalPageObjects.getText(xpath);
	//	   } else if(locator.equalsIgnoreCase("iosname")) {
	//		   	xpath = xpathGenerator.generateXpathForElementByName(locatorText);
	//	    	actualString = globalPageObjects.getText(xpath);
	//	   }
	//	    TestBase.writeResponse("Actual String = "+actualString);
	//	    TestBase.writeResponse("Expected String = "+expectedString);
	//	    if(actualString.equalsIgnoreCase(expectedString)) {
	//	    	TestBase.writeResponse("Actual And Expected String Are Same");
	//		} else {
	//			TestBase.writeFailedResponse("FAILED:: Actual And Expected Text Are NOT Same");
	//		}
	//	    assertEquals(expectedString, actualString);
	//	}
	//	@When("I enter {string} as {string}")
	//	public void i_enter_as(String string, String textToEnter) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	//		String xpath = "";
	//		boolean visibleStatus = false;
	//		boolean enterTextStatus = false;
	//		String[] splitText = string.split("=");
	//	    String locator = splitText[0];
	//	    String locatorText = splitText[1];
	//	    if(locator.equalsIgnoreCase("PH")) {
	//	    	xpath = xpathGenerator.generateXpathForPlaceHolder(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.clearText(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("PHEQ")) {
	//	    	xpath = xpathGenerator.generateXpathForPlaceHolderEquals(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("ID")) {
	//	    	xpath = xpathGenerator.generateXpathForElementById(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.clearText(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("NAME")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByName(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("IOSNAME")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByName(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("LABEL")) {
	//	    	xpath = xpathGenerator.generateXpathForInputFieldByLabel(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("LABELID")) {
	//	    	xpath = xpathGenerator.generateXpathForInputFieldByLabelId(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("LABELEQ")) {
	//	    	xpath = xpathGenerator.generateXpathForInputFieldByLabelEquals(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("CLASS")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByClass(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("TYPE")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByType(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("TITLE")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByTitle(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("path")) {
	//	    	xpath = getXpath(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("iospath")) {
	//	    	xpath = getXpath(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("iosvalue")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByValueTextField(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } 
	//	    if(enterTextStatus) {
	//	    	TestBase.writeResponse("I Enter "+locatorText+" As == "+textToEnter);
	//		} else {
	//			TestBase.writeFailedResponse("FAILED:: To Enter "+locatorText+" As == "+textToEnter);
	//		}
	//	    assertTrue("FAILED:: To Enter "+locatorText+" Id As == "+textToEnter, enterTextStatus);
	//	}
	//	
	//	@When("on {string} I enter {string} as {string}")
	//	public void on_i_enter_as(String app, String string, String textToEnter) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	//		String xpath = "";
	//		boolean visibleStatus = false;
	//		boolean enterTextStatus = false;
	//		String[] splitText = string.split("=");
	//	    String locator = splitText[0];
	//	    String locatorText = splitText[1];
	//	    if(locator.equalsIgnoreCase("PH")) {
	//	    	xpath = xpathGenerator.generateXpathForPlaceHolder(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.clearText(page, xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(page, xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("PHEQ")) {
	//	    	xpath = xpathGenerator.generateXpathForPlaceHolderEquals(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("ID")) {
	//	    	xpath = xpathGenerator.generateXpathForElementById(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.clearText(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("NAME")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByName(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("IOSNAME")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByName(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("LABEL")) {
	//	    	xpath = xpathGenerator.generateXpathForInputFieldByLabel(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("LABELEQ")) {
	//	    	xpath = xpathGenerator.generateXpathForInputFieldByLabelEquals(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("CLASS")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByClass(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("TYPE")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByType(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("TITLE")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByTitle(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("path")) {
	//	    	xpath = getXpath(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("iospath")) {
	//	    	xpath = getXpath(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } else if(locator.equalsIgnoreCase("iosvalue")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByValueTextField(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	enterTextStatus = globalPageObjects.enterText(xpath, textToEnter);
	//	    } 
	//	    if(enterTextStatus) {
	//	    	TestBase.writeResponse("I Enter "+locatorText+" As == "+textToEnter);
	//		} else {
	//			TestBase.writeFailedResponse("FAILED:: To Enter "+locatorText+" As == "+textToEnter);
	//		}
	//	    assertTrue("FAILED:: To Enter "+locatorText+" Id As == "+textToEnter, enterTextStatus);
	//	}
	//	
	//	@When("I click on {string}")
	//	public void i_click_on(String string) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	//		String xpath = "";
	//		boolean visibleStatus = false;
	//		boolean clickStatus = false;
	//		String[] splitText = string.split("=");
	//	    String locator = splitText[0];
	//	    String locatorText = splitText[1];
	//	    if(locator.equals("VT")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByVisibleTextContains(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equals("VTEQ")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByVisibleTextEquals(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equals("ID")) {
	//	    	xpath = xpathGenerator.generateXpathForElementById(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equalsIgnoreCase("NAME")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByName(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equalsIgnoreCase("LABELID")) {
	//	    	xpath = xpathGenerator.generateXpathForInputFieldByLabelId(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equalsIgnoreCase("IOSNAME")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByName(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equals("CLASS")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByClass(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equals("TYPE")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByType(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equals("TITLE")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByTitle(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equals("BUTTON")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByButtonTag(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equalsIgnoreCase("iosbtn")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByiOSButton(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equalsIgnoreCase("value")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByValue(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equalsIgnoreCase("iosvalue")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByValue(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equalsIgnoreCase("path")) {
	//	    	xpath = getXpath(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equalsIgnoreCase("iospath")) {
	//	    	xpath = getXpath(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equalsIgnoreCase("text")) {
	//	    	xpath = xpathGenerator.generateXpathForAndroidElementByTextEquals(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equalsIgnoreCase("ITEMVT")) {
	//	    	xpath = xpathGenerator.generateXpathForAddItemByVisibleTextEquals(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equalsIgnoreCase("ng-arrow")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByNGArrow(locatorText);
	//	    	visibleStatus = WaitForElementVisibility(xpath);
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } 
	//	    System.out.println("clickStatus="+clickStatus);
	//	    System.out.println("visibleStatus="+visibleStatus);
	//	    if(clickStatus && visibleStatus) {
	//	    	TestBase.writeResponse("I Clicked on "+string);
	//		} else {
	//			TestBase.writeFailedResponse("FAILED to Click on "+string + ". Please refere screenshot");
	//		}
	//	    System.out.println("clickStatus="+clickStatus);
	//		assertTrue("FAILED to Click on "+string + ". Please refere screenshot", clickStatus);
	//		assertTrue("FAILED to Click on "+string + ". Please refere screenshot", visibleStatus);
	//	}
	//	
	//	@When("I click on {string} under {string}")
	//	public void i_click_on_under(String string, String string2) throws InterruptedException {
	//		String xpath = "";
	//		//boolean enabledStatus = false;
	//		boolean clickStatus = false;
	//		String[] splitText = string.split("=");
	//	    String locator = splitText[0];
	//	    String locatorText = splitText[1];
	//	    
	//	    String[] splitText2 = string2.split("=");
	//	    String locator2 = splitText2[0];
	//	    String locatorText2 = splitText2[1];
	//	    if(locator.equals("VT") && locator2.equalsIgnoreCase("CLASS")) {
	//	    	xpath = xpathGenerator.generateXpathForParentByClassAndChildByVisibleTextContains(locatorText, locatorText2);
	//	    	//enabledStatus = page.locator(xpath).isEnabled();
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equalsIgnoreCase("VT") && locator2.equalsIgnoreCase("data-title")) {
	//	    	xpath = xpathGenerator.generateXpathForParentByDataTitleAndChildByVisibleTextContains(locatorText, locatorText2);
	//	    	//enabledStatus = page.locator(xpath).isEnabled();
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equalsIgnoreCase("VT") && locator2.equalsIgnoreCase("VT")) {
	//	    	xpath = xpathGenerator.generateXpathForParentByVisibleTextAndChildByVisibleTextContains(locatorText, locatorText2);
	//	    	page.waitForSelector(xpath);
	//	    	//enabledStatus = page.locator(xpath).isEnabled();
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equalsIgnoreCase("ID") && locator2.equalsIgnoreCase("VT")) {
	//	    	Thread.sleep(3000);
	//	    	xpath = xpathGenerator.generateXpathForParentByVisibleTextAndChildById(locatorText, locatorText2);
	//	    	page.waitForSelector(xpath);
	//	    	page.locator(xpath).isEnabled();
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    }
	//	    
	//	    if(clickStatus) {
	//	    	TestBase.writeResponse("I Clicked on "+string);
	//		} else {
	//			TestBase.writeFailedResponse("FAILED to Click on "+string + ". Please refere screenshot");
	//		}
	//	    System.out.println("xpath="+xpath);
	//	    System.out.println("clickStatus="+clickStatus);
	//	    //System.out.println("enabledStatus="+enabledStatus);
	//		assertTrue("FAILED to Click on "+string + ". Please refere screenshot", clickStatus);
	//		//assertTrue("FAILED to Click on "+string + ". Please refere screenshot", enabledStatus);
	//	}
	//	
	//	@When("I select {string} as {string}")
	//	public void i_select_as(String string, String selectString) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	//		String xpath = "";
	//		boolean visibleStatus = true;
	//		boolean selectStatus = false;
	//		String[] splitText = string.split("=");
	//	    String locator = splitText[0];
	//	    String locatorText = splitText[1];
	//	    if(locator.equals("ID")) {
	//	    	Instant start = Instant.now();
	//	    	xpath = xpathGenerator.generateXpathForElementById(locatorText);
	//	    	//visibleStatus = customWaitWebIsDisplayed(xpath, globalWebExplecitWaitTime());
	//	    	selectStatus = globalPageObjects.selectValueFromDropDownByXpath(xpath, selectString);
	//	    	Instant end = Instant.now();
	//	    	Duration timeElapsed = Duration.between(start, end);
	//	    	System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");
	//	    } else if(locator.equals("NAME")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByName(locatorText);
	//	    	//visibleStatus = customWaitIsDisplayed(xpath, globalWebExplecitWaitTime());
	//	    	selectStatus = globalPageObjects.selectValueFromDropDownByXpath(xpath, selectString);
	//	    } else if(locator.equals("LABEL")) {
	//	    	xpath = xpathGenerator.generateXpathForListSelectFromDropDown(locatorText, selectString);
	//	    	//visibleStatus = customWaitIsDisplayed(xpath, globalWebExplecitWaitTime());
	//	    	selectStatus = globalPageObjects.selectValueFromDropDownByXpath(xpath, selectString);
	//	    } else if(locator.equals("PATH")) {
	//	    	xpath = getXpath(locatorText);
	//	    	//visibleStatus = customWaitIsDisplayed(xpath, globalWebExplecitWaitTime());
	//	    	selectStatus = globalPageObjects.selectValueFromDropDownByXpath(xpath, selectString);
	//	    } 
	//	    if(selectStatus) {
	//	    	TestBase.writeResponse("I Selected ->"+selectString+" On Web Page.");
	//		} else {
	//			TestBase.writeFailedResponse("FAILED:: "+selectString+" Not Selected From Web Page. Please refer screenshot");
	//		}
	//	    assertTrue("FAILED:: "+selectString+" Not Selected From Web Page. Please refer screenshot", selectStatus);
	//	}
	//	
	//	@When("I click on {string} radio button")
	//	public void i_click_on_radio_button(String string) {
	//		String xpath = "";
	//		boolean visibleStatus = false;
	//		boolean clickStatus = false;
	//		String[] splitText = string.split("=");
	//	    String locator = splitText[0];
	//	    String locatorText = splitText[1];
	//	    if(locator.equals("VT")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByVisibleTextContains(locatorText);
	//	    	//visibleStatus = WaitForElementClickable(xpath, globalWebExplecitWaitTime());
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equals("VTEQ")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByVisibleTextEquals(locatorText);
	//	    	//visibleStatus = WaitForElementClickable(xpath, globalWebExplecitWaitTime());
	//		    clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equals("LABEL")) {
	//	    	xpath = xpathGenerator.generateXpathForLabelTagTextContains(locatorText);
	//	    	//visibleStatus = WaitForElementClickable(xpath, globalWebExplecitWaitTime());
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    } else if(locator.equals("LABELEQ")) {
	//	    	xpath = xpathGenerator.generateXpathForLabelTagTextContains(locatorText);
	//	    	//visibleStatus = WaitForElementClickable(xpath, globalWebExplecitWaitTime());
	//	    	clickStatus = globalPageObjects.clickOnElement(xpath);
	//	    }
	//	    if(clickStatus) {
	//	    	TestBase.writeResponse("I clicked on ->"+locatorText+" Radio Button.");
	//		} else {
	//			TestBase.writeFailedResponse("FAILED:: "+locatorText+" Radio Button Not Selected From Web Page. Please refer screenshot");
	//		}
	//	    assertTrue("FAILED:: "+locatorText+" Radio Button Not Selected From Web Page. Please refer screenshot", clickStatus);
	//	}
	//	
	//	@When("I wait for {string} milli seconds")
	//	public void i_wait_for_seconds_milli(String string) throws InterruptedException {
	//	    Thread.sleep(Integer.parseInt(string));
	//	}
	//	
	//	@When("I enter unique passport number in {string}")
	//	public void i_enter_unique_passport_number_in(String string) {
	//		int randomNumber=TestBase.generateRandomNumber();
	//	    TestBase.writeResponse("Passport Number == "+randomNumber);
	//	    //i_enter_as(string, Integer.toString(randomNumber));
	//	}
	//	
	//	@Given("I get {string} appointment details")
	//	public void i_get_appointment_details(String string) {
	//		TestBase.setAvailabilityForAllRows();
	//	    appointmentDetails = TestBase.getAppointmentDetailsByStatus(string);
	//	    System.out.println("appointments="+appointmentDetails);
	//	}
	//	
	//	@Then("I update status as {string} for above slot time in TestData sheet")
	//	public void i_update_status_as_for_above_slot_time_in_test_data_sheet(String status) {
	//		System.out.println("appointmentDetailsgerowCount="+appointmentDetails.get("rowCount"));
	//		TestBase.setSlotTimeStatus(appointmentDetails.get("rowCount"), status);
	//	}
	//	
	//	@When("I search available paitent from data sheet")
	//	public void i_search_available_paitent_from_data_sheet() {
	//		//i_enter_as("LABELEQ=Search Patient", appointmentDetails.get("MRN"));
	//	}
	//	
	//	@When("I move to {string}")
	//	public void i_move_to(String string) {
	//		String xpath = "";
	//		boolean visibleStatus = false;
	//		boolean clickStatus = false;
	//		String[] splitText = string.split("=");
	//	    String locator = splitText[0];
	//	    String locatorText = splitText[1];
	//	    if(locator.equals("VT")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByVisibleTextContains(locatorText);
	//	    	//visibleStatus = WaitForElementClickable(xpath, globalWebExplecitWaitTime());
	//	    	clickStatus = globalPageObjects.moveToElement(xpath);
	//	    } else if(locator.equals("VTEQ")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByVisibleTextEquals(locatorText);
	//	    	//visibleStatus = WaitForElementClickable(xpath, globalWebExplecitWaitTime());
	//		    clickStatus = globalPageObjects.moveToElement(xpath);
	//	    } else if(locator.equals("data-title")) {
	//	    	xpath = xpathGenerator.generateXpathForElementByDataTitle(locatorText);
	//	    	//visibleStatus = WaitForElementClickable(xpath, globalWebExplecitWaitTime());
	//		    clickStatus = globalPageObjects.moveToElement(xpath);
	//	    }
	//	    if(clickStatus && visibleStatus) {
	//	    	TestBase.writeResponse("I Clicked on "+string);
	//		} else {
	//			TestBase.writeFailedResponse("FAILED to Click on "+string + ". Please refere screenshot");
	//		}
	//	    System.out.println("xpath="+xpath);
	//	    System.out.println("clickStatus="+clickStatus);
	//		assertTrue("FAILED to Click on "+string + ". Please refere screenshot", clickStatus);
	//		assertTrue("FAILED to Click on "+string + ". Please refere screenshot", visibleStatus);
	//	}
	//	

	/*
	@When("I move to {string} and click")
	public void i_move_to_and_click(String string) {
		String[] splitText = string.split("=");
	    String locator = splitText[0];
	    String locatorText = splitText[1];
	    if(locator.equals("VT")) {
	    	globalPageObjects.moveToElementAndClickByVisibleText(locatorText);
	    } 
	    TestBase.writeResponse("I Click on "+locatorText+" web element");
	}



	@Then("I click on {string} dropdown")
	public void i_click_on_dropdown(String string) {
		String xpath = "";
		boolean visibleStatus = false;
		boolean clickStatus = false;
		String[] splitText = string.split("=");
	    String locator = splitText[0];
	    String locatorText = splitText[1];
	    if(locator.equals("LABEL")) {
	    	xpath = xpathGenerator.generateXpathForDropDownClickByLabelName(locatorText);
	    	visibleStatus = WaitForElementClickable(xpath, globalExplecitWaitTime());
		    clickStatus = globalPageObjects.clickOnElement(xpath);
	    } 
	    if(clickStatus) {
	    	TestBase.writeResponse("I Clicked on "+string);
		} else {
			TestBase.writeResponse("FAILED to Click on "+string + ". Please refere screenshot");
		}
	    assertTrue("FAILED to Click on "+string + ". Please refere screenshot", clickStatus);
	}

	@When("I click on {string} downarrow")
	public void i_click_on_downarrow(String string) {
		String[] splitText = string.split("=");
	    String locator = splitText[0];
	    String locatorText = splitText[1];
	    if(locator.equals("VT")) {
	    	globalPageObjects.clickOndownarrowByVisibleText(locatorText);
	    } 
	    TestBase.writeResponse("I Click on "+locatorText+" Dropdown");
	}
	@When("I click on {string} form")
	public void i_click_on_form(String string) {
		String[] splitText = string.split("=");
	    String locator = splitText[0];
	    String locatorText = splitText[1];
	    if(locator.equals("VT")) {
	    	globalPageObjects.clickOnSubmitFormByVisibleText(locatorText);
	    }
	    TestBase.writeResponse("I Click on "+locatorText+" form");
	}
	@When("I click on {string} button")
	public void i_click_on_button(String string) throws InterruptedException {
		String[] splitText = string.split("=");
	    String locator = splitText[0];
	    String locatorText = splitText[1];
	    if(locator.equals("VT")) {
	    	globalPageObjects.clickOnButtonByVisibleText(locatorText);
	    } else if(locator.equals("ID")) {
	    	globalPageObjects.clickOnButtonById(locatorText);
	    } else if(locator.equals("NAME")) {
	    	globalPageObjects.clickOnButtonByName(locatorText);
	    } else if(locator.equals("VTJS")) {
	    	globalPageObjects.clickOnButtonByVisibleTextJavaScript(locatorText);
	    } else if(locator.equals("VTAC")) {
	    	globalPageObjects.clickOnButtonByVisibleTextActionClass(locatorText);
	    } else if(locator.equals("VTEQ")) {
	    	globalPageObjects.clickOnButtonByVisibleTextEquals(locatorText);
	    } else if(locator.equals("BUTTON")) {
	    	globalPageObjects.clickOnButtonByVisibleTextButton(locatorText);
	    }
	    TestBase.writeResponse("I Click on "+locatorText+" Button");
	}

	@When("I enter {string} dropdown value as {string}")
	public void i_enter_dropdown_value_as(String string, String text2Enter) {
		String[] splitText = string.split("=");
	    String locator = splitText[0];
	    String locatorText = splitText[1];
	    if(locator.equals("PH")) {
	    	globalPageObjects.enterTextByPlaceHolderAndEnter(locatorText, text2Enter);
	    } 
	    TestBase.writeResponse("I Enter "+locatorText+" Id As == "+text2Enter);
	}
	 */

	@When("I swith to frame by {string}")
	public void i_swith_to_frame_by(String string) {
		String[] splitText = string.split("=");
		String locator = splitText[0];
		String locatorText = splitText[1];
		if(locator.equalsIgnoreCase("ID")) {
			//driver.switchTo().frame(driver.findElement(By.id(locatorText)));
		} else if(locator.equalsIgnoreCase("dc")) {
			//driver.switchTo().defaultContent();
		} 
	}

	public boolean WaitForElementVisibility(String xpathString) {
		try {
			//page.waitForSelector(xpathString);

			page.waitForSelector(xpathString);

			// Locate the element
			Locator elementLocator = page.locator(xpathString);

			// Check if the element is visible
			boolean isVisible = elementLocator.isVisible();

			if(isVisible) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean WaitForSelectDropdownVisibility(String xpathString, int waitTime) {
		try {
			System.out.println("waitTime="+waitTime);
			//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(waitTime));
			//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathString))));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean customWaitIsDisplayed(String xpathString, int waitTime) {
		for(int i=1; i<=waitTime; i++) {
			try {
				//driver.findElement(By.xpath(xpathString)).isDisplayed();
				return true;
			} catch(Exception e) {
				try {
					Thread.sleep(1000);
				} catch (Exception e1) {
					//Do nothing
				}	
			}
		}
		return false; 
	}

	public boolean customWaitWebIsDisplayed(String xpathString, int waitTime) {
		for(int i=1; i<=waitTime; i++) {
			try {
				//System.out.println("driver.findElements(By.xpath(xpathString)).size="+driver.findElements(By.xpath(xpathString)).size());
				//				if(driver.findElements(By.xpath(xpathString)).size() > 0) {
				//					return true;
				//				}
				//Thread.sleep(1000);
			} catch(Exception e) {
				try {
					Thread.sleep(1000);
				} catch (Exception e1) {
					//Do nothing
				}	
			}
		}
		return false; 
	}


	public boolean customWaitIsMobileElementDisplayed(String xpathString, int waitTime) {
		int wait = waitTime/globalMobileCustomWaitTime();
		for(int i=1; i<=wait; i++) {
			try{
				//				if(driver.findElements(By.xpath(xpathString)).size() > 0) {
				//					Thread.sleep(3000);
				//					return true;
				//				}
				Thread.sleep(1000);
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false; 
	}


	public boolean fluentWaitElementVisibilityOf(String xpathString, int waitTime) {
		try {
			//FluentWait wait = new FluentWait(driver);
			//wait.withTimeout(Duration.ofSeconds(waitTime));
			//wait.pollingEvery(Duration.ofMillis(500));
			//wait.ignoring(NoSuchElementException.class);
			//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathString))));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	//	public String getXpath(String locatorText) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	//		String xpath = null;
	//		Method[] method = globalPageObjects.getClass().getMethods();
	//    	for(int i=0; i <(method.length); i++) {
	//    		if(method[i].getName().equals(locatorText)) {
	//    			xpath = (String) method[i].invoke(globalPageObjects);
	//    		}
	//    	}
	//    	return xpath;
	//	}

	@When("I scroll to {string} element and click")
	public void i_scroll_to_element_and_click(String string) {
		//driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+string+"\").instance(0))")).click();
	}

	/*
	@When("I scroll up")
	public void i_scroll_up() {
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Knet Test Card [KNET1]\").instance(0))").click();
		final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		var start = new Point(468, 1845);
		var end = new Point (472, 724);
		var swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
		    PointerInput.Origin.viewport(), start.getX(), start.getY()));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
		    PointerInput.Origin.viewport(), end.getX(), end.getY()));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(swipe));
	}*/

	public void scrollUp() {
		//driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
		//		+ ".scrollIntoView(new UiSelector()" + ".textMatches(\"" + "KFH[TAM]" + "\").instance(0))")); 
	}

	@When("I wait {string} seconds for {string}")
	public void i_wait_seconds_for(String wait, String string) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException {
		String xpath = "";
		boolean visibleStatus = false;
		String[] splitText = string.split("=");
		String locator = splitText[0];
		String locatorText = splitText[1];
		if(locator.equalsIgnoreCase("iospath")) {
			Instant start = Instant.now();
			xpath = getXpath(locatorText);
			visibleStatus = customWaitIsMobileElementDisplayed(xpath, globalMobileCustomWaitTime());
			Thread.sleep(10000);
			Instant end = Instant.now();
			Duration timeElapsed = Duration.between(start, end);
			System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");
		}
		System.out.println("xpath="+xpath);
		System.out.println("visibleStatus="+visibleStatus);
		if(visibleStatus) {
			TestBase.writeResponse("I waited "+wait+" seconds for "+string);
		} else {
			TestBase.writeFailedResponse("FAILED :: "+string + " not present in "+wait+" seconds. Please refere screenshot");
		}
		assertTrue("FAILED :: "+string + " not present in "+wait+" seconds. Please refere screenshot", visibleStatus);

	}

	@Then("sap I should see {string}")
	public void sap_i_should_see(String string) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String pageObject = getXpath(string);

		String[] splitText = pageObject.split("=");
		String locator = splitText[0];
		String locatorText = splitText[1];

		page.title();

	}

	@Then("I should see {string}")
	public void i_should_see(String string) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("Execution Started");
		String locator = getXpath(string);
		System.out.println("Locatio="+locator);
		//actualString = globalPageObjects.getText(xpath);

	}
	//	@When("sap I enter {string} as {string}")
	//	public void sap_i_enter_as(String string, String textToEnter, String pagename) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	//		
	//		//SAP_Login.Login();
	//		//String pageObject = getXpath(string);
	//		  Class<?> clazz = Class.forName(className);           
	//		  clazz.getMethod("printMessage").invoke(null);
	//		String[] splitText = pageObject.split("=");
	//	    String locator = splitText[0];
	//	    String locatorText = splitText[1];
	//	    if(locator.equalsIgnoreCase("LABEL")) {
	//	    	page.getByLabel(locatorText).fill(textToEnter);;
	//	    }
	//
	//	}

	@When("sap I click on {string}")
	public void sap_i_click_on(String string) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String pageObject = getXpath(string);

		String[] splitText = pageObject.split("=");
		String locator = splitText[0];
		String locatorText = splitText[1];
		if(locator.equalsIgnoreCase("Text")) {
			page.locator(pageObject).click();
		}
	}

	@When("I enter {string} as {string} in {string} page")
	public void i_enter_as_in_page(String string, String string2, String className) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		
		Class<?> clazz = Class.forName("pageObjects."+className); 
		Method method = clazz.getMethod(string);
		Object pageObject = method.invoke(null);
		System.out.println("page object"+pageObject.toString());
		String[] splitText = pageObject.toString().split("=");;
		String locator = splitText[0];
		String locatorText = splitText[1];
		if(locator.equalsIgnoreCase("LABEL")) {
			page.getByLabel(locatorText).fill(string2);;
		}

	}

	@When("I click on {string} in {string} page")
	public void i_click_on_in_page(String string, String string2) {

	}

}
