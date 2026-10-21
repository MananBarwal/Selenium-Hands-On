package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ShopPage extends BasePage {

    private static final By MENS_WEAR_LINK =
            By.cssSelector("[data-testid='shop-now-mens-wear']");

    private static final By PRODUCT_LIST =
            By.id("product-list");

    private static final By SEARCH_INPUT =
            By.id("search-input");

    public ShopPage(WebDriver driver) {
        super(driver);
    }

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