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
        loginPage.clickOnSubmitButton();
    }

    @Test
    public void userCanGoToX() {
        footer.clickOnXLink();
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
        Assert.assertTrue(isUrlInTabs(tabList, "https://x.com/saucelabs"));
        }

    @Test
    public void userCanGoToFacebook() {
        footer.clickOnFacebookLink();
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
        Assert.assertTrue(isUrlInTabs(tabList, "https://www.facebook.com/saucelabs"));
    }

    @Test
    public void userCanGoToLinkedIn() {
        footer.clickOnLinkedInLink();
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
        Assert.assertTrue(isUrlInTabs(tabList, "https://www.linkedin.com/company/sauce-labs/"));
    }

    public boolean isUrlInTabs(ArrayList<String> tabList, String url) {
        for (String tab : tabList) {
            driver.switchTo().window(tab);
            wait.until((WebDriver d) -> {
                try {
                    return ((JavascriptExecutor) d)
                            .executeScript("return document.readyState")
                            .equals("complete");
                } catch (Exception e) {
                    return false;
                }
            });
           if (driver.getCurrentUrl().equals(url)) {
               return true;
           }
           driver.close();
        }
        return false;
    }
}
