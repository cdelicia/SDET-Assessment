package source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class Driver {
    private static WebDriver driver;

    public static WebDriver getDriver(boolean isHeadless, boolean isMaximized) {
        initialize(isHeadless, isMaximized);
        return driver;
    }

    public static void tearDown() {
        driver.quit();
    }

    private static void initialize(boolean isHeadless, boolean isMaximized) {
        //System.setProperty("webdriver.chrome.driver", "/Users/dima/chromedriver_mac_arm64/chromedriver");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        Map<String, Object> chromePreferences = new HashMap<>();
        chromePreferences.put("credentials_enable_service", false);
        chromePreferences.put("password_manager_enabled", false);

        ChromeOptions chromeOptions = new ChromeOptions();
        if (isMaximized)
            chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.setExperimentalOption("prefs", chromePreferences);
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        if (isHeadless) {
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--window-size=1920,1024");
            chromeOptions.addArguments("--disable-gpu");
        }
        driver = new ChromeDriver(chromeOptions);
    }
}

