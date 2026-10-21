package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductSearchPage extends BasePage {

    private static final By SEARCH_INPUT =
            By.id("search-input");

    private static final By PRODUCT_LIST =
            By.id("product-list");

    public ProductSearchPage(WebDriver driver) {
        super(driver);
    }

    public void searchProduct(String productName) {
        WebElement searchBox = find(SEARCH_INPUT);

        searchBox.clear();
        searchBox.sendKeys(productName);
        searchBox.sendKeys(Keys.ENTER);
    }

    public boolean isProductListDisplayed() {
        return isDisplayed(PRODUCT_LIST);
    }

    public boolean hasVisibleProductResults() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(PRODUCT_LIST)
        );

        List<WebElement> products = driver.findElements(PRODUCT_LIST);

        return products.stream()
                .anyMatch(WebElement::isDisplayed);
    }
}