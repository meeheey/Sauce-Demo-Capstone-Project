package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;
    public ExcelReader excelReader;
    public WebDriverWait wait;
    public HomePage homePage;
    public HomePageWithFilter homePageWithFilter;
    public LoginPage loginPage;
    public Menu menu;
    public Footer footer;
    public CartPage cartPage;
    public CartWithItemsPage cartWithItemsPage;
    public InventoryItemPage inventoryItemPage;
    public CheckoutStepOnePage checkoutStepOnePage;
    public CheckoutStepOneWithErrorMessagePage checkoutStepOneWithErrorMessagePage;
    public CheckoutStepTwoPage checkoutStepTwoPage;
    public CheckoutCompletePage checkoutCompletePage;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        excelReader = new ExcelReader("InventoryData.xlsx");
        driver.manage().window().maximize();
        homePage = new HomePage();
        homePageWithFilter = new HomePageWithFilter();
        loginPage = new LoginPage();
        menu = new Menu();
        footer = new Footer();
        cartPage = new CartPage();
        cartWithItemsPage = new CartWithItemsPage();
        inventoryItemPage = new InventoryItemPage();
        checkoutStepOnePage = new CheckoutStepOnePage();
        checkoutStepOneWithErrorMessagePage = new CheckoutStepOneWithErrorMessagePage();
        checkoutStepTwoPage = new CheckoutStepTwoPage();
        checkoutCompletePage = new CheckoutCompletePage();
    }

    @AfterClass
    public void close() {
        driver.close();
    }
}

