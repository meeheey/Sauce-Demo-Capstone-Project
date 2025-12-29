package Tests;

import Base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class InventoryTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickOnLoginButton();
        wait.until(ExpectedConditions.urlContains("inventory"));
    }

    @Test(priority = 13) // Test priorities correspond to test case IDs
    public void itemNamesTest() {
        List<String> itemNames = homePage.getItemNames();
        for (int i = 0; i < excelReader.getLastRow("InventoryData"); i++) { // Loop through Excel file data and check if item data on site is correct
            String itemName = excelReader.getStringData("InventoryData", i+1, 0);
            Assert.assertEquals(itemName, itemNames.get(i));
        }
    }

    @Test(priority = 14) // Test priorities correspond to test case IDs
    public void itemDescriptionsTest() {
        List<String[]> itemDescriptions = homePage.getItemDescriptions();
        for (int i = 0; i < excelReader.getLastRow("InventoryData"); i++) { // Loop through Excel file data and check if item data on site is correct
            String itemDescription = excelReader.getStringData("InventoryData", i+1, 1);
            Assert.assertEquals(itemDescription, itemDescriptions.get(i)[1]);
        }
    }

    @Test(priority = 15) // Test priorities correspond to test case IDs
    public void itemPricesTest() {
        List<String[]> itemPrices = homePage.getItemPrices();
        for (int i = 0; i < excelReader.getLastRow("InventoryData"); i++) { // Loop through Excel file data and check if item data on site is correct
            String itemPrice = excelReader.getStringData("InventoryData", i+1, 2);
            Assert.assertEquals(itemPrice, itemPrices.get(i)[1]);
        }
    }

}
