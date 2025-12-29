package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepOneWithErrorMessagePage extends CheckoutStepOnePage { // Additional elements loaded by JS
    @FindBy(css = "h3[data-test='error']")
    WebElement errorMessage; // Get error message

    //----------------------------------

    // Getters

    public WebElement getErrorMessage() {
        return errorMessage;
    }

}
