package Pages;

import Base.BaseTest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BaseTest {

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    // SauceDemo has unusual structure (there is no page called "Home Page"), so this is the page you end at after logging in, which can also be called "Inventory Page".

    @FindBy(id="react-burger-menu-btn")
    WebElement menuButton; // Get menu button

    @FindBy(className = "inventory_item")
    List<WebElement> inventoryItems; // Get list of inventory items

    @FindBy(className = "inventory_item_name")
    List<WebElement> inventoryItemLinks; // Get list of inventory item links

    @FindBy(className = "btn_inventory")
    List<WebElement> inventoryItemButtons; // Get list of inventory item buttons

    @FindBy (className = "shopping_cart_link")
    WebElement shoppingCartLink; // Get link to shopping cart

    @FindBy (className = "product_sort_container")
    WebElement filterDropdownMenu; // Get dropdown menu for filtering

    //-----------------------------------------

    // Getters

    public List<WebElement> getInventoryItemLinks() {
        return inventoryItemLinks;
    }

    public List<WebElement> getInventoryItemButtons() {
        return inventoryItemButtons;
    }

    //-----------------------------------------

    // Util functions

    public void clickOnMenuButton() {
        menuButton.click();
    }

    public List<String> getItemNames() {
        List<String> itemNames = new ArrayList<>();
        for (WebElement inventoryItem : inventoryItems) { // Loop through items
            String[] lines = inventoryItem.getText().split("\n"); // Get text from WebObject, parse it into array of lines
            itemNames.add(lines[0]); // Add first element of array to list
        }
        return itemNames;
    }

    public List<String[]> getItemDescriptions() {
        List<String[]> itemDescriptions = new ArrayList<>();
        for (WebElement inventoryItem : inventoryItems) { // Loop through items
            String[] lines = inventoryItem.getText().split("\n"); // Get text from WebObject, parse it into array of lines
            itemDescriptions.add(new String[]{lines[0], lines[1]}); // Create key:value pair and add it to list
        }
        return itemDescriptions;
    }

    public List<String[]> getItemPrices() {
        List<String[]> itemDescriptions = new ArrayList<>();
        for (WebElement inventoryItem : inventoryItems) { // Loop through items
            String[] lines = inventoryItem.getText().split("\n"); // Get text from WebObject, parse it into array of lines
            itemDescriptions.add(new String[]{lines[0], lines[2]}); // Create key:value pair and add it to list
        }
        return itemDescriptions;
    }

    public void chooseFilter(String filter) { // Takes filter name as an input
        Select selectFilter = new Select(filterDropdownMenu); // Declare and initialize Select object for dealing with dropdown
        selectFilter.selectByVisibleText(filter);
    }

    public void clickOnShoppingCartLink() {
        shoppingCartLink.click();
    }
}
