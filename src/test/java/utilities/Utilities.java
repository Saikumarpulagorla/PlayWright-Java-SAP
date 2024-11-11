package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Utilities {
	
	private WebDriver driver;
	
	public WindowHandlesUtil windowHandlesUtil;
	public WaitUtil waitUtil;
	
	public Utilities(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		windowHandlesUtil = new WindowHandlesUtil(this.driver);
		waitUtil = new WaitUtil(this.driver);
	}
	

}
