package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogOutTest extends BaseTest {

    @Test
    public void logOutTest() {
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickOnSubmitButton();
        wait.until(ExpectedConditions.urlContains("inventory"));
        homePage.clickOnMenuButton();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("logout_sidebar_link"))));
        menu.clickOnLogoutLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }

}
