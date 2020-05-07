package delfi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Core {

    WebDriver driver;
    WebDriverWait wait;

    private final Logger LOGGER = LogManager.getLogger(Core.class);

    public Core() {
        LOGGER.info("Setting up drivers...");
        System.setProperty("webdriver.chrome,driver", "C://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,5);
    }

    public void goToUrl(String url) {
        if (!url.contains("https://") && !url.contains("http://")) {
            url = "http://" + url;
        }
        LOGGER.info("Opening link and maximizing browser's window");
        driver.get(url);
        driver.manage().window().maximize();
    }

    public WebElement findElement(By locator) {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return driver.findElement(locator);
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public List<WebElement> findElements(By locator) {
        if (isElementPresent(locator)) {
           return driver.findElements(locator);
        } else {
            System.out.println("There is no elements!");
            return null;
        }
    }

    public int parseInt(WebElement webElement) {
        return Integer.parseInt(webElement.getText().replaceAll("\\D", ""));
    }

    public void click(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }
}
