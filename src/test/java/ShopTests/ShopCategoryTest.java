package ShopTests;

import BaseTest.AuthenticatedBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.ShopPage;

public class ShopCategoryTest extends AuthenticatedBaseTest {

    @Test
    public void mensWearPageLoadsFromShop() {
        ShopPage shopPage = new ShopPage(driver).open();

        CategoryPage categoryPage = shopPage.clickMensWear();

        Assert.assertTrue(
                categoryPage.isUrlDisplayed("mens-wear.php"),
                "Clicking Men's Wear should open mens-wear.php."
        );
        Assert.assertEquals(
                categoryPage.getBreadcrumbText(),
                "Mens Wear",
                "The Men's Wear page should be loaded."
        );
    }

    @Test
    public void womensWearPageLoadsFromShop() {
        ShopPage shopPage = new ShopPage(driver).open();

        CategoryPage categoryPage = shopPage.clickWomensWear();

        Assert.assertTrue(
                categoryPage.isUrlDisplayed("womens-wear.php"),
                "Clicking Women's Wear should open womens-wear.php."
        );
        Assert.assertEquals(
                categoryPage.getBreadcrumbText(),
                "Womens Wear",
                "The Women's Wear page should be loaded."
        );
    }

    @Test
    public void kidsWearPageLoadsFromShop() {
        ShopPage shopPage = new ShopPage(driver).open();

        CategoryPage categoryPage = shopPage.clickKidsWear();

        Assert.assertTrue(
                categoryPage.isUrlDisplayed("kids-wear.php"),
                "Clicking Kids' Wear should open kids-wear.php."
        );
        Assert.assertEquals(
                categoryPage.getBreadcrumbText(),
                "Kids Wear",
                "The Kids' Wear page should be loaded."
        );
    }

    @Test
    public void electronicsPageLoadsFromShop() {
        ShopPage shopPage = new ShopPage(driver).open();

        CategoryPage categoryPage = shopPage.clickElectronics();

        Assert.assertTrue(
                categoryPage.isUrlDisplayed("electronics.php"),
                "Clicking Electronics should open electronics.php."
        );
        Assert.assertEquals(
                categoryPage.getBreadcrumbText(),
                "Electronics",
                "The Electronics page should be loaded."
        );
    }

    @Test
    public void goToBackFromMensWearNavigatesToShop() {
        CategoryPage categoryPage = new CategoryPage(driver).openMensWear();

        ShopPage shopPage = categoryPage.clickGoBack();

        Assert.assertTrue(
                shopPage.isShopPageDisplayed(),
                "Clicking Go To Back on mens-wear.php should navigate to shop.php."
        );
    }
}
