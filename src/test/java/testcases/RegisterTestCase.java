package testcases;

import base.BaseTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignUp;
import utils.ExcelUtils;

public class RegisterTestCase extends BaseTest {

    @Test(priority = 0,dataProvider = "userData")
    public void testRegisterUser(String name, String email, String gender, String password,
            String day, String month, String year, String fname,
            String lname, String company, String address1, String address2,
            String country, String state, String city, String zip, String mobile) throws InterruptedException {

        SignUp su = new SignUp(driver);
        //HomePage hp = new HomePage(driver);
        //String expectedString =" Full-Fledged practice website for Automation Engineers";
       // String actualString = hp.validateHomePage();
        //Assert.assertEquals(actualString, expectedString);
        
        su.click_On_SignUp();
        Assert.assertEquals(su.get_SignUp_Text(), "New User Signup!");
        su.enter_Name(name);
        su.enter_Email(email);
        su.click_On_Submit();
        Assert.assertEquals(su.validate_Account_Creation(),"ENTER ACCOUNT INFORMATION");
        Thread.sleep(2000);
        switch (gender) {
		case "male":
				su.select_Male();
				break;
		case "female":
				su.select_Female();
				break;

		default:
			break;
		}
        su.enter_Password(password);
        su.select_date(day);
        su.select_month(month);
        su.select_Year(year);
        su.check_For_News();
        su.check_For_offer();
        su.enter_firstName(fname);
        su.enter_lastName(lname);
        su.enter_address1(address1);
        su.enter_address2(address2);
        su.select_Country(country);
        su.enter_State(state);
        su.enter_City(city);
        su.enter_ZipCode(zip);
        su.enter_mobileNumber(mobile);
        su.click_on_CreateAccount();
        Assert.assertEquals(su.validate_Confirmation_Of_AccountCreation(),  "ACCOUNT CREATED!");
        su.click_On_Continue();
        Assert.assertEquals(su.getUserName(), "Logged in as "+name);
        su.click_on_deleteAccount();
        Assert.assertEquals(su.confirm_Account_Deletion(), "ACCOUNT DELETED!");
        su.click_on_ContinueAfter_deletion();
		System.out.println("registration and deletion");
           
        
    }
    
    @Test(priority = 1, dataProvider = "registeredEmail")
    public void testWithAlreadyRegisteredEmail(String name, String email) {
    	
    	SignUp su = new SignUp(driver);
    	su.click_On_SignUp();
        Assert.assertEquals(su.get_SignUp_Text(), "New User Signup!");
        su.enter_Name(name);
        su.enter_Email(email);
        su.click_On_Submit();
        Assert.assertEquals(su.getSignUpErrorMessage(), "Email Address already exist!");
        System.out.println("registration with already email");
    }

    @DataProvider(name = "userData")
    public Object[][] getData() {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testDate.xlsx";
		return ExcelUtils.readExcel(filePath, "registration");

    }
    
    @DataProvider(name = "registeredEmail")
    public Object[][] getRegisteredEmail() {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testDate.xlsx";
		return ExcelUtils.readExcel(filePath, "registeredEmail");
    }
}
