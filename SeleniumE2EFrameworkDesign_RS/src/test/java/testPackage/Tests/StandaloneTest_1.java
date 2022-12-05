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

public class StandaloneTest_1 extends BaseTest {

	@Test
	public void testMethod()  throws Throwable {
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";


		//launchApplication();

		CatalougePage catalougepage =loginpage.login("sarkar.abhishek06@gmail.com", "Rony#6692");


		List<WebElement> products =catalougepage.getProductList();

		catalougepage.addProductToCart(productName);

		//CartPage cartpage =catalougepage.clickCart();
		CartPage cartpage =catalougepage.JSMethodClickonCartIcon();
		Boolean match =	cartpage.verifyProductDisplay(productName);

		Assert.assertTrue(match);

		//Click on the checkout button
		CheckOutPage checkoutpage =cartpage.goToCheckout();
		Thread.sleep(3000);

		checkoutpage.selectCountry("India");

		ConfirmationPage confirmationpage = checkoutpage.submitOrderByJavaScriptExecutor();

		String ActualconfirmMessage = confirmationpage.getConfirmationMessg();



		//Had to use this javascript , as normal click was not working due to [[ElementClickInterceptedException]]
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click()", placeOrderBttn);


		Assert.assertTrue(ActualconfirmMessage.equalsIgnoreCase("Thankyou for the order."), "The Purchase is not successful, please check the message");



	}

	@Test (dependsOnMethods = {"testMethod"})
	public void testMethod2() {

		System.out.println("StandaloneTest_1 -- testMethod2 ");

	}

	@Test
	public void testMethod3() {

		System.out.println("StandaloneTest_1 -- testMethod3 ");
	}

}
