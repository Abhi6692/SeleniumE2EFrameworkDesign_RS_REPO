package testPackage.Tests;

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
import testPackage.TestComponents.Retry;

public class StandaloneTest_Parameterisation_DataProvider_4 extends BaseTest {

	@Test(dataProvider = "getData" , retryAnalyzer = Retry.class)
	public void testMethod(String email , String pwd , String product )  throws Throwable {
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";


		//launchApplication();

		CatalougePage catalougepage =loginpage.login(email,pwd );


		List<WebElement> products =catalougepage.getProductList();

		catalougepage.addProductToCart(product);

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
		
		return new Object [][] {{"sarkar.abhishek06@gmail.com" , "Rony#6692" , "ZARA COAT 3"} , {"shetty@gmail.com", "Iamking@000" ,  "ADIDAS ORIGINAL"}};
	}
	



}
