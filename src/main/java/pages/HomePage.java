package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	
	@FindBy(xpath = "(//h2[contains(text(), \"Full-Fledged \")])[1]")
	WebElement homElement;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String validateHomePage() {
		
		String homeText = homElement.getText();
		return homeText;
	}
}
