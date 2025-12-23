package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Footer extends BaseTest {
    public Footer() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="a[href='https://twitter.com/saucelabs']")
    WebElement xLink;

    @FindBy(css="a[href='https://www.facebook.com/saucelabs']")
    WebElement facebookLink;

    @FindBy(css="a[href='https://www.linkedin.com/company/sauce-labs/']")
    WebElement linkedInLink;

    //---------------------------------

    public void clickOnXLink() {
        xLink.click();
    }

    public void clickOnFacebookLink() {
        facebookLink.click();
    }

    public void clickOnLinkedInLink() {
        linkedInLink.click();
    }
}
