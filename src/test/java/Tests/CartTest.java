package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CartTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickOnSubmitButton();
        wait.until(ExpectedConditions.urlContains("inventory"));
        for (WebElement button : homePage.inventoryItemButtons){
            if(button.getText().equals("Remove")) {
                button.click();
            }
        }
    }

    @Test
    public void removeButtonAppearsOnHomepage() {
        for (WebElement button : homePage.inventoryItemButtons){
            button.click();
        }
        for (WebElement button : homePage.inventoryItemButtons){
            Assert.assertEquals(button.getText(), "Remove");
        }
    }

    @Test
    public void removeButtonAppearsOnInventoryItemPage() {
        homePage.inventoryItemLinks.getFirst().click();
        inventoryItemPage.clickOnInventoryItemButton();
        Assert.assertEquals(inventoryItemPage.inventoryItemButton.getText(), "Remove");
    }

    @Test
    public void addOneItemToCartFromHomepage() {
        homePage.inventoryItemButtons.getFirst().click();
        List<String> itemNames = homePage.getItemNames();
        homePage.clickOnShoppingCartLink();
        ArrayList cartItemNames = cartPage.getCartItemNamesList();
        Assert.assertEquals(itemNames.getFirst(), cartItemNames.getFirst());
    }

    @Test
    public void addAllItemsToCartFromHomepage() {
        List<String> itemNames = homePage.getItemNames();
        for (WebElement button : homePage.inventoryItemButtons){
            button.click();
        }
        homePage.clickOnShoppingCartLink();
        ArrayList cartItemNames = cartPage.getCartItemNamesList();
        for (int i = 0; i < cartItemNames.size(); i++) {
            Assert.assertEquals(itemNames.get(i), cartItemNames.get(i));
        }
    }

    @Test
    public void addOneItemToCartFromInventoryItemPage() {
        List<String> itemNames = homePage.getItemNames();
        homePage.inventoryItemLinks.getFirst().click();
        inventoryItemPage.clickOnInventoryItemButton();
        homePage.clickOnShoppingCartLink();
        ArrayList cartItemNames = cartPage.getCartItemNamesList();
        Assert.assertEquals(itemNames.getFirst(), cartItemNames.getFirst());
    }

    @Test
    public void addAllItemsToCartFromInventoryItemPage() {
        List<String> itemNames = homePage.getItemNames();
        int totalItems = itemNames.size();
        for (int i = 0; i < totalItems; i++) {
            WebElement link = driver.findElements(By.className("inventory_item_name")).get(i);
            link.click();
            inventoryItemPage.clickOnInventoryItemButton();
            driver.navigate().back();
            wait.until(ExpectedConditions.urlContains("inventory"));
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.className("inventory_item_name")
            ));
        }
        homePage.clickOnShoppingCartLink();
        List<String> cartItemNames = cartPage.getCartItemNamesList();

        Assert.assertEquals(cartItemNames, itemNames);
    }

    @Test
    public void removeItemFromCartFromHomepage() {
        homePage.inventoryItemButtons.getFirst().click();
        List<String> itemNames = homePage.getItemNames();
        homePage.clickOnShoppingCartLink();
        ArrayList cartItemNames = cartPage.getCartItemNamesList();
        Assert.assertEquals(itemNames.getFirst(), cartItemNames.getFirst());
        driver.navigate().back();
        homePage.inventoryItemButtons.getFirst().click();
        homePage.clickOnShoppingCartLink();
        Assert.assertTrue(cartPage.cartItemNamesData.isEmpty());
    }

    @Test
    public void removeItemFromCartFromInventoryItemPage() {
        List<String> itemNames = homePage.getItemNames();
        homePage.inventoryItemLinks.getFirst().click();
        inventoryItemPage.clickOnInventoryItemButton();
        homePage.clickOnShoppingCartLink();
        ArrayList cartItemNames = cartPage.getCartItemNamesList();
        Assert.assertEquals(itemNames.getFirst(), cartItemNames.getFirst());
        driver.navigate().back();
        inventoryItemPage.clickOnInventoryItemButton();
        homePage.clickOnShoppingCartLink();
        Assert.assertTrue(cartPage.cartItemNamesData.isEmpty());
    }

    @AfterMethod
    public void cleanUp(){
        driver.manage().deleteAllCookies();
    }
}
