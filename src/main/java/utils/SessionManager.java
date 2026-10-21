package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public final class SessionManager {

    private static final String BASE_URL = "https://shop.qaautomationlabs.com/";

    private SessionManager() {}

    /**
     * The application tracks the logged-in state with the "isLoggedIn" localStorage flag
     * (no cookies are involved), so the session is restored by setting that flag.
     */
    public static void applySession(WebDriver driver) {
        driver.get(BASE_URL);
        ((JavascriptExecutor) driver)
                .executeScript("window.localStorage.setItem('isLoggedIn', 'true');");
    }
}
