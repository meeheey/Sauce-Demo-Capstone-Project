package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepOneWithErrorMessagePage extends CheckoutStepOnePage {
    @FindBy(css = "h3[data-test='error']")
    WebElement errorMessage;

    public WebElement getErrorMessage() {
        return errorMessage;
    }

}
