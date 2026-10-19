package ProductTests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.AuthenticatedBaseTest;
import pages.MensWearPage;

public class MensWearTest extends AuthenticatedBaseTest {

    @Test
    public void filterByPriceRange() {
        MensWearPage mensWearPage = new MensWearPage(driver);

        mensWearPage.open();
        mensWearPage.selectPriceRange101To200();

        List<WebElement> productPrices = mensWearPage.getDisplayedProductPrices();

        Assert.assertFalse(
                productPrices.isEmpty(),
                "The selected price range should display products."
        );

        for (WebElement productPrice : productPrices) {
            int price = Integer.parseInt(
                    productPrice.getText().replaceAll("[^0-9]", "")
            );

            Assert.assertTrue(
                    price >= 101 && price <= 200,
                    "Displayed product price should be between $101 and $200, but was $" + price
            );
        }
    }

    @Test
    public void filterBySize() {
        MensWearPage mensWearPage = new MensWearPage(driver);

        mensWearPage.open();
        mensWearPage.selectMediumSize();

        List<WebElement> productMetadata = mensWearPage.getDisplayedProductMetadata();

        Assert.assertFalse(
                productMetadata.isEmpty(),
                "The medium-size filter should display products."
        );

        for (WebElement productMeta : productMetadata) {
            Assert.assertTrue(
                    productMeta.getText().contains("Size: M"),
                    "Displayed product should have medium size, but was: "
                            + productMeta.getText()
            );
        }
    }

    @Test
    public void filterByColor() {
        MensWearPage mensWearPage = new MensWearPage(driver);

        mensWearPage.open();
        mensWearPage.selectBlueColor();

        List<WebElement> productMetadata = mensWearPage.getDisplayedProductMetadata();

        Assert.assertFalse(
                productMetadata.isEmpty(),
                "The blue-color filter should display products."
        );

        for (WebElement productMeta : productMetadata) {
            Assert.assertTrue(
                    productMeta.getText().contains("Color: Blue"),
                    "Displayed product should have blue color, but was: "
                            + productMeta.getText()
            );
        }
    }

    @Test
    public void sortLowToHigh() {
        MensWearPage mensWearPage = new MensWearPage(driver);

        mensWearPage.open();
        mensWearPage.sortLowToHigh();

        List<WebElement> productPrices = mensWearPage.getDisplayedProductPrices();
        assertPricesSorted(productPrices, true);
    }

    @Test
    public void sortHighToLow() {
        MensWearPage mensWearPage = new MensWearPage(driver);

        mensWearPage.open();
        mensWearPage.sortHighToLow();

        List<WebElement> productPrices = mensWearPage.getDisplayedProductPrices();
        assertPricesSorted(productPrices, false);
    }

    @Test
    public void sortAtoZ() {
        MensWearPage mensWearPage = new MensWearPage(driver);

        mensWearPage.open();
        mensWearPage.sortAtoZ();

        List<String> productNames = mensWearPage.getDisplayedProductNames();
        assertNamesSorted(productNames, true);
    }

    @Test
    public void sortZtoA(){
        MensWearPage mensWearPage = new MensWearPage(driver);

        mensWearPage.open();
        mensWearPage.sortZtoA();

        List<String> productNames = mensWearPage.getDisplayedProductNames();
        assertNamesSorted(productNames, false);
    }

    private void assertNamesSorted(List<String> productNames, boolean AtoZ ){
        Assert.assertFalse(
                productNames.isEmpty(),
                "Sorted Products are not displayed"
        );

        List<String> sortedList;
        if(AtoZ){
            sortedList = productNames
                    .stream()
                    .sorted()
                    .collect(Collectors.toList());
        }
        else {
            sortedList = productNames
                    .stream()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
        }

        Assert.assertEquals(sortedList, productNames, "Sorting has failed in case " + (AtoZ ? "A to Z" : "Z to A"));
    }

    private void assertPricesSorted(List<WebElement> productPrices, boolean ascending) {
        Assert.assertFalse(
                productPrices.isEmpty(),
                "Sorting should display products."
        );

        int previousPrice = parsePrice(productPrices.get(0));
        for (int index = 1; index < productPrices.size(); index++) {
            int currentPrice = parsePrice(productPrices.get(index));
            if (ascending) {
                Assert.assertTrue(
                        currentPrice >= previousPrice,
                        "Product prices should be sorted from low to high."
                );
            } else {
                Assert.assertTrue(
                        currentPrice <= previousPrice,
                        "Product prices should be sorted from high to low."
                );
            }
            previousPrice = currentPrice;
        }
    }

    private int parsePrice(WebElement productPrice) {
        return Integer.parseInt(productPrice.getText().replaceAll("[^0-9]", ""));
    }
}
