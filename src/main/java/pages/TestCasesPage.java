package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCasesPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//a[@href='/test_cases']")
	WebElement testCaseButtonElement;
	
	@FindBy(xpath = "//h2[@class='title text-center']")
	WebElement testCaseHeadingElement;
	
	public TestCasesPage(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	public void click_On_testCases() {
		
		testCaseButtonElement.click();
	}

	public String getPageHeading() {
		
		String headingString = testCaseHeadingElement.getText();
		
		return headingString;
	}

}
