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
    public List<WebElement> cartItemNamesData;

    @FindBy (id = "checkout")
    public WebElement checkoutButton;

    @FindBy (id = "continue-shopping")
    public WebElement continueShoppingButton;

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