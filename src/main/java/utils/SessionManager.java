package utils;

import Driver.DriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import java.util.Set;

public final class SessionManager {

    private static Set<Cookie> authenticationCookies;
    private static final String BASE_URL = "https://shop.qaautomationlabs.com/";
    private static final String LOGIN_URL = "https://shop.qaautomationlabs.com/index.php";
    private static final String VALID_EMAIL = "demo@demo.com";
    private static final String VALID_PASSWORD = "demo";

    private SessionManager() {}

    public static synchronized void createSession() {
        if (authenticationCookies != null && !authenticationCookies.isEmpty()) {
            return;
        }

        WebDriver setupDriver = DriverManager.selectDriver("chrome");

        try {
            setupDriver.manage().window().maximize();
            setupDriver.get(LOGIN_URL);
            LoginPage loginPage = new LoginPage(setupDriver);
            loginPage.loginAs(VALID_EMAIL, VALID_PASSWORD);
            authenticationCookies = setupDriver.manage().getCookies();
            if (authenticationCookies.isEmpty()) {
                throw new RuntimeException(
                        "Login appeared successful, but no cookies were captured.");
            }
        } finally {
            setupDriver.quit();
        }
    }


    public static void applySession(WebDriver driver) {
        createSession();
        driver.get(BASE_URL);
        driver.manage().deleteAllCookies();
        for (Cookie cookie : authenticationCookies) {
            driver.manage().addCookie(cookie);
        }
        driver.navigate().refresh();
    }
}