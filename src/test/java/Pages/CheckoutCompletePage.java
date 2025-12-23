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
    WebElement completeHeader;

    @FindBy(className = "complete-text")
    WebElement completeText;

    @FindBy(id = "back-to-products")
    WebElement backHomeButton;

    //------------------------------


    public WebElement getCompleteHeader() {
        return completeHeader;
    }

    public WebElement getCompleteText() {
        return completeText;
    }
}
