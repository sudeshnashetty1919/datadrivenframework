package pages;

import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Factory;

import utils.WaitsAndScrolls;


public class SignUp{

	WebDriver driver;
	WaitsAndScrolls wait;
	
	@FindBy(xpath="//a[@href='/login']")
	WebElement signUpElement;
	
	@FindBy(xpath="//h2[text() = 'New User Signup!']")
	WebElement validateSignupTextElement;
	
	@FindBy(xpath="//input[@type='text']")
	WebElement namElement;
	
	@FindBy(xpath="//form[@action='/signup']//input[@type='email']")
	WebElement emaElement;
	
	@FindBy(xpath="//button[@data-qa='signup-button']")
	WebElement signUpButtonElement;
	
	@FindBy(xpath="//h2//b[text() = 'Enter Account Information']")
	WebElement confirmationElement;
	
	@FindBy(id="id_gender1")
	WebElement mrElement;
	
	@FindBy(id="id_gender2")
	WebElement mrsElement;
	
	@FindBy(id="password")
	WebElement pasElement;
	
	@FindBy(id="days")
	WebElement dayElement;
	
	@FindBy(id="months")
	WebElement monthElement;
	
	@FindBy(id="years")
	WebElement yearElement;
	
	@FindBy(id="newsletter")
	WebElement checkBoxforNewElement;
	
	@FindBy(id="optin")
	WebElement checkBoxforOfferElement;

	@FindBy(id ="first_name")
	WebElement firstNamElement;
	
	@FindBy(id ="last_name")
	WebElement lastNamElement;
	
	@FindBy(id ="company")
	WebElement companyNamElement;
	
	@FindBy(id ="address1")
	WebElement address1Element;
	
	@FindBy(id="address2")
	WebElement address2Element;
	
	@FindBy(id ="country")
	WebElement countryElement;
		
	@FindBy(id ="state")
	WebElement stateElement;
	
	@FindBy(id ="city")
	WebElement cityElement;
		
	@FindBy(id ="zipcode")
	WebElement zipCodeElement;
		
	@FindBy(id ="mobile_number")
	WebElement mobileNumberElement;
	
	@FindBy(xpath="//button[@data-qa='create-account']")
	WebElement createAccountElement;
	
	@FindBy(xpath="//b[text() = 'Account Created!']")
	WebElement accountCreationCofirmationElement;
	
	@FindBy(xpath="//a[@data-qa='continue-button']")
	WebElement continuElement;
	
	@FindBy(xpath="(//div[@class='shop-menu pull-right']//ul//li)[10]")
	WebElement usernamElement;
	
	@FindBy(xpath="//a[@href='/delete_account']")
	WebElement deleteAccountElement;
	
	@FindBy(xpath="//b[text()= 'Account Deleted!']")
	WebElement accountDeleteConfirmationElement;
	
	@FindBy(xpath="//a[@data-qa='continue-button']")
	WebElement deleteContiunElement;
	
	@FindBy(xpath="//p[contains(text(),'Email Address already exist!')]")
	WebElement errorMessageElement;
	
	@FindBy(xpath = "//div[@class='grippy-host']")
	WebElement addElement;
	
	
	public SignUp(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
		wait = new WaitsAndScrolls(driver); 
	
	}
	
	public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

	public void click_On_SignUp() {
		
		signUpElement.click();
	}
	
	public String get_SignUp_Text() {
		
		String signUpTextString = validateSignupTextElement.getText();
		return signUpTextString;
	}
	
	public void enter_Name(String name) {
		
		namElement.sendKeys(name);
	}
	
	public void enter_Email(String email) {
		
		emaElement.sendKeys(email);
	}
	
	public void click_On_Submit() {
		
		signUpButtonElement.click();
	}
	
	public String validate_Account_Creation() {
		
		wait.waitForVisibility(confirmationElement);
		String confirmationCreation = confirmationElement.getText();
		
		return confirmationCreation;
	}
	
	public void select_Male(){
		 
		mrElement.click();
	} 
	
	public void select_Female(){
		 
		mrsElement.click();
	} 
	
	public void enter_Password(String pass) {
		
		pasElement.sendKeys(pass);
	}
	
	public void select_date(String dateString) {
		scrollIntoView(dayElement);
		Select dpSelect = new Select(dayElement);
		dpSelect.selectByVisibleText(dateString);
	}
	
	public void select_month(String monthString) {
		scrollIntoView(monthElement);
		Select dpSelect = new Select(monthElement);
		dpSelect.selectByVisibleText(monthString);
	}
	
	public void select_Year(String yearString) {
		scrollIntoView(yearElement);
		Select dpSelect = new Select(yearElement);
		dpSelect.selectByVisibleText(yearString);
	}
	
	public void check_For_News() {
		
		checkBoxforNewElement.click();
	}
	
	public void check_For_offer() {
		
		checkBoxforOfferElement.click();
	}
	
	public void enter_firstName(String fName) {
		
		firstNamElement.sendKeys(fName);
	}
	
	public void enter_lastName(String lName) {
		
		lastNamElement.sendKeys(lName);
	}
	
	public void enter_companyName(String cName) {
		
		companyNamElement.sendKeys(cName);
	}
	
	public void enter_address1(String add) {
		
		address1Element.sendKeys(add);
	}
	
	public void enter_address2(String add) {
		
		address2Element.sendKeys(add);
	}
	
	public void enter_State(String state) {
		
		stateElement.sendKeys(state);
	}
	
	public void enter_City(String city) {
		
		cityElement.sendKeys(city);
	}
	
	public void enter_ZipCode(String code) {
		
		zipCodeElement.sendKeys(code);
	}
	
	public void select_Country(String country) {
		
		Select dpSelect = new Select(countryElement);
		dpSelect.selectByValue(country);
	}
	
	public void enter_mobileNumber(String mobile) {
		
		mobileNumberElement.sendKeys(mobile);
	}
	
	public void click_on_CreateAccount() {
		createAccountElement.click();
	}
	
	public String validate_Confirmation_Of_AccountCreation() {
		String confirmation = accountCreationCofirmationElement.getText();
		
		return confirmation;
	}
	
	public void click_On_Continue() {

		scrollIntoView(continuElement);
		
		continuElement.click();
	}
	
	public String getUserName() {
		
		String userName = usernamElement.getText();
		
		return userName;
	}
	
	public void click_on_deleteAccount() {
		
		deleteAccountElement.click();
	}
	
	public String confirm_Account_Deletion() {
		
		String confirmation = accountDeleteConfirmationElement.getText();
		
		return confirmation;
	}
	
	public void click_on_ContinueAfter_deletion() {
		
		deleteContiunElement.click();
	}
	
	public String getSignUpErrorMessage() {
		
		String errorMessageString = errorMessageElement.getText();
		
		return errorMessageString;
	}
	
	public void closeAdd() {
		
		addElement.click();
	}
}
