package stepDefinitions;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.awt.Dimension;
import java.awt.JobAttributes.DialogType;
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
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.microsoft.playwright.*;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pageObjects.GlobalPageObjects;
import pageObjects.Homepage;
import testbase.TestBase;
import utilities.PlayWrightUtil;
import utilities.Utilities;
import com.microsoft.playwright.assertions.PlaywrightAssertions;



public class GlobalStepDefs extends TestBase {

	GlobalPageObjects globalPageObjects = new GlobalPageObjects(page);
	PlayWrightUtil PlaywrightUtil = new PlayWrightUtil(page);
	//	Utilities util = new Utilities(driver);

	@Given("I navigate to web application")
	public void i_navigate_to_web_application() {
		try {
			page.navigate(CONFIG.getProperty("TEST_URL"));
			TestBase.writeResponse("Page Title: "+page.title());
		} catch (Exception e) {
			TestBase.writeResponse("Unable to Load the URL"+CONFIG.getProperty("TEST_URL"));
		}
	}

	@Given("I wait for page to load")
	public void i_wait_for_page_to_load() {
		page.waitForLoadState(LoadState.NETWORKIDLE, new Page.WaitForLoadStateOptions().setTimeout(60000));
	}

	@Given("I wait for {int} milli Seconds")
	public void i_wait_for_seconds(Integer int1) throws Exception {
		Thread.sleep(int1);
	}

	@Then("I should see page title as {string}")
	public void i_should_see_page_title_as(String string) {
	}

	@Then("I should see {string} as {string}")
	public void i_see_as(String string, String string2) {
		PlaywrightAssertions.assertThat(page).hasTitle(string2);
	}

	@Then("I should see Submit_Button in {string}")
	public void i_should_see_submit_button_in(String string) {
		page.evaluate("window.scrollTo(0, document.body.scrollHeight)");
		//		 boolean isScrollable = page.evaluate("() => document.documentElement.scrollHeight > document.documentElement.clientHeight");
		//         System.out.println("Is page scrollable? " + isScrollable);
	}

	@Then("I should see {string} in {string}")
	public void i_see_in(String string, String className) throws Exception, SecurityException {
		Class<?> clazz = Class.forName("pageObjects."+className); 
		Method method = clazz.getMethod(string);
		Object pageObject = method.invoke(null);
		//System.out.println("page object"+pageObject.toString());
		String[] splitText = pageObject.toString().split("==");;
		String locator = splitText[0];
		String locatorText = splitText[1];
		System.out.println(locatorText);

		if(locator.equals("text")) {
			try {
				Locator ele = page.locator("text="+locatorText);
				String actualText= ele.textContent();
				assertEquals("element not displayed",locatorText , actualText);
			} catch (Exception e) {
				writeResponse("Test case failed Because Element Not found");
				assertEquals("Element Not found with given locator:",locatorText , "Eelement Not Found");
			}
		} else if (locator.equals("LABEL")) {
			try {
				Locator ele = page.getByLabel(locatorText);
				String eleText = ele.textContent();
				PlaywrightUtil.scrollToElement(ele);
				assertEquals("element not displayed",locatorText , eleText);
			} catch (Exception e) {
				writeResponse("Test case failed Because of assertion");
			}
		}

		else if (locator.equals("ID")) {
			try {
				Locator ele = page.locator(locatorText);
				PlaywrightUtil.scrollToElement(ele);
				String eleText = ele.textContent();
				assertEquals("element not displayed", eleText, locatorText);

			} catch (Exception e) {
				writeResponse("Test case failed Because of assertion");
			}
			
		} 

		else if (locator.equals("XPATH")) {
			try {
				Locator ele = page.locator(locatorText);
				PlaywrightUtil.scrollToElement(ele);
				String eleText = ele.textContent();
				assertEquals("element not displayed", eleText, locatorText);

			} catch (Exception e) {
				writeResponse("Test case failed Because of assertion");
			}
		}

	}

