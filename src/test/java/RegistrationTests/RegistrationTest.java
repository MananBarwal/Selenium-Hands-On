package RegistrationTests;

import BaseTest.UnauthenticatedBaseTest;
import org.testng.Assert;
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

    @Test
    public void TC001_successfulUserRegistrationWithValidData() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();

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
}
