package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.core.backend.Status;
import testbase.TestBase;

public class WaitUtil {
	private WebDriver driver;

	
	public WaitUtil(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void WaitForExpectedTextToBePresentUpdate(WebElement element, String expectedText, int timeOutInSeconds)
			throws InterruptedException {
		String text = null;
		for (int i = 0; i <= timeOutInSeconds; i++) {
			try {
				text = element.getText();
				//System.out.println("*****************text="+text);
				if (text.equals(expectedText)) {
					break;
				} else {
					Thread.sleep(500);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
	}
	
	public void WaitForElementToBePresentUpdate(WebElement element, int timeOutInSeconds)
			throws InterruptedException {
		for (int i = 0; i <= timeOutInSeconds; i++) {
			try {
				if (element.isDisplayed()) {
					break;
				} else {
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
	}
	
	public void WaitAndClick(WebElement element, int timeOutInSeconds) throws InterruptedException {
		for (int i = 0; i <= timeOutInSeconds; i++) {
			try {
				element.click();
			} catch (Exception e) {
				Thread.sleep(1000);
				// e.printStackTrace();
			}
		}
	}
	
	public void WaitForGivenTime(int timeOutInSeconds) throws InterruptedException {
		for (int i = 0; i <= timeOutInSeconds; i++) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String WaitForExpectedTitle(String expectedTitle, int waitTime) {
		String title = "";
		try {
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(waitTime));
			wait.until(ExpectedConditions.titleIs(expectedTitle));
			title = driver.getTitle();
			if(title.equalsIgnoreCase(expectedTitle)) {
				return title;
			} else {
				return title;
			}
		} catch (Exception e) {
			return title;
		}
	}
	
	
}