	@Then("I move to {string} in {string}")
	public void i_move_to_in(String string, String className) throws Exception {
		Class<?> clazz = Class.forName("pageObjects."+className); 
		Method method = clazz.getMethod(string);
		Object pageObject = method.invoke(null);
		//System.out.println("page object"+pageObject.toString());
		String[] splitText = pageObject.toString().split("==");;
		String locator = splitText[0];
		String locatorText = splitText[1];

		if(locator.equals("LABEL")) {
			Locator ele = page.getByLabel(locatorText);
			PlaywrightUtil.moveToElement(ele);
		} else if(locator.equals("ID")) {
			Locator ele = page.locator(locatorText);
			PlaywrightUtil.moveToElement(ele);
		} else if (locator.equals("XPATH")) {
			Locator ele = page.locator(locatorText);
			PlaywrightUtil.moveToElement(ele);
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

	@When("I enter {string} as {string} in {string} page")
	public void i_enter_as_in_page(String string, String string2, String className) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<?> clazz = Class.forName("pageObjects."+className); 
		Method method = clazz.getMethod(string);
		Object pageObject = method.invoke(null);
		System.out.println("page object"+pageObject.toString());
		String[] splitText = pageObject.toString().split("==");;
		String locator = splitText[0];
		String locatorText = splitText[1];

		if(locator.equalsIgnoreCase("LABEL")) {
			page.getByLabel(locatorText).fill(string2);;
		}
	}

	@When("I enter {string} as {string} in {string}")
	public void i_enter_as_in(String string, String TextToEnter, String className) throws Exception {
		Class<?> clazz = Class.forName("pageObjects."+className); 
		Method method = clazz.getMethod(string);
		Object pageObject = method.invoke(null);
		System.out.println("page object"+pageObject.toString());
		String[] splitText = pageObject.toString().split("==");;
		String locator = splitText[0];
		String locatorText = splitText[1];

		if(locator.equals("LABEL")) {
			Locator ele = page.getByLabel(locatorText);
			PlaywrightUtil.moveToElement(ele);
			PlaywrightUtil.waitForElementToBeVisible(ele, 15000);
			ele.clear();
			ele.fill(TextToEnter);
		} else if(locator.equals("ID")) {
			Locator ele = page.locator(locatorText);
			PlaywrightUtil.moveToElement(ele);
			PlaywrightUtil.waitForElementToBeVisible(ele, 15000);
			ele.clear();
			ele.fill(TextToEnter);
		} else if (locator.equals("XPATH")) {
			Locator ele = page.locator(locatorText);
			PlaywrightUtil.moveToElement(ele);
			PlaywrightUtil.waitForElementToBeVisible(ele, 15000);
			ele.clear();
			ele.fill(TextToEnter);
		}
	}

	@When("I click on {string} in {string}")
	public void i_click_on_in(String string, String className) throws Exception {
		Class<?> clazz = Class.forName("pageObjects."+className); 
		Method method = clazz.getMethod(string);
		Object pageObject = method.invoke(null);
		System.out.println("page object"+pageObject.toString());
		String[] splitText = pageObject.toString().split("==");
		String locator = splitText[0];
		System.out.println("locator= "+locator);
		String locatorText = splitText[1];

		if(locator.equals("text")) {
			Locator ele = page.locator("text="+locatorText).last();
			PlaywrightUtil.moveToElement(ele);
			PlaywrightUtil.waitForElementToBeVisible(ele, 15000);
			ele.click();
		} else if (locator.equals("TEXT")) {
			page.locator("span").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^"+locatorText+"$"))).click();
		} else if(locator.equals("ID")) {
			Locator ele = page.locator(locatorText);
			PlaywrightUtil.moveToElement(ele);
			PlaywrightUtil.waitForElementToBeVisible(ele, 15000);
			ele.click();
		} else if (locator.equals("XPATH")) {
			Locator ele = page.locator(locatorText);
			PlaywrightUtil.moveToElement(ele);
			PlaywrightUtil.waitForElementToBeVisible(ele, 15000);
			ele.click();
		}
		else if (locator.equals("TITLE")) {
			Locator ele = page.getByTitle(locatorText);
			PlaywrightUtil.moveToElement(ele);
			PlaywrightUtil.waitForElementToBeVisible(ele, 15000);
			ele.click();
		}
		else
		{
			page.getByText(locatorText).nth(2).click();
		}
	}

	@Then("I click on {string} of {string} in {string}")
	public void i_click_on_of_in(String string, String string2, String className) throws ClassNotFoundException, Exception, SecurityException {
		Class<?> clazz = Class.forName("pageObjects."+className); 
		Method method = clazz.getMethod(string);
		Object pageObject = method.invoke(null);
		System.out.println("page object"+pageObject.toString());
		String[] splitText = pageObject.toString().split("==");
		String locator = splitText[0];
		System.out.println("locator= "+locator);
		String locatorText = splitText[1];


		Method method2 = clazz.getMethod(string2);
		Object pageObject2 = method2.invoke(null);
		System.out.println("page object"+pageObject2.toString());
		String[] splitText2 = pageObject2.toString().split("==");
		String locator2 = splitText2[0];
		System.out.println("locator= "+locator2);
		String locatorText2 = splitText2[1];
		if(locator.equals("LABEL")&&locator2.equals("LABEL"))
		{
			page.getByLabel(locatorText2).getByLabel(locatorText).click();
		}
		{

		}
	}
	@Then("I click on {string} in {string} in {string}")
	public void i_click_on_in_in(String string, String string2, String className) throws Exception {
		Class<?> clazz = Class.forName("pageObjects."+className); 
		Method method = clazz.getMethod(string);
		Object pageObject = method.invoke(null);
		System.out.println("page object"+pageObject.toString());
		String[] splitText = pageObject.toString().split("==");
		String locator = splitText[0];
		System.out.println("locator= "+locator);
		String locatorText = splitText[1];


		Method method2 = clazz.getMethod(string2);
		Object pageObject2 = method2.invoke(null);
		System.out.println("page object"+pageObject2.toString());
		String[] splitText2 = pageObject2.toString().split("==");
		String locator2 = splitText2[0];
		System.out.println("locator= "+locator2);
		String locatorText2 = splitText2[1];
		if(locator.equals("text")&&locator2.equals("LABEL"))
		{
			page.getByLabel(locatorText2).getByText(locatorText).click();
		}
	}
	public void handleAlert() {
		// Attach the onDialog handler to listen for dialog events
		page.onDialog(dialog -> {
			dialog.accept();

		});
	}  
	@Then("I accept the alert in {string}")
	public void i_accept_the_alert_in(String string) {
		page.onDialog(dialog -> {
			dialog.accept();

		});
	}
	
	/*
	 * public String textContent(String locatorText) { try { Locator ele =
	 * page.locator("text="+locatorText); return ele.textContent(); } catch
	 * (Exception e) { return "Unable to locate element"; } }
	 */
}
