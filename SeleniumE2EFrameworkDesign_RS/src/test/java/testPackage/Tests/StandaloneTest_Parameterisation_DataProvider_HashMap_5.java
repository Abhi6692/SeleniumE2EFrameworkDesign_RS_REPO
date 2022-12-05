package testPackage.Tests;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automationProject.pageObjects.CartPage;
import automationProject.pageObjects.CatalougePage;
import automationProject.pageObjects.CheckOutPage;
import automationProject.pageObjects.ConfirmationPage;
import testPackage.TestComponents.BaseTest;

public class StandaloneTest_Parameterisation_DataProvider_HashMap_5 extends BaseTest {

	@Test(dataProvider = "getData")
	public void testMethod(HashMap<String, String> input )  throws Throwable {
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";


		//launchApplication();

		CatalougePage catalougepage =loginpage.login(input.get("email"), input.get("password") );


		List<WebElement> products =catalougepage.getProductList();

		catalougepage.addProductToCart(input.get("product"));

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

	@DataProvider
	public Object[][] getData(){
		
		HashMap<Object, Object> map_1 = new HashMap<Object, Object>();
		map_1.put("email", "sarkar.abhishek06@gmail.com");
		map_1.put("password", "Rony#6692");
		map_1.put("product", "ZARA COAT 3");
		
		HashMap<Object, Object> map_2 = new HashMap<Object, Object>();
		map_2.put("email", "shetty@gmail.com");
		map_2.put("password", "Iamking@000");
		map_2.put("product", "ADIDAS ORIGINAL");
		
		
		return new Object [][] {{map_1} , {map_2}};
	}
	



}
