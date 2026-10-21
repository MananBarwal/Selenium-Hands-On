package UIandResponsiveTests;

import BaseTest.AuthenticatedBaseTest;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ShopPage;

import java.util.LinkedHashMap;
import java.util.Map;

public class UIandResponsiveTest extends AuthenticatedBaseTest {

    private ShopPage shopPage;

    @BeforeMethod(alwaysRun = true)
    public void setupPages() {
        shopPage = new ShopPage(driver);
    }

    /*
     * TC030:
     * Verify the shop category layout on desktop, tablet, and mobile.
     */
    @Test(priority = 1)
    public void responsiveLayoutVerification() {
        shopPage.openMensWear();

        Assert.assertTrue(
                shopPage.isProductListDisplayed(),
                "Product list is not displayed on the Men's Wear page."
        );

        Map<String, Dimension> viewports = new LinkedHashMap<>();

        viewports.put(
                "Desktop",
                new Dimension(1920, 1080)
        );

        viewports.put(
                "Tablet",
                new Dimension(768, 1024)
        );

        viewports.put(
                "Mobile",
                new Dimension(375, 667)
        );

        for (Map.Entry<String, Dimension> viewport : viewports.entrySet()) {
            String deviceName = viewport.getKey();
            Dimension browserSize = viewport.getValue();

            shopPage.setBrowserSize(browserSize);

            Assert.assertTrue(
                    shopPage.isProductListDisplayed(),
                    deviceName + ": Product list is not visible."
            );

            Assert.assertTrue(
                    shopPage.isSearchInputDisplayed(),
                    deviceName + ": Search field is not visible."
            );

            Assert.assertTrue(
                    shopPage.hasNoHorizontalOverflow(),
                    deviceName + ": Horizontal overflow detected."
            );
        }

        shopPage.maximizeBrowser();
    }
}