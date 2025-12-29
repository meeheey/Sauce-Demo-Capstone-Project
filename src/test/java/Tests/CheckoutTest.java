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
        // Go to Sauce Demo and log in
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickOnLoginButton();
        // Wait until url is changed
        wait.until(ExpectedConditions.urlContains("inventory"));
        // If there are any buttons in cart, remove them
        for (WebElement button : homePage.getInventoryItemButtons()) {
            if (button.getText().equals("Remove")) {
                button.click();
            }
        }
    }

    @Test(priority = 30) // Test priorities correspond to test case IDs
    public void checkoutTest() {
        homePage.getInventoryItemButtons().getFirst().click();
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        // Get checkout data
        String firstName = excelReader.getStringData("CheckoutData", 1, 0);
        String lastName = excelReader.getStringData("CheckoutData", 1, 1);
        String postalCode = excelReader.getStringData("CheckoutData", 1, 2);
        // Input checkout data
        checkoutStepOnePage.inputFirstName(firstName);
        checkoutStepOnePage.inputLastName(lastName);
        checkoutStepOnePage.inputPostalCode(postalCode);
        // Click on button
        checkoutStepOnePage.clickOnContinueButton();
        // Click on "Finish" button
        checkoutStepTwoPage.clickOnFinishButton();
        Assert.assertEquals(checkoutCompletePage.getCompleteHeader().getText(), "Thank you for your order!");
        // Check is correct text is displayed
        Assert.assertEquals(checkoutCompletePage.getCompleteText().getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    @Test(priority = 31) // Test priorities correspond to test case IDs
    public void checkoutWithEmptyCart() { // This test fails. Look at Bug report at project root.
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
    }

    @Test(priority = 32) // Test priorities correspond to test case IDs
    public void checkoutWithEmptyFirstNameFieldTest() {
        homePage.getInventoryItemButtons().getFirst().click();
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        // Get checkout data
        String lastName = excelReader.getStringData("CheckoutData", 1, 1);
        String postalCode = excelReader.getStringData("CheckoutData", 1, 2);
        // Input checkout data
        checkoutStepOnePage.inputFirstName(""); // Enter empty string
        checkoutStepOnePage.inputLastName(lastName);
        checkoutStepOnePage.inputPostalCode(postalCode);
        // Click on button
        checkoutStepOnePage.clickOnContinueButton();
        // Check for error message
        Assert.assertEquals(checkoutStepOneWithErrorMessagePage.getErrorMessage().getText(), "Error: First Name is required");
    }

    @Test(priority = 33) // Test priorities correspond to test case IDs
    public void checkoutWithEmptyLastNameFieldTest() {
        homePage.getInventoryItemButtons().getFirst().click();
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        // Get checkout data
        String firstName = excelReader.getStringData("CheckoutData", 1, 0);
        String postalCode = excelReader.getStringData("CheckoutData", 1, 2);
        // Enter checkout data
        checkoutStepOnePage.inputFirstName(firstName);
        checkoutStepOnePage.inputLastName(""); // Enter empty string
        checkoutStepOnePage.inputPostalCode(postalCode);
        // Click on button
        checkoutStepOnePage.clickOnContinueButton();
        // Check for error message
        Assert.assertEquals(checkoutStepOneWithErrorMessagePage.getErrorMessage().getText(), "Error: Last Name is required");
    }

    @Test(priority = 34) // Test priorities correspond to test case IDs
    public void checkoutWithEmptyPostalCodeFieldTest() {
        homePage.getInventoryItemButtons().getFirst().click();
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        // Get checkout data
        String firstName = excelReader.getStringData("CheckoutData", 1, 0);
        String lastName = excelReader.getStringData("CheckoutData", 1, 1);
        // Enter checkout data
        checkoutStepOnePage.inputFirstName(firstName);
        checkoutStepOnePage.inputLastName(lastName);
        checkoutStepOnePage.inputPostalCode(""); // Enter empty string
        // Click on button
        checkoutStepOnePage.clickOnContinueButton();
        // Check for error message
        Assert.assertEquals(checkoutStepOneWithErrorMessagePage.getErrorMessage().getText(), "Error: Postal Code is required");
    }

    @Test(priority = 35) // Test priorities correspond to test case IDs
    public void checkoutWithAllFieldsEmptyTest() {
        homePage.getInventoryItemButtons().getFirst().click();
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.clickOnContinueButton();
        // Check for error message
        Assert.assertEquals(checkoutStepOneWithErrorMessagePage.getErrorMessage().getText(), "Error: First Name is required");
    }

    @Test(priority = 36) // Test priorities correspond to test case IDs
    public void validCheckoutSubtotal(){
        List<String[]> itemPrices = homePage.getItemPrices(); // Get all prices from homepage
        float sumPrices = 0; // Declare and initialize a variable for storing sum of prices
        for (String[] itemPrice : itemPrices) { // Loop through item prices
            sumPrices += Float.parseFloat(itemPrice[1].replaceAll("[^\\d.-]", "")); // converting them into floats and adding them to sumPrices
        }
        for (WebElement button : homePage.getInventoryItemButtons()) { // Add all items to cart
            if (button.getText().equals("Add to cart")) {
                button.click();
            }
        }
        homePage.clickOnShoppingCartLink(); // Go to cart
        cartPage.clickOnCheckoutButton(); // Click on "Checkout" button
        // Get checkout data
        String firstName = excelReader.getStringData("CheckoutData", 1, 0);
        String lastName = excelReader.getStringData("CheckoutData", 1, 1);
        String postalCode = excelReader.getStringData("CheckoutData", 1, 2);
        // Input checkout data
        checkoutStepOnePage.inputFirstName(firstName);
        checkoutStepOnePage.inputLastName(lastName);
        checkoutStepOnePage.inputPostalCode(postalCode);
        // Click on button
        checkoutStepOnePage.clickOnContinueButton();
        Assert.assertEquals(checkoutStepTwoPage.getSubtotal(), sumPrices); // Compare results of our calculation and displayed price
    }

    @Test(priority = 37) // Test priorities correspond to test case IDs
    public void validCheckoutItemNames() {
        List<String> homepageItemNames = homePage.getItemNames();
        for (WebElement button : homePage.getInventoryItemButtons()){
            button.click();
        }
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        // Get checkout data
        String firstName = excelReader.getStringData("CheckoutData", 1, 0);
        String lastName = excelReader.getStringData("CheckoutData", 1, 1);
        String postalCode = excelReader.getStringData("CheckoutData", 1, 2);
        // Input checkout data
        checkoutStepOnePage.inputFirstName(firstName);
        checkoutStepOnePage.inputLastName(lastName);
        checkoutStepOnePage.inputPostalCode(postalCode);
        // Click on button
        checkoutStepOnePage.clickOnContinueButton();
        List<String> checkoutItemNames = checkoutStepTwoPage.getItemNames();
        for (int i = 0; i < checkoutItemNames.size(); i++) { // Loop through names, checking them
            Assert.assertEquals(checkoutItemNames.get(i), homepageItemNames.get(i));
        }
    }

    @Test(priority = 38) // Test priorities correspond to test case IDs
    public void validCheckoutItemDescriptions() {
        List<String[]> homepageItemDescriptions = homePage.getItemDescriptions();
        for (WebElement button : homePage.getInventoryItemButtons()){
            button.click();
        }
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        // Get checkout data
        String firstName = excelReader.getStringData("CheckoutData", 1, 0);
        String lastName = excelReader.getStringData("CheckoutData", 1, 1);
        String postalCode = excelReader.getStringData("CheckoutData", 1, 2);
        // Input checkout data
        checkoutStepOnePage.inputFirstName(firstName);
        checkoutStepOnePage.inputLastName(lastName);
        checkoutStepOnePage.inputPostalCode(postalCode);
        // Click on button
        checkoutStepOnePage.clickOnContinueButton();
        List<String[]> checkoutItemDescriptions = checkoutStepTwoPage.getItemDescriptions();
        for (int i = 0; i < checkoutItemDescriptions.size(); i++) { // Loop through descriptions, checking them
            Assert.assertEquals(checkoutItemDescriptions.get(i)[1], homepageItemDescriptions.get(i)[1]);
        }
    }

    @Test(priority = 39) // Test priorities correspond to test case IDs
    public void validCheckoutItemPrices() {
        List<String[]> homepageItemPrices = homePage.getItemPrices();
        for (WebElement button : homePage.getInventoryItemButtons()) { // Add all items to cart
            if (button.getText().equals("Add to cart")) {
                button.click();
            }
        }
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        // Get checkout data
        String firstName = excelReader.getStringData("CheckoutData", 1, 0);
        String lastName = excelReader.getStringData("CheckoutData", 1, 1);
        String postalCode = excelReader.getStringData("CheckoutData", 1, 2);
        // Input checkout data
        checkoutStepOnePage.inputFirstName(firstName);
        checkoutStepOnePage.inputLastName(lastName);
        checkoutStepOnePage.inputPostalCode(postalCode);
        // Click on button
        checkoutStepOnePage.clickOnContinueButton();
        List<String[]> checkoutItemPrices = checkoutStepTwoPage.getItemPrices();
        for (int i = 0; i < checkoutItemPrices.size(); i++) { // Loop through prices, checking them
            Assert.assertEquals(checkoutItemPrices.get(i)[1], homepageItemPrices.get(i)[1]);
        }
    }

}
