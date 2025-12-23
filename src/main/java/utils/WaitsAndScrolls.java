package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitsAndScrolls {
	
	private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public WaitsAndScrolls(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    // 🔹 Wait for element to be visible
    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // 🔹 Wait for element to be clickable
    public WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // 🔹 Scroll element into view
    public void scrollIntoView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // 🔹 Click with wait + scroll
    public void clickWhenReady(WebElement element) {
        scrollIntoView(element);
        waitForClickable(element).click();
    }

    // 🔹 Type text safely
    public void sendKeysWhenVisible(WebElement element, String text) {
        waitForVisibility(element).clear();
        element.sendKeys(text);
    }

    // 🔹 Wait for page load
    public void waitForPageLoad() {
        wait.until(driver -> 
            js.executeScript("return document.readyState").equals("complete"));
    }
    


}
