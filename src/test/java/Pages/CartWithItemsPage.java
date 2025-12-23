package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartWithItemsPage extends CartPage {

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;

    @FindBy(className = "cart_button")
    List<WebElement> cartItemButtons;

    //---------------------------------

    public int getCountItems() {
        return Integer.parseInt(cartBadge.getText());
    }

    public List<WebElement> getCartItemButtons() {
        return cartItemButtons;
    }
}
