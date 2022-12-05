package automationProject.abstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationProject.pageObjects.CartPage;

/**
 * @author Abhishek Sarkar
 *This is the parent calss to all the page object classes
 */
public class AbstractComponent {

	WebDriver driver;

	//this driver is sent via super' in the child pageobject classes
	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartIcon;
	
	
	public void waitForElementToAppear(By findBy) {


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy)); 
	}
	
	
	public void waitForElementToDisappear(WebElement ele) {


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.invisibilityOf(ele)); 
	}
	
	public CartPage clickCart() {
		
		cartIcon.click();
		CartPage cartpage =new CartPage(driver);
		return cartpage;
		
	}
	
	public CartPage JSMethodClickonCartIcon() {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",cartIcon );
		return new CartPage(driver);
	}
}
