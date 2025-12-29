package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BaseTest {
    public CartPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[data-test='inventory-item-name']")
    List<WebElement> cartItemNamesData; // Get list of item names

    @FindBy (id = "checkout")
    WebElement checkoutButton; // Get "Checkout" button

    //----------------------

    // Getters

    public List<WebElement> getCartItemNamesData() {
        return cartItemNamesData;
    }

    //----------------------

    // Util functions

    public ArrayList<String> getCartItemNamesList(){
        ArrayList<String> cartItemNames = new ArrayList<>();
        for (WebElement cartItemName : cartItemNamesData) { // Loops through all item names
            cartItemNames.add(cartItemName.getText()); // Extracts text and adds it to the list
        }
        return cartItemNames; // Returns the list
    }

    public void clickOnCheckoutButton(){
        checkoutButton.click();
    }

}