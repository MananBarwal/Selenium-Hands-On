package utils;

import Driver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public final class SessionManager {

    private static boolean authenticated;
    private static final String BASE_URL = "https://shop.qaautomationlabs.com/";
    private static final String LOGIN_URL = "https://shop.qaautomationlabs.com/index.php";
    private static final String VALID_EMAIL = "demo@demo.com";
    private static final String VALID_PASSWORD = "demo";

    private SessionManager() {}

    public static synchronized void createSession() {
        if (authenticated) {
            return;
        }

        WebDriver setupDriver = DriverManager.selectDriver("chrome");

        try {
            setupDriver.manage().window().maximize();
            setupDriver.get(LOGIN_URL);
            LoginPage loginPage = new LoginPage(setupDriver);
            loginPage.loginAs(VALID_EMAIL, VALID_PASSWORD);
            String isLoggedIn = (String) ((JavascriptExecutor) setupDriver)
                    .executeScript("return window.localStorage.getItem('isLoggedIn');");
            if (!"true".equals(isLoggedIn)) {
                throw new RuntimeException(
                        "Login appeared successful, but the authenticated session was not established.");
            }
            authenticated = true;
        } finally {
            setupDriver.quit();
        }
    }


    public static void applySession(WebDriver driver) {
        createSession();
        driver.get(BASE_URL);
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.setItem('isLoggedIn', 'true');"
        );
        driver.navigate().refresh();
    }
}