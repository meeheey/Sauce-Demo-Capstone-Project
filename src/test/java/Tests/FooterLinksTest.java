package Tests;

import Base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        footer.clickOnFacebookLink();
        footer.clickOnLinkedInLink();
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabList.get(1));
        
    }
}
