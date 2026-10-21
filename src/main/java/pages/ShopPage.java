package pages;

import org.openqa.selenium.By;
<<<<<<< HEAD
import org.openqa.selenium.Dimension;
=======
>>>>>>> 5d8c9330f1266979460ff384d1c4fdd3d6af31b0
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ShopPage extends BasePage {

<<<<<<< HEAD
    private static final By MENS_WEAR_LINK =
            By.cssSelector("[data-testid='shop-now-mens-wear']");

    private static final By PRODUCT_LIST =
            By.id("product-list");

    private static final By SEARCH_INPUT =
            By.id("search-input");
=======
    private static final String SHOP_URL = "https://shop.qaautomationlabs.com/shop.php";

    private final By mensWearButton = By.cssSelector("[data-testid='shop-now-mens-wear']");
    private final By womensWearButton = By.cssSelector("[data-testid='shop-now-womens-wear']");
    private final By kidsWearButton = By.cssSelector("[data-testid='shop-now-kids-wear']");
    private final By electronicsButton = By.cssSelector("[data-testid='shop-now-electronics']");
>>>>>>> 5d8c9330f1266979460ff384d1c4fdd3d6af31b0

    public ShopPage(WebDriver driver) {
        super(driver);
    }

<<<<<<< HEAD
    public void openMensWear() {
        click(MENS_WEAR_LINK);

        wait.until(
                ExpectedConditions.urlContains("mens-wear.php")
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(PRODUCT_LIST)
        );
    }

    public boolean isProductListDisplayed() {
        return isDisplayed(PRODUCT_LIST);
    }

    public boolean isSearchInputDisplayed() {
        return isDisplayed(SEARCH_INPUT);
    }

    public void setBrowserSize(Dimension dimension) {
        driver.manage().window().setSize(dimension);
    }

    public void maximizeBrowser() {
        driver.manage().window().maximize();
    }

    public boolean hasNoHorizontalOverflow() {
        Number documentWidth = (Number) executeJavaScript(
                "return Math.max(" +
                        "document.body.scrollWidth, " +
                        "document.documentElement.scrollWidth" +
                        ");"
        );

        Number viewportWidth = (Number) executeJavaScript(
                "return window.innerWidth;"
        );

        return documentWidth.longValue()
                <= viewportWidth.longValue() + 2;
    }
}
=======
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
>>>>>>> 5d8c9330f1266979460ff384d1c4fdd3d6af31b0
