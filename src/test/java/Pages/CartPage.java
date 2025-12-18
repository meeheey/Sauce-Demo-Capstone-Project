package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BaseTest {
    public CartPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[data-test='inventory-item-name']")
    List<WebElement> cartItemNamesData;

    @FindBy (id = "checkout")
    WebElement checkoutButton;

    @FindBy (id = "continue-shopping")
    WebElement continueShoppingButton;

    //----------------------

    public List<WebElement> getCartItemNamesData() {
        return cartItemNamesData;
    }

    public WebElement getCheckoutButton() {
        return checkoutButton;
    }

    public WebElement getContinueShoppingButton() {
        return continueShoppingButton;
    }

    //----------------------

    public ArrayList<String> getCartItemNamesList(){
        ArrayList<String> cartItemNames = new ArrayList<>();
        for (WebElement cartItemName : cartItemNamesData) {
            cartItemNames.add(cartItemName.getText());
        }
        return cartItemNames;
    }

    public void clickOnCheckoutButton(){
        checkoutButton.click();
    }

}