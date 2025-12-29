package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryItemPage extends BaseTest {
    public InventoryItemPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy (className = "btn_inventory")
    WebElement inventoryItemButton; // Get inventory item buttons

    //------------------------------

    // Getters

    public WebElement getInventoryItemButton() {
        return inventoryItemButton;
    }

    //------------------------------

    // Util functions

    public void clickOnInventoryItemButton() {
        inventoryItemButton.click();
    }
}
