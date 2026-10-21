package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public final class SessionManager {

<<<<<<< HEAD
    private static final String BASE_URL =
            "https://shop.qaautomationlabs.com/";

    private static final String SHOP_URL =
            "https://shop.qaautomationlabs.com/shop.php";

    private SessionManager() {}

    public static void applySession(WebDriver driver) {
        // Open application domain before accessing localStorage.
        driver.get(BASE_URL);

        driver.manage().deleteAllCookies();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.localStorage.clear();");
        js.executeScript("window.sessionStorage.clear();");

        js.executeScript(
                "window.localStorage.setItem('isLoggedIn', 'true');"
        );

        // Open the authenticated dashboard/shop page.
        driver.get(SHOP_URL);
=======
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
>>>>>>> 5d8c9330f1266979460ff384d1c4fdd3d6af31b0
    }
}
