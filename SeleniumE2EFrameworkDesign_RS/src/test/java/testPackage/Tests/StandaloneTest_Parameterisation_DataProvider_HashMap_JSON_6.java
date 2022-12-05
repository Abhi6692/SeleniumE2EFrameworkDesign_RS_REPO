package testPackage.Tests;

import java.nio.charset.StandardCharsets;
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
import testPackage.data.DataReader;

public class StandaloneTest_Parameterisation_DataProvider_HashMap_JSON_6 extends BaseTest {

	DataReader dataReader = new DataReader();
	
	
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
	public Object[][] getData() throws Throwable{
		
	List<HashMap<String, String>> testData =	dataReader.getJsonDataToHashMap(System.getProperty("user.dir")  +  "\\src\\test\\java\\testPackage\\data\\TestData.json");
		
		return new Object [][] {{testData.get(0)} , {testData.get(1)}};
	}
	



}
