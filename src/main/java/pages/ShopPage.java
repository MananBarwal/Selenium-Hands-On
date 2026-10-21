package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ShopPage extends BasePage {

    private static final String SHOP_URL = "https://shop.qaautomationlabs.com/shop.php";

    private final By mensWearButton = By.cssSelector("[data-testid='shop-now-mens-wear']");
    private final By womensWearButton = By.cssSelector("[data-testid='shop-now-womens-wear']");
    private final By kidsWearButton = By.cssSelector("[data-testid='shop-now-kids-wear']");
    private final By electronicsButton = By.cssSelector("[data-testid='shop-now-electronics']");

    public ShopPage(WebDriver driver) {
        super(driver);
    }

    public ShopPage open() {
        driver.get(SHOP_URL);
        return this;
    }

    public CategoryPage clickMensWear() {
        click(mensWearButton);
        return new CategoryPage(driver);
    }

    public CategoryPage clickWomensWear() {
        click(womensWearButton);
        return new CategoryPage(driver);
    }

    public CategoryPage clickKidsWear() {
        click(kidsWearButton);
        return new CategoryPage(driver);
    }

    public CategoryPage clickElectronics() {
        click(electronicsButton);
        return new CategoryPage(driver);
    }

    public boolean isShopPageDisplayed() {
        return wait.until(ExpectedConditions.urlContains("shop.php"));
    }
}
