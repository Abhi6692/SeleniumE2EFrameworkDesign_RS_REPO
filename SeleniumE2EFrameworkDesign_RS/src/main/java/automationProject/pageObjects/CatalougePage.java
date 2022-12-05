package automationProject.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationProject.abstractComponent.AbstractComponent;
;

/**
 * @author OM
 *
 */
public class CatalougePage extends AbstractComponent {

	WebDriver driver;

	//First thing you need to do is create a constructor that will have the driver reference 
	//from the object creation of this class.
	//Whenever the object will be created , the driver will be passed from thw argument to here/
	//and this page will be initialized with the driver

	public CatalougePage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;

		//To initialize all the Webelements
		PageFactory.initElements(driver, this);
	}

	//PageFactory

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css="..ng-animating")
	WebElement spinner;
	
	
	By productsBy = By.cssSelector(".mb-3");
	By addtoCartBy = By.cssSelector(".card-body button:last-of-type");
	By toastMessageBy = By.cssSelector("#toast-container");
	By spinnerBy = By.cssSelector("..ng-animating");
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prodName = getProductList().stream().
				filter(product->product.findElement(By.cssSelector("b")).getText().
						equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prodName ;
	}
	
	
	public void addProductToCart(String productName) {
		
		WebElement prod = getProductByName(productName);
		prod.findElement(addtoCartBy).click();
		waitForElementToAppear(toastMessageBy);
		waitForElementToDisappear(spinner);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
