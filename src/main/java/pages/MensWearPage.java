package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MensWearPage extends BasePage {

    private static final By PRICE_RANGE_101_TO_200 =
            By.cssSelector("label[for='price-2']");
    private static final By MEDIUM_SIZE =
            By.cssSelector("label[for='size-2']");
    private static final By BLUE_COLOR =
            By.cssSelector("label[for='color-4']");
    private static final By SORT_SELECT =
            By.id("sort-select");
    private static final By PRODUCT_PRICES =
            By.cssSelector("[data-testid^='product-price-']");
    private static final By PRODUCT_METADATA =
            By.cssSelector("[data-testid^='product-meta-']");
    private static final By PRODUCT_LIST =
            By.cssSelector("[data-testid='product-list']");

    public MensWearPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://shop.qaautomationlabs.com/mens-wear.php");
    }

    public void selectPriceRange101To200() {
        click(PRICE_RANGE_101_TO_200);
    }

    public void selectMediumSize() {
        click(MEDIUM_SIZE);
    }

    public void selectBlueColor() {
        click(BLUE_COLOR);
    }

    public void sortLowToHigh() {
        selectSortOption("price-asc");
    }

    public void sortHighToLow() {
        selectSortOption("price-desc");
    }

    private void selectSortOption(String value) {
        driver.findElement(SORT_SELECT)
                .findElement(By.cssSelector("option[value='" + value + "']"))
                .click();
    }

    public List<WebElement> getDisplayedProductPrices() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_LIST));
        return driver.findElements(PRODUCT_PRICES);
    }

    public List<WebElement> getDisplayedProductMetadata() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_LIST));
        return driver.findElements(PRODUCT_METADATA);
    }
}
