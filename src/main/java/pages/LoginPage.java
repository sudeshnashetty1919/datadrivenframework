package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitsAndScrolls;

public class LoginPage {
	
	WebDriver driver;
	WaitsAndScrolls wait;
	
	@FindBy(xpath="//h2[text()='Login to your account']")
	WebElement welcomeMessageForLoginElement;
	
	@FindBy(xpath="//form[@action='/login']//input[@type='email']")
	WebElement loginEmailElement;
	
	@FindBy(xpath="//form[@action='/login']//input[@type='password']")
	WebElement loginPassElement;
	
	@FindBy(xpath="//form[@action='/login']/child::button[@type=\"submit\"]")
	WebElement loginButtonElement;
	
	@FindBy(xpath="//p[contains(text(), 'Your email or password is incorrect!')]")
	WebElement incorrectCredentialsElement;
	
	@FindBy(xpath="//a[@href='/logout']")
	WebElement logOutElement;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitsAndScrolls(driver); 
		
	}
	
	public String getWelcomeText() {
		
		String welcomeText = welcomeMessageForLoginElement.getText();
		
		return welcomeText;
		
	}
	
	public void enter_Email(String email ){
		
		loginEmailElement.sendKeys(email);
			
	}
	
	public void enter_Password(String passString) {
		
		loginPassElement.sendKeys(passString);
	}
	
	public void click_on_LoginButton() {
		
		loginButtonElement.click();
		
	}
	
	public String getErrorMessage() {
		
		String errorMessageString= incorrectCredentialsElement.getText();
		
		return errorMessageString;
	}
	
	public void click_on_LogOut() {
		
		logOutElement.click();
	}

}
