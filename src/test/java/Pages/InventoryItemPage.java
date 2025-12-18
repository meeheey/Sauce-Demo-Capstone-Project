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
    public WebElement inventoryItemButton;

    //------------------------------

    public void clickOnInventoryItemButton() {
        inventoryItemButton.click();
    }
}
