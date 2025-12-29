package Tests;

import Base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class FooterLinksTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickOnLoginButton();
    }

    @Test(priority = 10) // Test priorities correspond to test case IDs
    public void userCanGoToX() {
        footer.clickOnXLink();
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
        Assert.assertTrue(isUrlInTabs(tabList, "https://x.com/saucelabs"));
        }

    @Test(priority = 11) // Test priorities correspond to test case IDs
    public void userCanGoToFacebook() {
        footer.clickOnFacebookLink();
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
        Assert.assertTrue(isUrlInTabs(tabList, "https://www.facebook.com/saucelabs"));
    }

    @Test(priority = 12) // Test priorities correspond to test case IDs
    public void userCanGoToLinkedIn() {
        footer.clickOnLinkedInLink();
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
        Assert.assertTrue(isUrlInTabs(tabList, "https://www.linkedin.com/company/sauce-labs/"));
    }

    public boolean isUrlInTabs(ArrayList<String> tabList, String url) { // Util function for checking if url is in any of the open tabs
        for (String tab : tabList) { // Loop through tavs
            driver.switchTo().window(tab);
            wait.until((WebDriver d) -> { // Wait until DOM is loaded
                try {
                    return ((JavascriptExecutor) d)
                            .executeScript("return document.readyState")
                            .equals("complete");
                } catch (Exception e) {
                    return false;
                }
            });
           if (driver.getCurrentUrl().equals(url)) { // If url is found, return true
               return true;
           }
           driver.close();
        }
        return false; // If url is not in any of the tabs, return false
    }
}
