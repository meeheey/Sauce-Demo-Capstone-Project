package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartWithItemsPage extends CartPage {

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge; // Get cart badge

    @FindBy(className = "cart_button")
    List<WebElement> cartItemButtons; // Get cart item buttons

    //---------------------------------

    // Getters
    public List<WebElement> getCartItemButtons() {
        return cartItemButtons;
    }

    //---------------------------------

    // Util functions

    public int getCountItems() {
        return Integer.parseInt(cartBadge.getText()); // Get text from counter and convert it to integer
    }

}
