package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="user-name")
    WebElement usernameField; // Get "Username" field

    @FindBy(id="password")
    WebElement passwordField; // Get "Password" field

    @FindBy(id="login-button")
    WebElement loginButton; // Get "Login" button

    //------------------------------

    // Getters

    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    //----------------------------------------------

    // Util functions

    public void inputUsername(String username) { // Function for inputting username
        usernameField.clear(); // Clear
        usernameField.sendKeys(username); // Input string
    }

    public void inputPassword(String password) { // Function for inputting password
        passwordField.clear(); // Clear
        passwordField.sendKeys(password); // Input string
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }
}
