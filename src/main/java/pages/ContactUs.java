package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitsAndScrolls;

public class ContactUs {
	
	WebDriver driver;
	
	WaitsAndScrolls waits;
	
	@FindBy(xpath = "//a[@href='/contact_us']")
	WebElement contactUsElement;
	
	@FindBy(xpath="//h2[contains(text(), 'Get In Touch')]")
	WebElement getInTouchElement;
	
	@FindBy(xpath="//input[@data-qa='name']")
	WebElement nameElement;
	
	@FindBy(xpath="//div[@class='form-group col-md-6']/child::input[@type='email']")
	WebElement emailElement;
	
	@FindBy(xpath="//input[@placeholder='Subject']")
	WebElement subjectElement;
	
	@FindBy(xpath="//textarea[@placeholder='Your Message Here']")
	WebElement messageElement;
	
	@FindBy(xpath = "//input[@name='upload_file']")
	WebElement chooseFileElement;
	
	@FindBy(xpath="//input[@value='Submit']")
	WebElement submitElement;
	
	@FindBy(xpath="//div[@class='status alert alert-success']")
	WebElement successMessagElement;
	
	@FindBy(xpath = "//a[@class='btn btn-success']")
	WebElement homeButtonElement;

	
	public ContactUs(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
		waits = new WaitsAndScrolls(driver);
		
	}
	
	public void click_On_ContactUs() {
		
		contactUsElement.click();
	}
	
	public String getHeadingText() {
		
		String headingString= getInTouchElement.getText();
		
		return headingString;
	}
	
	public void enter_name(String name) {
		
		nameElement.sendKeys(name);
	}
	
	public void enter_Email(String email) {
		
		emailElement.sendKeys(email);
	}
	
	public void enter_Subject(String subjectString) {
		
		subjectElement.sendKeys(subjectString);
	}
	
	public void enter_Message(String message) {
		
		messageElement.sendKeys(message);
	}
	
	public void upload_File(String fileString) {
		
		chooseFileElement.sendKeys(fileString);
	}
	
	public void click_On_Submit() {
		
		submitElement.click();;
	
	}
	
	public String getSuccessMessage() {

		
		String messageString = successMessagElement.getText();
		
		return messageString;
	}
	
	public void click_On_Home() {
		
		homeButtonElement.click();
	}
}
