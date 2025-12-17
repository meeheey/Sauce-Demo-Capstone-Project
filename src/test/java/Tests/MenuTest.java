package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MenuTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickOnSubmitButton();
        wait.until(ExpectedConditions.urlContains("inventory"));
        homePage.clickOnMenuButton();
    }

    @Test
    public void allItemsTest() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("inventory_sidebar_link"))));
        menu.clickOnAllItemsLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void aboutLinkTest() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("about_sidebar_link"))));
        menu.clickOnAboutLink();
        wait.until(ExpectedConditions.urlToBe("https://saucelabs.com/"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
    }

    @Test
    public void logoutLinkTest() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("logout_sidebar_link"))));
        menu.clickOnLogoutLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test
    public void resetAppStateLinkTest() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("reset_sidebar_link"))));
        menu.clickOnResetAppStateLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        // TODO
    }
}
