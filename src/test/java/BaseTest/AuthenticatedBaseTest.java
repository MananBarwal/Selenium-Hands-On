package BaseTest;

import org.testng.annotations.BeforeMethod;
import utils.SessionManager;

public abstract class AuthenticatedBaseTest extends base.BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void restoreAuthenticatedSession() {
        SessionManager.applySession(driver);
    }
}