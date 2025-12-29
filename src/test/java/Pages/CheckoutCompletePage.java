package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends BaseTest {
    public CheckoutCompletePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "complete-header")
    WebElement completeHeader; // Get "Complete" header

    @FindBy(className = "complete-text")
    WebElement completeText; // Get "Complete" text

    //------------------------------

    // Getters

    public WebElement getCompleteHeader() {
        return completeHeader;
    }

    public WebElement getCompleteText() {
        return completeText;
    }
}
