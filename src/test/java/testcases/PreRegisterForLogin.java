package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SignUp;
import utils.ExcelUtils;
import utils.WaitsAndScrolls;

public class PreRegisterForLogin {
	
	private WebDriver driver;

    // Pass the already initialized driver from the test
    public PreRegisterForLogin(WebDriver driver) {
        this.driver = driver; // ThreadLocal-safe driver from current test thread
    }
	
	

    public void createUserForLoginTest() {

        String email = EmailGeneration.randomEmail();
        String password = "123456";
        String name = "AutoUser";

        SignUp su = new SignUp(driver);
        LoginPage lP = new LoginPage(driver);

        su.click_On_SignUp();
        su.enter_Name(name);
        su.enter_Email(email);
        su.click_On_Submit();

        su.select_Male();
        su.enter_Password(password);
        su.select_date("10");
        su.select_month("May");
        su.select_Year("1995");
        su.check_For_News();
        su.check_For_offer();
        su.enter_firstName("Auto");
        su.enter_lastName("User");
        su.enter_address1("Test Street");
        su.enter_address2("Near Park");
        su.select_Country("India");
        su.enter_State("Telangana");
        su.enter_City("Hyderabad");
        su.enter_ZipCode("500001");
        su.enter_mobileNumber("9876543210");
        
        su.click_on_CreateAccount();

        su.click_On_Continue();
        lP.click_on_LogOut();

        ExcelUtils.writeLoginData(email, password, name);
    }

}
