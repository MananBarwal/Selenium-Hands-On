package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Shared page object for the category pages (mens-wear.php, womens-wear.php,
 * kids-wear.php and electronics.php), which all use the same layout.
 */
public class CategoryPage extends BasePage {

    private static final String MENS_WEAR_URL = "https://shop.qaautomationlabs.com/mens-wear.php";

    private final By breadcrumbCurrent = By.cssSelector("[data-testid='breadcrumb-current']");
    private final By goBackButton = By.cssSelector("[data-testid='go-back-btn']");

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public CategoryPage openMensWear() {
        driver.get(MENS_WEAR_URL);
        return this;
    }

    public boolean isUrlDisplayed(String urlPart) {
        return wait.until(ExpectedConditions.urlContains(urlPart));
    }

    public String getBreadcrumbText() {
        return getText(breadcrumbCurrent);
    }

    public ShopPage_Shantu clickGoBack() {

        click(goBackButton);
        return new ShopPage_Shantu(driver);
    }

}
