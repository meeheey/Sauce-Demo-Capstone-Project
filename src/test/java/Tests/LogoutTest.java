package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    @Test(priority = 5) // Test priorities correspond to test case IDs
    public void logOutTest() {
        // Go to Sauce Demo and log in
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickOnLoginButton();
        // Wait until url is changed
        wait.until(ExpectedConditions.urlContains("inventory"));
        // Go to menu and wait for logout link
        homePage.clickOnMenuButton();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("logout_sidebar_link"))));
        // Click on it
        menu.clickOnLogoutLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }

}
