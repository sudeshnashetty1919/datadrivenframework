package factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.AdBlockListener;

import org.openqa.selenium.support.events.EventFiringDecorator;

public class DriverFactory {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> tlRawDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static WebDriver getRawDriver() {
        return tlRawDriver.get();
    }

    public static WebDriver initDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        //options.addArguments("--headless=new");   // FIX for proper screenshots
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        WebDriver raw = new ChromeDriver(options);
        tlRawDriver.set(raw);

        WebDriver decorated = new EventFiringDecorator(new AdBlockListener()).decorate(raw);

        decorated.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        decorated.manage().window().maximize();
        decorated.get("https://automationexercise.com/");

        tlDriver.set(decorated);
        return decorated;
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
        }
        tlDriver.remove();
        tlRawDriver.remove();
    }
}
