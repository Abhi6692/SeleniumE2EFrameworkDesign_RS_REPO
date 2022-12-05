package testPackage.Tests;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import automationProject.pageObjects.CartPage;
import automationProject.pageObjects.CatalougePage;
import automationProject.pageObjects.CheckOutPage;
import automationProject.pageObjects.ConfirmationPage;
import testPackage.TestComponents.BaseTest;

public class StandaloneTest_2 extends BaseTest {

	@Test
	public void testMethod()  throws Throwable {
		// TODO Auto-generated method stub
		
		System.out.println("This is test method of StandaloneTest_2 ---> Executed Successfully");
	
		//Designed to fail
		Assert.assertTrue(false);
	}



}
