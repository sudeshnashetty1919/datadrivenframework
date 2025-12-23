package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class AdBlockListener implements WebDriverListener {
	private static final By AD_CONTAINER =
            By.xpath("//ins[contains(@class,'adsbygoogle') and @data-anchor-status]");

    @Override
    public void afterGet(WebDriver driver, String url) {
        closeAdIfOpen(driver);
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        closeAdIfOpen(driver);
    }

    // ❌ REMOVE beforeClick — it breaks form submissions!
    // @Override
    // public void beforeClick(WebElement element, WebDriver driver) { ... }

    private void closeAdIfOpen(WebDriver driver) {
        try {
            WebElement anchor = driver.findElement(AD_CONTAINER);
            String status = anchor.getAttribute("data-anchor-status");

            if (status != null && !status.equalsIgnoreCase("dismissed")) {
                WebElement closeBtn = anchor.findElement(By.xpath(".//div[@class='grippy-host']"));
                closeBtn.click();
                System.out.println("Ad closed safely.");
            }
        } catch (Exception ignore) {
            // No ad — ignore
        }
    }
}
