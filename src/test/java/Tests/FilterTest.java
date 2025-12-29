package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FilterTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp(){
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickOnLoginButton();
    }

    @Test(priority = 16) // Test priorities correspond to test case IDs
    public void nameFilterTest() {
        homePage.chooseFilter("Name (Z to A)"); // We first have to change filter since "Name (A to Z)" is default
        homePage.chooseFilter("Name (A to Z)");
        List<String> itemNames = homePageWithFilter.getItemNames();
        for (int i = 0; i < excelReader.getLastRow("Name (A to Z)"); i++) { // Loop through item names and check if they correspond with filtered data in Excel file
            String itemName = excelReader.getStringData("Name (A to Z)", i+1, 0);
            Assert.assertEquals(itemName, itemNames.get(i));
        }
    }

    @Test(priority = 17) // Test priorities correspond to test case IDs
    public void reverseNameFilterTest() {
        homePage.chooseFilter("Name (Z to A)");
        List<String> itemNames = homePageWithFilter.getItemNames();
        for (int i = 0; i < excelReader.getLastRow("Name (Z to A)"); i++) { // Loop through item names and check if they correspond with filtered data in Excel file
            String itemName = excelReader.getStringData("Name (Z to A)", i+1, 0);
            Assert.assertEquals(itemName, itemNames.get(i));
        }
    }

    @Test(priority = 18) // Test priorities correspond to test case IDs
    public void priceFilterTest() {
        homePage.chooseFilter("Price (low to high)");
        List<String> itemNames = homePageWithFilter.getItemNames();
        for (int i = 0; i < excelReader.getLastRow("Price (low to high)"); i++) { // Loop through item names and check if they correspond with filtered data in Excel file
            String itemName = excelReader.getStringData("Price (low to high)", i+1, 0);
            Assert.assertEquals(itemName, itemNames.get(i));
        }
    }

    @Test(priority = 19) // Test priorities correspond to test case IDs
    public void reversePriceFilterTest() {
        homePage.chooseFilter("Price (high to low)");
        List<String> itemNames = homePageWithFilter.getItemNames();
        for (int i = 0; i < excelReader.getLastRow("Price (high to low)"); i++) { // Loop through item names and check if they correspond with filtered data in Excel file
            String itemName = excelReader.getStringData("Price (high to low)", i+1, 0);
            Assert.assertEquals(itemName, itemNames.get(i));
        }
    }
}
