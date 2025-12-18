package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Menu extends BaseTest {
    public Menu() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "inventory_sidebar_link")
    WebElement allItemsLink;

    @FindBy(id = "about_sidebar_link")
    WebElement aboutLink;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutLink;

    @FindBy(id = "reset_sidebar_link")
    WebElement resetAppStateLink;

    //------------------------------

    public WebElement getAllItemsLink() {
        return allItemsLink;
    }

    public WebElement getAboutLink() {
        return aboutLink;
    }

    public WebElement getLogoutLink() {
        return logoutLink;
    }

    public WebElement getResetAppStateLink() {
        return resetAppStateLink;
    }


    //---------------------------------

    public void clickOnAllItemsLink() {
        allItemsLink.click();
    }

    public void clickOnAboutLink() {
        aboutLink.click();
    }

    public void clickOnLogoutLink() {
        logoutLink.click();
    }

    public void clickOnResetAppStateLink() {
        resetAppStateLink.click();
    }
}
