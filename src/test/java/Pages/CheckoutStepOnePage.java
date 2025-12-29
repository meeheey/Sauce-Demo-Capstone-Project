package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepOnePage extends BaseTest {
    public CheckoutStepOnePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    WebElement firstNameField; // Get "First Name" field

    @FindBy(id = "last-name")
    WebElement lastNameField; // Get "Last Name" field

    @FindBy(id = "postal-code")
    WebElement postalCodeField; // Get "Postal code/zip" field

    @FindBy(id = "continue")
    WebElement continueButton; // Get "Continue" button

    //--------------------------------

    // Util functions

    public void inputFirstName(String firstName) { // Function for inputting first name
        firstNameField.clear(); // Clear
        firstNameField.sendKeys(firstName); // Input string
    }

    public void inputLastName(String lastName) { // Function for inputting last name
        lastNameField.clear(); // Clear
        lastNameField.sendKeys(lastName); // Input string
    }

    public void inputPostalCode(String postalCode) { // Function for inputting last name
        postalCodeField.clear(); // Clear
        postalCodeField.sendKeys(postalCode); // Input string
    }

    public void clickOnContinueButton() {
        continueButton.click();
    }
}
