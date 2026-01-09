package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SignUp;
import utils.ExcelUtils;

public class LogOutTest extends BaseTest {
	
	@Test(dataProvider = "validCredentials")
	public void testLogOut(String email, String password, String name) {
		
		LoginPage lP = new LoginPage(driver);
		
		SignUp su = new SignUp(driver);
		
		su.click_On_SignUp();
		
		Assert.assertEquals(lP.getWelcomeText(), "Login to your account");
		
		lP.enter_Email(email);
		
		lP.enter_Password(password);
		
		lP.click_on_LoginButton();
		
		Assert.assertEquals(su.getUserName(),"Logged in as "+name );
		
		lP.click_on_LogOut();
		
		Assert.assertEquals(lP.getWelcomeText(), "Login to your account");
		System.out.println("logout");
	}
	
	
	@DataProvider(name ="validCredentials")
	public Object[][] getData(){
		String filePath = System.getProperty("user.dir") + "/src/test/resources/testDate.xlsx";
		return ExcelUtils.readExcel(filePath, "loginWithValid");
		
	}

}
