package RegistrationTests;

import BaseTest.UnauthenticatedBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegisterPage;

public class RegistrationTest extends UnauthenticatedBaseTest {

    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL = "john.doe@testmail.com";
    // The app only accepts a 10-digit numeric phone (input type=number), so "+1-555-0123" is not usable.
    private static final String PHONE = "5550123456";
    private static final String GENDER = "Male";
    private static final String DOB = "1990-01-15"; // 15-Jan-1990
    private static final String COUNTRY = "United States";
    private static final String PASSWORD = "Test@1234";

    private static final String MISMATCHED_PASSWORD = "Test@5678";
    private static final String INVALID_EMAIL = "john.doetestmail.com";
    private static final String EMPTY_FIRST_NAME = "";
    private static final String INVALID_PHONE = "12345";

    private RegisterPage registerPage;

    @BeforeMethod(alwaysRun = true)
    public void openRegisterPage() {
        registerPage = new RegisterPage(driver);
        registerPage.open();
    }

    @Test
    public void successfulUserRegistrationWithValidData() {
        registerPage.registerAs(FIRST_NAME, LAST_NAME, EMAIL, PHONE, GENDER, DOB, COUNTRY, PASSWORD);

        Assert.assertEquals(
                registerPage.getSuccessMessage(),
                "Registration successful! Redirecting to login page...",
                "The application should show a registration success message."
        );
        Assert.assertTrue(
                registerPage.isLoginPageDisplayed(),
                "User should be redirected to the login page after successful registration."
        );
    }

    @Test
    public void registrationWithPasswordMismatch() {
        registerPage.registerAs(FIRST_NAME, LAST_NAME, EMAIL, PHONE, GENDER, DOB, COUNTRY,
                PASSWORD, MISMATCHED_PASSWORD);

        Assert.assertEquals(
                registerPage.getConfirmPasswordError(),
                "Passwords do not match.",
                "A validation error should be shown when the passwords do not match."
        );
    }

    @Test
    public void registrationWithInvalidEmail() {
        registerPage.registerAs(FIRST_NAME, LAST_NAME, INVALID_EMAIL, PHONE, GENDER, DOB, COUNTRY, PASSWORD);

        Assert.assertEquals(
                registerPage.getEmailError(),
                "Please enter a valid email address.",
                "An email validation error should be shown for an email without '@'."
        );
    }

    @Test
    public void registrationWithEmptyFirstName() {
        registerPage.registerAs(EMPTY_FIRST_NAME, LAST_NAME, EMAIL, PHONE, GENDER, DOB, COUNTRY, PASSWORD);

        Assert.assertEquals(
                registerPage.getFirstNameError(),
                "Please enter your first name.",
                "A validation error should be shown when the first name is empty."
        );
    }

    @Test
    public void registrationWithInvalidPhone() {
        registerPage.registerAs(FIRST_NAME, LAST_NAME, EMAIL, INVALID_PHONE, GENDER, DOB, COUNTRY, PASSWORD);

        Assert.assertEquals(
                registerPage.getPhoneError(),
                "Phone Number Should be 10 digit long.",
                "A phone validation error should be shown for a phone number shorter than 10 digits."
        );
    }
}
