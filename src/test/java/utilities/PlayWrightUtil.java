package utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.Locator.WaitForOptions;

public class PlayWrightUtil {

	 private Page page;

	    public PlayWrightUtil(Page page) {
	        this.page = page;
	    }
	    
	    public void waitForElementToBeVisible(Locator locator, int timeout) {
	        locator.waitFor(new WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(timeout));
	    }
	    
	    public void scrollToElement(Locator locator) {
	        // Scroll to the element by evaluating JavaScript
	        locator.scrollIntoViewIfNeeded();
	    }
	    
	    public void moveToElement(Locator locator) {
	        locator.hover(null);; // Moves to (hovers over) the element
	    }
}
