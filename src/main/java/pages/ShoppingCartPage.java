package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

public class ShoppingCartPage extends BasePage {
    private static final By BUY_BUTTON =
            By.cssSelector("[data-testid^='add-to-cart-']");
    private static final By PRODUCT_LIST =
            By.cssSelector("[data-testid='product-list']");
    private static final By CART_ITEMS =
            By.cssSelector("[data-testid^='cart-row-']");
    private static final By CART =
            By.tagName("tbody");
    private static final By DELETE_BUTTON =
            By.cssSelector("[data-testid^='cart-remove-']");
    private static final By CART_COUNT=
            By.cssSelector("[data-testid='header-cart-count']");
    private static final By CART_INC =
            By.cssSelector("[data-testid^='cart-qty-']");
    private static final By CART_ICON =
            By.id("cartdesk");

    private static final By CHECKOUT_BUTTON =
            By.id("checkoutBtn");

    private static final By TOTAL_PRICE =
            By.id("totalPrice");

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public void gotoCart(){
        driver.get("https://shop.qaautomationlabs.com/cart.php");
    }

    public void gotoProducts() {driver.get("https://shop.qaautomationlabs.com/mens-wear.php");}

    public void addToCart(int count,boolean checkCart){
        wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_LIST));

        List<WebElement> itemBuyButtons = driver.findElements(BUY_BUTTON);

        int limit = Math.min(itemBuyButtons.size(),count);
        for(int i = 0;i<limit;i++){
            WebElement button = itemBuyButtons.get(i);
            button.click();
        }

        if(checkCart) gotoCart();
    }

    public void addFirstToCart(boolean checkCart){
        addToCart(1,checkCart);
    }

    public void addProductToCart(String productId) {
        By productButton = By.cssSelector(
                "[data-testid='add-to-cart-" + productId + "']"
        );

        click(productButton);
    }

    public void openCartFromHeader() {
        click(CART_ICON);
    }

    public int cartIconCount(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(CART_COUNT));
        return parseInt(driver.findElement(CART_COUNT).getText());
    }

    public int cartCount(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(CART));
        List<WebElement> items = driver.findElements(CART_ITEMS);
        return items.size();
    }

    public void removeFromCart(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(CART));
        List<WebElement> items = driver.findElements(CART_ITEMS);
        WebElement itemRemoved = items.getFirst();
        itemRemoved.findElement(DELETE_BUTTON).click();
    }

    public void itemInc(int count){
        wait.until(ExpectedConditions.visibilityOfElementLocated(CART));
        for(int i = 0;i<count;i++){
            WebElement item = wait.until(
                    ExpectedConditions.elementToBeClickable(CART_INC)
            );
            item.sendKeys(Keys.ARROW_UP);
        }
    }

    public int itemCount(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(CART));
        String item = driver.findElement(CART_INC).getAttribute("value");

        if(item != null){
            return parseInt(item);
        } else {
            return -1;
        }
    }

    public String getCartItemName(String productId) {
        By itemName = By.cssSelector(
                "[data-testid='cart-item-name-" + productId + "']"
        );

        return getText(itemName);
    }

    public String getTotalPrice() {
        return getText(TOTAL_PRICE);
    }

    public void proceedToCheckout() {
        click(CHECKOUT_BUTTON);
    }

}
