package CheckoutTests;

import BaseTest.AuthenticatedBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.ShoppingCartPage;
import pages.ShopPage;

public class CheckoutTest extends AuthenticatedBaseTest {

    private ShopPage shopPage;
    private ShoppingCartPage shoppingCartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod(alwaysRun = true)
    public void setupPagesAndCart() {
        shopPage = new ShopPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        shopPage.openMensWear();
        shoppingCartPage.addProductToCart("21");
    }

    private void openCheckoutPage() {
        shoppingCartPage.openCartFromHeader();

        Assert.assertEquals(
                shoppingCartPage.getCartItemName("21"),
                "Black T-Shirt",
                "Expected product was not found in the cart."
        );

        Assert.assertEquals(
                shoppingCartPage.getTotalPrice(),
                "Total: $150",
                "Product price is incorrect."
        );

        shoppingCartPage.proceedToCheckout();

        Assert.assertTrue(
                checkoutPage.isCheckoutPageDisplayed(),
                "Checkout page was not displayed."
        );
    }

    @Test(priority = 1)
    public void proceedToCheckout() {
        openCheckoutPage();

        Assert.assertEquals(
                checkoutPage.getPageTitle(),
                "Checkout — E-commerce Demo | QA Automation Labs",
                "Page title is incorrect."
        );
    }

    @Test(priority = 2)
    public void checkoutMissingPinCode() {
        openCheckoutPage();

        checkoutPage.enterCustomerDetails(
                "Rahul",
                "Kumar",
                "Sharma",
                "rahul.sharma.test@example.com",
                "9876543210",
                "123, MG Road, Indiranagar",
                "Karnataka",
                "Bangalore",
                ""
        );

        checkoutPage.clickContinue();
        checkoutPage.waitForPinCodeError();

        Assert.assertEquals(
                checkoutPage.getPinCodeError().trim(),
                "Please enter your pincode.",
                "Incorrect validation message for missing PIN code."
        );
    }

    @Test(priority = 3)
    public void checkoutValidFlow() {
        openCheckoutPage();

        checkoutPage.enterCustomerDetails(
                "Rahul",
                "Kumar",
                "Sharma",
                "rahul.sharma.test@example.com",
                "9876543210",
                "123, MG Road, Indiranagar",
                "Karnataka",
                "Bangalore",
                "560038"
        );

        checkoutPage.clickContinue();

        Assert.assertTrue(
                checkoutPage.isConfirmationPageDisplayed(),
                "Confirmation page was not displayed."
        );

        Assert.assertEquals(
                checkoutPage.getConfirmationItemName("21"),
                "Black T-Shirt",
                "Incorrect item displayed on checkout overview."
        );

        Assert.assertEquals(
                checkoutPage.getConfirmationItemTotal("21"),
                "$150",
                "Incorrect item price."
        );

        Assert.assertEquals(
                checkoutPage.getTotalPrice(),
                "$150",
                "Incorrect item subtotal."
        );

        checkoutPage.placeOrder();

        Assert.assertEquals(
                checkoutPage.getOrderSuccessMessage(),
                "🎉 Thank You for Your Order!",
                "Checkout was not completed successfully."
        );
    }
}