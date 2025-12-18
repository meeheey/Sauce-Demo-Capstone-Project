package Tests;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickOnSubmitButton();
        wait.until(ExpectedConditions.urlContains("inventory"));
        for (WebElement button : homePage.inventoryItemButtons) {
            if (button.getText().equals("Remove")) {
                button.click();
            }
        }
    }

    @Test
    public void checkoutTest() {
        homePage.inventoryItemButtons.getFirst().click();
        homePage.clickOnShoppingCartLink();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.inputFirstName("Petar");
        checkoutStepOnePage.inputLastName("Petrović");
        checkoutStepOnePage.inputPostalCode("21000");
        checkoutStepOnePage.clickOnContinueButton();
        checkoutStepTwoPage.clickOnFinishButton();
        Assert.assertEquals(checkoutCompletePage.completeHeader.getText(), "Thank you for your order!");
        Assert.assertEquals(checkoutCompletePage.completeText.getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }
}
