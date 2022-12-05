package automationProject.pageObjects;

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
public class LoginPage extends AbstractComponent {

	WebDriver driver;

	//First thing you need to do is create a constructor that will have the driver reference 
	//from the object creation of this class.
	//Whenever the object will be created , the driver will be passed from thw argument to here/
	//and this page will be initialized with the driver

	public LoginPage(WebDriver driver) {
		
		super(driver);//using this super keyword , we are sending this driver to the parent class constructor
		this.driver = driver;
		//To initialize all the Webelements
		PageFactory.initElements(driver, this);
	}

	//PageFactory

	@FindBy(id="userEmail")
	WebElement emailtxtBox;

	@FindBy(id="userPassword")
	WebElement passwordtxtBox;

	@FindBy(id="login")
	WebElement LoginBttn;

	
	/**
	 * This method is used to login to the application 
	 * @param email
	 * @param password
	 * @return 
	 */
	public CatalougePage login(String email, String password) {
		
		emailtxtBox.sendKeys(email);
		passwordtxtBox.sendKeys(password);
		LoginBttn.click();
		
		CatalougePage catalougepage = new CatalougePage(driver);
		return catalougepage;
	}

	
	
	/**
	 * This method is created to open any URL
	 */
	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client/");

	}

}
