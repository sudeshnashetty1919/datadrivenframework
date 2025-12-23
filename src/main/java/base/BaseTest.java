package base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;           // For @Listeners
import listeners.ExtentReportListener;           // Your listener class
import factory.DriverFactory;

import org.openqa.selenium.WebDriver;
@Listeners(ExtentReportListener.class)
public class BaseTest {

    public WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = DriverFactory.initDriver();
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
