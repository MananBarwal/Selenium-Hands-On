package LoginTests;

import BaseTest.UnauthenticatedBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends UnauthenticatedBaseTest {

    private static final String VALID_EMAIL = "demo@demo.com";
    private static final String VALID_PASSWORD = "demo";

    private static final String WRONG_PASSWORD = "WrongPass123";

    private static final String NON_EXISTENT_EMAIL = "nonexistent@testmail.com";

    @Test
    public void loginWithNonExistentEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs(NON_EXISTENT_EMAIL, VALID_PASSWORD);
        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Invalid email or password!",
                "The application should show a user-not-found message."
        );
    }

    @Test
    public void successfulLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginAs(VALID_EMAIL, VALID_PASSWORD);

//        Assert.assertTrue(
//                loginPage.isDashboardPageDisplayed(),
//                "User should be redirected to dashboard.php after valid login."
//        );
    }

    @Test
    public void loginWithIncorrectPassword() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginAs(VALID_EMAIL, WRONG_PASSWORD);

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Invalid email or password!",
                "The application should show an invalid-credentials message."
        );
    }

    @Test
    public void loginWithEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginAs("", "");

        Assert.assertTrue(
                loginPage.getEmailError()
                        .contains("Please enter your email."),
                "A validation error should be displayed for empty credentials."
        );
    }
}