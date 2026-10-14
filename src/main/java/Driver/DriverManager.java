package Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class DriverManager {

    private DriverManager() {
    }

    public static WebDriver selectDriver(String browser) {

        if (browser == null || browser.isBlank()) {
            throw new IllegalArgumentException(
                    "Browser name cannot be null or empty."
            );
        }

        return switch (browser.toLowerCase().trim()) {
            case "chrome" -> new ChromeDriver();
            case "firefox", "ff" -> new FirefoxDriver();
            case "edge" -> new EdgeDriver();
            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + browser
            );
        };
    }
}