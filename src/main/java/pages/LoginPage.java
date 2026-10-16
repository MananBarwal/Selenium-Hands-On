package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("loginBtn");
    private final By errorMessage = By.id("errorMsg");
    private final By emailError = By.id("emailerror");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        sendData(emailField, email);
    }

    public void enterPassword(String password) {
        sendData(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void loginAs(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public String getEmailError() {
        return getText(emailError);
    }

    public boolean isDashboardPageDisplayed() {
        return wait.until(
                ExpectedConditions.urlContains("shop.php")
        );
    }
}