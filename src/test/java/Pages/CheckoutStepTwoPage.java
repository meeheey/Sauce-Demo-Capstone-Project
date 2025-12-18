package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CheckoutStepTwoPage extends BaseTest {
    public CheckoutStepTwoPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(className = "summary_subtotal_label")
    WebElement subtotalLabel;

    @FindBy(className = "cart_item_label")
    List<WebElement> inventoryItems;

    //-----------------------------------

    public void clickOnFinishButton() {
        finishButton.click();
    }

    public float getSubtotal() {
        return Float.parseFloat(subtotalLabel.getText().replaceAll("[^\\d.-]", ""));
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

}
