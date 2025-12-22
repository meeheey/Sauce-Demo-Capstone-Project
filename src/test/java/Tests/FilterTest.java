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
        loginPage.clickOnSubmitButton();
    }

    @Test
    public void nameFilterTest() {
        homePage.chooseFilter("Name (Z to A)");
        homePage.chooseFilter("Name (A to Z)");
        List<String> itemNames = homePageWithFilter.getItemNames();
        for (int i = 0; i < excelReader.getLastRow("Name (A to Z)"); i++) {
            String itemName = excelReader.getStringData("Name (A to Z)", i+1, 0);
            Assert.assertEquals(itemName, itemNames.get(i));
        }
    }

    @Test
    public void reverseNameFilterTest() {
        homePage.chooseFilter("Name (Z to A)");
        List<String> itemNames = homePageWithFilter.getItemNames();
        for (int i = 0; i < excelReader.getLastRow("Name (Z to A)"); i++) {
            String itemName = excelReader.getStringData("Name (Z to A)", i+1, 0);
            Assert.assertEquals(itemName, itemNames.get(i));
        }
    }

    @Test
    public void priceFilterTest() {
        homePage.chooseFilter("Price (low to high)");
        List<String> itemNames = homePageWithFilter.getItemNames();
        for (int i = 0; i < excelReader.getLastRow("Price (low to high)"); i++) {
            String itemName = excelReader.getStringData("Price (low to high)", i+1, 0);
            Assert.assertEquals(itemName, itemNames.get(i));
        }
    }

    @Test
    public void reversePriceFilterTest() {
        homePage.chooseFilter("Price (high to low)");
        List<String> itemNames = homePageWithFilter.getItemNames();
        for (int i = 0; i < excelReader.getLastRow("Price (high to low)"); i++) {
            String itemName = excelReader.getStringData("Price (high to low)", i+1, 0);
            Assert.assertEquals(itemName, itemNames.get(i));
        }
    }
}
