package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.TestCasesPage;

public class ValidateTestCasesPages extends BaseTest{
	
	@Test
	public void navigateToTestCases() {
		 
		TestCasesPage tP = new TestCasesPage(driver);
		
		tP.click_On_testCases();
		
		Assert.assertTrue(tP.getPageHeading().equalsIgnoreCase("Test Cases"));
	}

}
