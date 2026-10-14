package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends BasePage {

    private static final String REGISTER_URL = "https://shop.qaautomationlabs.com/register.php";

    private final By firstNameField = By.id("regfirstname");
    private final By lastNameField = By.id("reglastname");
    private final By emailField = By.id("regemail");
    private final By phoneField = By.id("regphone");
    private final By dobField = By.id("regdob");
    private final By countrySelect = By.id("regcountry");
    private final By passwordField = By.id("regpassword");
    private final By confirmPasswordField = By.id("regconfirmpassword");
    private final By registerButton = By.id("registerBtn");
    private final By successMessage = By.id("regsuccessMsg");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(REGISTER_URL);
    }

    public void enterFirstName(String firstName) {
        type(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameField, lastName);
    }

    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void enterPhone(String phone) {
        type(phoneField, phone);
    }

    public void selectGender(String gender) {
        click(By.cssSelector("input[name='gender'][value='" + gender + "']"));
    }

    /**
     * @param isoDate date in yyyy-MM-dd format. The value is set via JavaScript because
     *                typing into a native date input depends on the browser locale.
     */
    public void selectDateOfBirth(String isoDate) {
        WebElement dob = find(dobField);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1];"
                        + "arguments[0].dispatchEvent(new Event('input', {bubbles: true}));"
                        + "arguments[0].dispatchEvent(new Event('change', {bubbles: true}));",
                dob, isoDate
        );
    }

    public void selectCountry(String country) {
        new Select(find(countrySelect)).selectByVisibleText(country);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        type(confirmPasswordField, confirmPassword);
    }

    public void clickRegister() {
        click(registerButton);
    }

    public void registerAs(String firstName, String lastName, String email, String phone,
                           String gender, String isoDob, String country, String password) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPhone(phone);
        selectGender(gender);
        selectDateOfBirth(isoDob);
        selectCountry(country);
        enterPassword(password);
        enterConfirmPassword(password);
        clickRegister();
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public boolean isLoginPageDisplayed() {
        return wait.until(ExpectedConditions.urlContains("index.php"));
    }
}
