package ProductTests;

import BaseTest.AuthenticatedBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ProductSearchPage;
import pages.ShopPage;

public class ProductSearchTest extends AuthenticatedBaseTest {

    private ShopPage shopPage;
    private ProductSearchPage productSearchPage;

    @BeforeMethod(alwaysRun = true)
    public void setupPages() {
        shopPage = new ShopPage(driver);
        productSearchPage = new ProductSearchPage(driver);

        shopPage.openMensWear();

        Assert.assertTrue(
                shopPage.isProductListDisplayed(),
                "Men's Wear product list was not displayed."
        );
    }

    /*
     * TC029:
     * Verify that searching for a product displays product results.
     */
    @Test(priority = 1)
    public void searchFunctionality() {
        productSearchPage.searchProduct("Black T-Shirt");

        Assert.assertTrue(
                productSearchPage.isProductListDisplayed(),
                "Product list is not displayed after search."
        );

        Assert.assertTrue(
                productSearchPage.hasVisibleProductResults(),
                "No visible products were found for 'Black T-Shirt'."
        );
    }
}