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
    WebElement finishButton; // Get "Finish" button

    @FindBy(className = "summary_subtotal_label")
    WebElement subtotalLabel; // Get subtotal label

    @FindBy(className = "cart_item_label")
    List<WebElement> inventoryItems; // Get list of inventory items

    //-----------------------------------

    // Util functions

    public void clickOnFinishButton() {
        finishButton.click();
    }

    public float getSubtotal() {
        return Float.parseFloat(subtotalLabel.getText().replaceAll("[^\\d.-]", "")); // Get text from label, clean from non-float chars by regex, parse float
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

}
