package BaseTest;

import org.testng.annotations.BeforeMethod;

public abstract class UnauthenticatedBaseTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void openApplication() {
        driver.manage().deleteAllCookies();
        driver.get("https://shop.qaautomationlabs.com/");
    }
}