package base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import factory.DriverFactory;

import org.openqa.selenium.WebDriver;

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
