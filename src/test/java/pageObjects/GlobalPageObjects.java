package pageObjects;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

import utilities.Utilities;
import utilities.WaitUtil;

public class GlobalPageObjects  {
	
	private Page page;
	//Utilities util = new Utilities(driver);
	//WaitUtil waitUtil = new WaitUtil(driver);

	public GlobalPageObjects(Page page) {
		this.page = page;
	}
	
	public boolean clickOnElement(String xpathString) {
		try{
			//page.locator(xpathString).click();
			page.click(xpathString);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String getText(String xpathString) {
		try{
			ElementHandle element = page.querySelector("xpath=" + xpathString);
			String text = element.textContent();
			return text;
		} catch(Exception e) {
			return "";
		}
	}
	
	public boolean isIOSElementDisplayed(String xpathString) {
		try{
			// Locate elements using XPath
            List<ElementHandle> elements = page.querySelectorAll("xpath=" + xpathString);
			if(elements.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean clearText(String xpathString) {
		try {
			page.locator(xpathString).clear();
			//driver.findElement(By.xpath(xpathString)).clear();
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public boolean clearText(Page page, String xpathString) {
		try {
			page.locator(xpathString).clear();
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public boolean enterText(String xpathString, String textToEnter) {
		try{
			page.locator("xpath=" + xpathString).fill(textToEnter);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean enterText(Page page, String xpathString, String textToEnter) {
		try{
			page.locator(xpathString).fill(textToEnter);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean selectValueFromDropDownByXpath(String selectName, String selectString) {
		try {
			Locator dropdownLocator = page.locator(selectName);
			// Ensure the dropdown options are present
            List<String> optionLabels = (List<String>) dropdownLocator.evaluate("dropdown => Array.from(dropdown.options).map(option => option.label)");
            System.out.println("Available options: " + optionLabels);

            // Check if the desired option is available
            if (optionLabels.contains(selectString)) {
                // Select the option by label
                dropdownLocator.selectOption(new SelectOption().setLabel(selectString));
                System.out.println("Selected label: " + selectString);
            } else {
                System.out.println("Desired option label not found in the dropdown options.");
            }

			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean moveToElement(String xpathString) {
		try {
			// Hover over the element
            page.hover("xpath=" + xpathString);
            return true;
		} catch(Exception e) {
			return false;
		}
		
	}
	
	public void enterTextByLabel(String locatortext,String string) {
		page.getByLabel(locatortext).fill(string);
	}
	public void enterTextByPlaceHolder(String locatortext,String string) {
		page.getByPlaceholder(locatortext).fill(string);
	}
	public void clickOnTextByVisibleText(String VisibleText) {
		Locator text = page.locator("text="+VisibleText);
		text.click();
	}
	public void clickOnTextByVisibleTextFromAutoSuggestion(String VisibleText) {
		Locator text = page.locator("span").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^"+VisibleText+"$")));;
		text.waitFor();
		text.click();
	}
	public void clickOnEnter(String key) {
		page.keyboard().press(key);
	}
	
	/*
	public boolean isElementPresentByVisibleText(String visibleText) {
		return driver.findElement(By.xpath("//*[contains(text(),'"+visibleText+"')]")).isDisplayed();
	}
	
	public boolean getTextById(String visibleText) {
		return driver.findElement(By.xpath("//*[contains(text(),'"+visibleText+"')]")).isDisplayed();
	}
	
	public boolean isElementPresentById(String id) {
		return driver.findElement(By.xpath("//*[@id='"+id+"']")).isDisplayed();
	}
	
	public boolean isElementPresentByName(String name) {
		return driver.findElement(By.xpath("//*[@name='"+name+"']")).isDisplayed();
	}
	
	public void enterTextByPlaceHolder(String placeHolder, String textToEnter) {
		driver.findElement(By.xpath("//*[contains(@placeholder,'"+placeHolder+"')]")).clear();
		driver.findElement(By.xpath("//*[contains(@placeholder,'"+placeHolder+"')]")).sendKeys(textToEnter);
	}
	public void enterTextByPlaceHolderAndEnter(String placeHolder, String textToEnter) {
		driver.findElement(By.xpath("//*[contains(@placeholder,'"+placeHolder+"')]")).clear();
		driver.findElement(By.xpath("//*[contains(@placeholder,'"+placeHolder+"')]")).sendKeys(textToEnter);
		driver.findElement(By.xpath("//*[contains(@placeholder,'"+placeHolder+"')]")).sendKeys(Keys.ENTER);
	}
	
	public void enterTextById(String id, String textToEnter) {
		driver.findElement(By.xpath("//*[@id='"+id+"']")).sendKeys(textToEnter);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'COMPOSE')]")));
	}
	
	public void enterTextByName(String name, String textToEnter) {
		driver.findElement(By.xpath("//*[@name='"+name+"']")).sendKeys(textToEnter);
	}
	*/
	
//	public void enterTextByLabel(String Label, String textToEnter) {
//		driver.findElement(By.xpath("//*[contains(text(),'"+Label+"')]/../../..//input")).sendKeys(textToEnter);
//	}
	
//	public void clickOnElementByVisibleText(String visibleText) {
//		driver.findElement(By.xpath("//*[contains(text(),'"+visibleText+"')]")).click();
//	}
	
//	public void clickOnElementById(String xpathString, String locator) {
//		//boolean status = WaitForElementClickable(xpathString, 10);
//		//System.out.println("status="+status);
//		if(true) {
//			driver.findElement(By.xpath(xpathString)).click();
//			TestBase.writeResponse("I Click on "+locator);
//		} else {
//			TestBase.writeResponse("FAILED to Click on "+locator + ". Please refere screenshot");
//		}
//		assertTrue("FAILED to Click on "+locator + ". Please refere screenshot", status);
//	}
	
//	public void clickOnElementByName(String name) {
//		driver.findElement(By.xpath("//*[@name='"+name+"']")).click();
//	}
//	
//	public void clickOnElementByLabel(String Label) {
//		driver.findElement(By.xpath("//*[contains(text(),'"+Label+"')]/../../..//input"));
//	}
	
//	public void clickOnElementByDI(String di) {
//		WebElement ele = driver.findElement(By.xpath("(//*[@data-icon='"+di+"'])[1]/.."));
//		JavascriptExecutor jse = (JavascriptExecutor)driver;
//		jse.executeScript("arguments[0].click()", ele);
//	}
//	public void clickOnElementByVisiualTextAndJavaScript(String visibleText) {
//		WebElement ele = driver.findElement(By.xpath("//*[contains(text(),'"+visibleText+"')]"));
//		JavascriptExecutor jse = (JavascriptExecutor)driver;
//		jse.executeScript("arguments[0].click()", ele);
//	}
//	public void clickOnElementByType(String visibleText) {
//		driver.findElement(By.xpath("//*[@type='"+visibleText+"']")).click();
//	}
//	public void clickOnRadioButtonByVisibleText(String visibleText) {
//		driver.findElement(By.xpath("//*[contains(text(),'"+visibleText+"')]/span")).click();
//	}
	
/*	
	public void moveToElementAndClickByVisibleText(String visibleText) {
		WebElement element = driver.findElement(By.xpath("//*[contains(text(),'"+visibleText+"')]"));
		Actions action = new Actions(driver);
		action.moveToElement(element, 0, 0);
		element.click();
	}
	public void moveToElementByVisibleText(String visibleText) {
		WebElement element = driver.findElement(By.xpath("//*[contains(text(),'"+visibleText+"')]"));
		Actions action = new Actions(driver);
		action.moveToElement(element, 0, 0);
	}
//	public void clickOnDropDownByVisibleText(String visibleText) {
//		driver.findElement(By.xpath("//*[contains(text(),'"+visibleText+"')]/..//i/*[@data-icon='down']")).click();
//		//driver.findElement(By.xpath("//*[contains(text(),'"+visibleText+"')]/../div/div")).click();
//	}
	
	public void clickOndownarrowByVisibleText(String visibleText) {
		driver.findElement(By.xpath("//*[contains(text(),'"+visibleText+"')]/../../span")).click();
	}
	
	public void clickOnSubmitFormByVisibleText(String visibleText) {
		driver.findElement(By.xpath("//*[contains(text(),'"+visibleText+"')]/../../button[@type='submit']"));
	}
	
	
	public void clickOnButtonByVisibleTextEquals(String visibleText) {
		driver.findElement(By.xpath("//*[text()='"+visibleText+"']/../..")).click();
	}
	
	public void clickOnButtonByVisibleTextButton(String visibleText) {
		driver.findElement(By.xpath("//button[contains(text(),'"+visibleText+"')]")).click();
	}
	
	public void clickOnButtonByVisibleText(String visibleText) {
		driver.findElement(By.xpath("//*[contains(text(),'"+visibleText+"')]/..")).click();
	}
	
	public void clickOnButtonByVisibleTextJavaScript(String visibleText) {
		//driver.findElement(By.xpath("//*[contains(text(),'"+visibleText+"')]/..")).click();
		WebElement ele = driver.findElement(By.xpath("//*[contains(text(),'"+visibleText+"')]/.."));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", ele);
	}
	
	public void clickOnButtonByVisibleTextActionClass(String visibleText) {
		//driver.findElement(By.xpath("//*[contains(text(),'"+visibleText+"')]/..")).click();
		WebElement element = driver.findElement(By.xpath("//*[contains(text(),'"+visibleText+"')]/.."));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
	}
	
	public void clickOnButtonById(String id) {
		driver.findElement(By.xpath("//*[@id='"+id+"']")).click();
	}
	
	public void clickOnButtonByName(String name) {
		driver.findElement(By.xpath("//*[@name='"+name+"']")).click();
	}
	*/

}
