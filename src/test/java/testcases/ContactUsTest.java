package testcases;

import java.time.Duration;
import java.util.concurrent.TimeoutException;
import java.io.File;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ContactUs;
import utils.ExcelUtils;

public class ContactUsTest extends BaseTest{
	
	@Test(dataProvider = "contactUsData")
	public void testContactUsForm(String name, String email, String subject, String message, String fileString) throws InterruptedException {
		
		ContactUs cU = new ContactUs(driver);
		
		cU.click_On_ContactUs();
		
		Assert.assertTrue(cU.getHeadingText().equalsIgnoreCase("Get In Touch"));
		
		cU.enter_name(name);
		
		cU.enter_Email(email);
		
		cU.enter_Subject(subject);
		
		cU.enter_Message(message);
		
		cU.upload_File(fileString);
		
		cU.click_On_Submit();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
		    wait.until(ExpectedConditions.alertIsPresent());
		    Alert alert = driver.switchTo().alert();
		    alert.accept();
		    System.out.println("Alert accepted!");
		} catch (Exception e) {
		    System.out.println("Alert did NOT appear!");
		}

		
		Assert.assertEquals(cU.getSuccessMessage(), "Success! Your details have been submitted successfully.");
		
	}
	@DataProvider(name ="contactUsData")
	public Object[][] getData(){
    String filePath = System.getProperty("user.dir") + "/src/test/resources/testDate.xlsx";
    Object[][] data = ExcelUtils.readExcel(filePath, "contactUS");

    // Convert file paths to absolute paths for the file column (last column)
    for (int i = 0; i < data.length; i++) {
        int fileColumnIndex = data[i].length - 1; // assuming last column is file path
        String relativePath = (String) data[i][fileColumnIndex];
        if (relativePath != null && !relativePath.isEmpty()) {
            File file = new File(relativePath);
            data[i][fileColumnIndex] = file.getAbsolutePath();
        }
    }
    return data;
}

}
