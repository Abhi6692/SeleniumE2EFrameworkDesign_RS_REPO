package automationProject.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationProject.abstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryTxtBox;
	
	@FindBy(css="button.ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	@FindBy(css =".btnn.action__submit.ng-star-inserted")
	WebElement placeOrderBttn;
	
	public void selectCountry(String CountryName) throws InterruptedException {
		
		Actions a = new Actions(driver);
		a.sendKeys(countryTxtBox, CountryName).build().perform(); 
		//countryTxtBox.sendKeys(CountryName);
		selectCountry.click();
		
	}
	
	public ConfirmationPage submitOrder() {
		placeOrderBttn.click();
		ConfirmationPage confirmationpage =	new ConfirmationPage(driver);
		return confirmationpage ;
		
	}
	
	public ConfirmationPage submitOrderByJavaScriptExecutor() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("arguments[0].click()", placeOrderBttn);
				return new ConfirmationPage(driver);
		
	}
	

}
