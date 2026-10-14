package BaseTest;

import org.testng.annotations.BeforeMethod;

public abstract class UnauthenticatedBaseTest extends base.BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void openApplication() {
        driver.manage().deleteAllCookies();
        driver.get("https://shop.qaautomationlabs.com/");
    }
}