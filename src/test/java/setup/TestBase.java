package setup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class TestBase {
    private static final Logger logger = LogManager.getLogger(TestBase.class);

    static WebDriver driver = null;
    static String browserName = null;
    static String currentPath = System.getProperty("user.dir");

    public String getBrowserName() {
        browserName = System.getProperty("browser");

        if(browserName == null) {
            browserName = "ch";
            logger.info("No browser provided, selecting a default one: {}", browserName);
        } else {
            logger.info("Browser provided is: {}", browserName);
        }
        return browserName;
    }

    public WebDriver initializeWebDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "ff":
            case "firefox":
                System.setProperty("webdriver.gecko.driver",currentPath + "/src/test/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;

            case "ch":
            case "chrome":
                System.setProperty("webdriver.chrome.driver",currentPath + "/src/test/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;

            default:
                logger.error("Invalid browser selected: {}", browser);
                System.exit(0);
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        return driver;
    }
}
