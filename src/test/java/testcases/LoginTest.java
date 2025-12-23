package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SignUp;
import utils.ExcelUtils;

public class LoginTest extends BaseTest {
	
	@BeforeClass
	public void setUpUser() {
	    PreRegisterForLogin pre = new PreRegisterForLogin(driver);
	    pre.createUserForLoginTest();
	}
	
	@Test(dataProvider = "userData")
	public void testLogin(String email, String password, String name) {
		
		LoginPage lP = new LoginPage(driver);
		
		SignUp su = new SignUp(driver);
		
		su.click_On_SignUp();
		
		Assert.assertEquals(lP.getWelcomeText(), "Login to your account");
		
		lP.enter_Email(email);
		
		lP.enter_Password(password);
		
		lP.click_on_LoginButton();
		
		Assert.assertEquals(su.getUserName(),"Logged in as "+name );
		
		su.click_on_deleteAccount();
        
		Assert.assertEquals(su.confirm_Account_Deletion(), "ACCOUNT DELETED!");
        
		su.click_on_ContinueAfter_deletion();
		
		ExcelUtils.deleteLastLoginData();
			
	}
	
	@Test(dataProvider = "invalidData")
	public void testLoginWithInvalidCredentials(String email, String password) {
		
		LoginPage lP = new LoginPage(driver);
		
		SignUp su = new SignUp(driver);
		
		su.click_On_SignUp();
		
		Assert.assertEquals(lP.getWelcomeText(), "Login to your account");
		
		lP.enter_Email(email);
		
		lP.enter_Password(password);
		
		lP.click_on_LoginButton();
		
		Assert.assertEquals(lP.getErrorMessage(),"Your email or password is incorrect!" );
	}
	
	@DataProvider(name = "userData")
	public Object[][] getValidData(){
		
		 String filePath = System.getProperty("user.dir") + "/src/test/resources/testData.xlsx";
		return ExcelUtils.readExcel(filePath, "login");

		
		
	}
	
	@DataProvider(name = "invalidData")
	public Object[][] getInvalidData(){
		
		 String filePath = System.getProperty("user.dir") + "/src/test/resources/testData.xlsx";
		return ExcelUtils.readExcel(filePath, "invalidData");

		
		
	}

}
