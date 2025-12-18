package Tests;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickOnSubmitButton();
        wait.until(ExpectedConditions.urlContains("inventory"));
        for (WebElement button : homePage.getInventoryItemButtons()) {
            if (button.getText().equals("Remove")) {
                button.click();
            }
        }
    }

    @Test
    public void checkoutTest() {
        homePage.getInventoryItemButtons().getFirst().click();
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.inputFirstName("Petar");
        checkoutStepOnePage.inputLastName("Petrović");
        checkoutStepOnePage.inputPostalCode("21000");
        checkoutStepOnePage.clickOnContinueButton();
        checkoutStepTwoPage.clickOnFinishButton();
        Assert.assertEquals(checkoutCompletePage.getCompleteHeader().getText(), "Thank you for your order!");
        Assert.assertEquals(checkoutCompletePage.getCompleteText().getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    @Test
    public void checkoutWithEmptyCart() {
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
    }

    @Test
    public void checkoutWithEmptyFirstNameFieldTest() {
        homePage.getInventoryItemButtons().getFirst().click();
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.inputLastName("Petrović");
        checkoutStepOnePage.inputPostalCode("21000");
        checkoutStepOnePage.clickOnContinueButton();
        Assert.assertEquals(checkoutStepOneWithErrorMessagePage.getErrorMessage().getText(), "Error: First Name is required");
    }

    @Test
    public void checkoutWithEmptyLastNameFieldTest() {
        homePage.getInventoryItemButtons().getFirst().click();
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.inputFirstName("Petar");
        checkoutStepOnePage.inputPostalCode("21000");
        checkoutStepOnePage.clickOnContinueButton();
        Assert.assertEquals(checkoutStepOneWithErrorMessagePage.getErrorMessage().getText(), "Error: Last Name is required");
    }

    @Test
    public void checkoutWithEmptyPostalCodeFieldTest() {
        homePage.getInventoryItemButtons().getFirst().click();
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.inputFirstName("Petar");
        checkoutStepOnePage.inputLastName("Petrović");
        checkoutStepOnePage.clickOnContinueButton();
        Assert.assertEquals(checkoutStepOneWithErrorMessagePage.getErrorMessage().getText(), "Error: Postal Code is required");
    }

    @Test
    public void checkoutWithAllFieldsEmptyTest() {
        homePage.getInventoryItemButtons().getFirst().click();
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.clickOnContinueButton();
        Assert.assertEquals(checkoutStepOneWithErrorMessagePage.getErrorMessage().getText(), "Error: First Name is required");
    }

    @Test
    public void validCheckoutSubtotal(){
        List<String[]> itemPrices = homePage.getItemPrices();
        float sumPrices = 0;
        for (String[] itemPrice : itemPrices) {
            sumPrices += Float.parseFloat(itemPrice[1].replaceAll("[^\\d.-]", ""));
        }
        System.out.println(sumPrices);
        for (WebElement button : homePage.getInventoryItemButtons()){
            button.click();
        }
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.inputFirstName("Petar");
        checkoutStepOnePage.inputLastName("Petrović");
        checkoutStepOnePage.inputPostalCode("21000");
        checkoutStepOnePage.clickOnContinueButton();
        Assert.assertEquals(checkoutStepTwoPage.getSubtotal(), sumPrices);
    }

    @Test
    public void validCheckoutItemNames() {
        List<String> homepageItemNames = homePage.getItemNames();
        for (WebElement button : homePage.getInventoryItemButtons()){
            button.click();
        }
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.inputFirstName("Petar");
        checkoutStepOnePage.inputLastName("Petrović");
        checkoutStepOnePage.inputPostalCode("21000");
        checkoutStepOnePage.clickOnContinueButton();
        List<String> checkoutItemNames = checkoutStepTwoPage.getItemNames();
        for (int i = 0; i < checkoutItemNames.size(); i++) {
            Assert.assertEquals(checkoutItemNames.get(i), homepageItemNames.get(i));
        }
    }

    @Test
    public void validCheckoutItemDescriptions() {
        List<String[]> homepageItemDescriptions = homePage.getItemDescriptions();
        for (WebElement button : homePage.getInventoryItemButtons()){
            button.click();
        }
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.inputFirstName("Petar");
        checkoutStepOnePage.inputLastName("Petrović");
        checkoutStepOnePage.inputPostalCode("21000");
        checkoutStepOnePage.clickOnContinueButton();
        List<String[]> checkoutItemDescriptions = checkoutStepTwoPage.getItemDescriptions();
        for (int i = 0; i < checkoutItemDescriptions.size(); i++) {
            Assert.assertEquals(checkoutItemDescriptions.get(i)[1], homepageItemDescriptions.get(i)[1]);
        }
    }

    @Test
    public void validCheckoutItemPrices() {
        List<String[]> homepageItemPrices = homePage.getItemPrices();
        for (WebElement button : homePage.getInventoryItemButtons()){
            button.click();
        }
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.inputFirstName("Petar");
        checkoutStepOnePage.inputLastName("Petrović");
        checkoutStepOnePage.inputPostalCode("21000");
        checkoutStepOnePage.clickOnContinueButton();
        List<String[]> checkoutItemPrices = checkoutStepTwoPage.getItemPrices();
        for (int i = 0; i < checkoutItemPrices.size(); i++) {
            Assert.assertEquals(checkoutItemPrices.get(i)[1], homepageItemPrices.get(i)[1]);
        }
    }

}
