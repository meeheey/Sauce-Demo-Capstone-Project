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

    @Test(priority = 1) // Test priorities correspond to test case IDs
    public void userCanLogIn() {
        // Get data from Excel file
        String validUsername = excelReader.getStringData("LoginData", 1, 0);
        String validPassword = excelReader.getStringData("LoginData", 1, 1);
        // Enter username and password
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        // Click on Login button
        loginPage.clickOnLoginButton();
        homePage.clickOnMenuButton();
        Assert.assertTrue(menu.getLogoutLink().isDisplayed());
    }

    @Test(priority = 2) // Test priorities correspond to test case IDs
    public void userCanLogInWithInvalidUsername() {
        // Get data from Excel file
        String validPassword = excelReader.getStringData("LoginData", 1, 1);
        String invalidUsername = excelReader.getStringData("LoginData", 1, 2);
        // Enter username and password
        loginPage.inputUsername(invalidUsername);
        loginPage.inputPassword(validPassword);
        // Click on Login button
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
    }

    @Test(priority = 3) // Test priorities correspond to test case IDs
    public void userCanLogInWithInvalidPassword() {
        // Get data from Excel file
        String validUsername = excelReader.getStringData("LoginData", 1, 0);
        String invalidPassword = excelReader.getStringData("LoginData", 1, 3);
        // Enter username and password
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(invalidPassword);
        // Click on Login button
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
    }

    @Test(priority = 4) // Test priorities correspond to test case IDs
    public void userCanLogInWithNoCredentials() {
        // Enter empty strings as username and password
        loginPage.inputUsername("");
        loginPage.inputPassword("");
        // Click on Login button
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        try {
            // Logout
            homePage.clickOnMenuButton();
            if (menu.getLogoutLink().isDisplayed()) {
                menu.clickOnLogoutLink();
                wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameField()));
            }
        } catch (Exception e) {
        }

        driver.get("about:blank");
    }
}
