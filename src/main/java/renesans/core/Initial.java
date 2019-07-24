package renesans.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Initial {

    private static WebDriver driver;
    private static Properties properties = TestProperties.getInstance().getProperties();

    public static void startBrowser() {
        switch (properties.getProperty("browser")) {
            case "firefox":
                System.setProperty("webdriver.firefox.driver", properties.getProperty("webdriver.firefox.driver"));
                FirefoxOptions options = new FirefoxOptions();
                driver = new FirefoxDriver(options);
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                ChromeOptions option = new ChromeOptions();
                option.setExperimentalOption("useAutomationExtension", false);
                driver = new ChromeDriver(option);
                break;
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
