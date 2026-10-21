package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private static final By FIRST_NAME =
            By.id("firstname");

    private static final By MIDDLE_NAME =
            By.id("middlename");

    private static final By LAST_NAME =
            By.id("lastname");

    private static final By EMAIL =
            By.id("email");

    private static final By PHONE =
            By.id("phone");

    private static final By ADDRESS =
            By.id("address");

    private static final By STATE =
            By.id("states");

    private static final By CITY =
            By.id("city");

    private static final By PIN_CODE =
            By.id("pincode");

    private static final By CONTINUE_BUTTON =
            By.id("continue");

    private static final By PIN_CODE_ERROR =
            By.id("pincodeerror");

    private static final By TOTAL_PRICE =
            By.id("totalPrice");

    private static final By PLACE_ORDER_BUTTON =
            By.cssSelector("[data-testid='place-order-btn']");

    private static final By ORDER_SUCCESS_TITLE =
            By.cssSelector("[data-testid='order-success-title']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutPageDisplayed() {
        return wait.until(
                ExpectedConditions.urlContains("checkout")
        );
    }

    public void enterFirstName(String firstName) {
        sendData(FIRST_NAME, firstName);
    }

    public void enterMiddleName(String middleName) {
        sendData(MIDDLE_NAME, middleName);
    }

    public void enterLastName(String lastName) {
        sendData(LAST_NAME, lastName);
    }

    public void enterEmail(String email) {
        sendData(EMAIL, email);
    }

    public void enterPhone(String phone) {
        sendData(PHONE, phone);
    }

    public void enterAddress(String address) {
        sendData(ADDRESS, address);
    }

    public void enterState(String state) {
        sendData(STATE, state);
    }

    public void enterCity(String city) {
        sendData(CITY, city);
    }

    public void enterPinCode(String pinCode) {
        sendData(PIN_CODE, pinCode);
    }

    public void clearPinCode() {
        find(PIN_CODE).clear();
    }

    public void enterCustomerDetails(
            String firstName,
            String middleName,
            String lastName,
            String email,
            String phone,
            String address,
            String state,
            String city,
            String pinCode
    ) {
        enterFirstName(firstName);
        enterMiddleName(middleName);
        enterLastName(lastName);
        enterEmail(email);
        enterPhone(phone);
        enterAddress(address);
        enterState(state);
        enterCity(city);

        if (pinCode == null || pinCode.isBlank()) {
            clearPinCode();
        } else {
            enterPinCode(pinCode);
        }
    }

    public void clickContinue() {
        click(CONTINUE_BUTTON);
    }

    public String getPinCodeError() {
        return getText(PIN_CODE_ERROR);
    }

    public void waitForPinCodeError() {
        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        PIN_CODE_ERROR,
                        "Please enter your pincode."
                )
        );
    }

    public boolean isConfirmationPageDisplayed() {
        return wait.until(
                ExpectedConditions.urlContains("confirm")
        );
    }

    public String getConfirmationItemName(String productId) {
        By itemName = By.cssSelector(
                "[data-testid='confirm-item-name-" + productId + "']"
        );

        return getText(itemName);
    }

    public String getConfirmationItemTotal(String productId) {
        By itemTotal = By.cssSelector(
                "[data-testid='confirm-item-total-" + productId + "']"
        );

        return getText(itemTotal);
    }

    public String getTotalPrice() {
        return getText(TOTAL_PRICE);
    }

    public void placeOrder() {
        click(PLACE_ORDER_BUTTON);
    }

    public String getOrderSuccessMessage() {
        return getText(ORDER_SUCCESS_TITLE);
    }
}