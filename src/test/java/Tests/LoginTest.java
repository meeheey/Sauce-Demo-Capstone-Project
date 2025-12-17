package Tests;

import Base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test
    public void userCanLogIn() {
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickOnSubmitButton();
        homePage.clickOnMenuButton();
        Assert.assertTrue(menu.logoutLink.isDisplayed());
    }

    @Test
    public void userCanLogInWithInvalidUsername() {
        loginPage.inputUsername("locked_out_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickOnSubmitButton();
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }

    @Test
    public void userCanLogInWithInvalidPassword() {
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("Password123");
        loginPage.clickOnSubmitButton();
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }

    @Test
    public void userCanLogInWithNoCredentials() {
        loginPage.inputUsername("");
        loginPage.inputPassword("");
        loginPage.clickOnSubmitButton();
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        try {
            homePage.clickOnMenuButton();
            if (menu.logoutLink.isDisplayed()) {
                menu.clickOnLogoutLink();
                wait.until(ExpectedConditions.visibilityOf(loginPage.usernameField));
            }
        } catch (Exception e) {
        }

        driver.get("about:blank");
    }
}
