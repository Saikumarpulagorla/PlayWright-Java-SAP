package utilities;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBase;

public class WindowHandlesUtil extends TestBase {

	private WebDriver driver;
	String parentWindow;
	
	public WindowHandlesUtil(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void switchToNewWindow() {
		//Store the ID of the parentWindow window
		parentWindow = driver.getWindowHandle();

		//		//Loop through until we find a new window handle
		//		for (String windowHandle : driver.getWindowHandles()) {
		//		    if(!parentWindow.contentEquals(windowHandle)) {
		//		        driver.switchTo().window(windowHandle);
		//		        break;
		//		    }
		//		}
		Set<String>s=driver.getWindowHandles();

		Iterator<String> I1= s.iterator();
		while(I1.hasNext())
		{
			String child_window=I1.next();
			if(!parentWindow.equals(child_window))
			{	
				driver.switchTo().window(child_window);
				System.out.println(driver.switchTo().window(child_window).getTitle());
				//driver.close();
			}
		}
	}

	public void SwitchToParentWindow() {
		//switch to the parent window
		driver.switchTo().window(parentWindow);
	}

}
