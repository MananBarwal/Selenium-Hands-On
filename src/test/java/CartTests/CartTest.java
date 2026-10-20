package CartTests;

import BaseTest.AuthenticatedBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ShoppingCartPage;

public class CartTest extends AuthenticatedBaseTest {

    @Test
    public void addToCart(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.gotoProducts();
        int count = shoppingCartPage.cartIconCount();
        shoppingCartPage.addFirstToCart(false);
        Assert.assertTrue(
                count<shoppingCartPage.cartIconCount(),
                "Items were not added"
        );
    }

    @Test
    public void viewInCart(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        int randCount = ((int) (Math.random()*3)) + 1;

        shoppingCartPage.gotoProducts();
        shoppingCartPage.addToCart(randCount,true);

        int currCount = shoppingCartPage.cartCount();
        System.out.println(randCount);
        System.out.println(currCount);
        Assert.assertTrue(
                currCount == randCount,
                "Not All Items Were Added To Cart"
        );

    }

    @Test
    public void removeFromCart(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

        int randCount = (int) (Math.random()*5)+1;

        shoppingCartPage.gotoProducts();
        shoppingCartPage.addToCart(randCount,true);

        if(shoppingCartPage.cartCount() == randCount){
            shoppingCartPage.removeFromCart();
            Assert.assertTrue(
                    shoppingCartPage.cartCount() == (randCount-1),
                    "Failed to remove items from cart"
            );
        } else {
            Assert.fail("Failed to add items to cart");
        }

    }

    @Test
    public void adjustQuantity(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.gotoProducts();
        shoppingCartPage.addFirstToCart(true);

        int initCount = shoppingCartPage.itemCount();
        if(initCount == -1 ){
            Assert.fail("Failed to add Items in the car");
        }

        int randCount = (int) (Math.random()*5)+1;
        shoppingCartPage.itemInc(randCount);

        int currCount = shoppingCartPage.itemCount();

        Assert.assertTrue(
                currCount == initCount+randCount,
                "Failed to increment number of items in cart"
        );
    }
}
