package Pages;

import Base.BaseTest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BaseTest {

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="react-burger-menu-btn")
    WebElement menuButton;

    @FindBy(className = "inventory_item")
    List<WebElement> inventoryItems;

    @FindBy(className = "inventory_item_name")
    List<WebElement> inventoryItemLinks;

    @FindBy(className = "btn_inventory")
    List<WebElement> inventoryItemButtons;

    @FindBy (className = "shopping_cart_link")
    WebElement shoppingCartLink;

    //-----------------------------------------

    public WebElement getMenuButton() {
        return menuButton;
    }

    public List<WebElement> getInventoryItems() {
        return inventoryItems;
    }

    public List<WebElement> getInventoryItemLinks() {
        return inventoryItemLinks;
    }

    public List<WebElement> getInventoryItemButtons() {
        return inventoryItemButtons;
    }

    public WebElement getShoppingCartLink() {
        return shoppingCartLink;
    }


    //-----------------------------------------

    public void clickOnMenuButton() {
        menuButton.click();
    }

    public List<String> getItemNames() {
        List<String> itemNames = new ArrayList<>();
        for (WebElement inventoryItem : inventoryItems) {
            String[] lines = inventoryItem.getText().split("\n");
            itemNames.add(lines[0]);
        }
        return itemNames;
    }

    public List<String[]> getItemDescriptions() {
        List<String[]> itemDescriptions = new ArrayList<>();
        for (WebElement inventoryItem : inventoryItems) {
            String[] lines = inventoryItem.getText().split("\n");
            itemDescriptions.add(new String[]{lines[0], lines[1]});
        }
        return itemDescriptions;
    }

    public List<String[]> getItemPrices() {
        List<String[]> itemDescriptions = new ArrayList<>();
        for (WebElement inventoryItem : inventoryItems) {
            String[] lines = inventoryItem.getText().split("\n");
            itemDescriptions.add(new String[]{lines[0], lines[2]});
        }
        return itemDescriptions;
    }

    public void clickOnShoppingCartLink() {
        shoppingCartLink.click();
    }
}
