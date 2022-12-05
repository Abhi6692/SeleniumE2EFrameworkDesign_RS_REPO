package automationProject.pageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationProject.abstractComponent.*;

public class CartPage extends AbstractComponent{

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath="//*[@class='cartSection']/h3")
	List<WebElement> cartProductsTitle;

	@FindBy(xpath="//button[normalize-space()='Checkout']")
	WebElement checkkOutBttn;

	public boolean verifyProductDisplay(String productName) {
		Boolean match =cartProductsTitle.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match; 


	}

	public CheckOutPage goToCheckout() {
		checkkOutBttn.click();
		CheckOutPage checkoutpage = new CheckOutPage(driver);
		return checkoutpage;

	}

}
