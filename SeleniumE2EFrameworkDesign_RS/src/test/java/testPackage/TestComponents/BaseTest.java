package testPackage.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import automationProject.pageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LoginPage loginpage;

	public WebDriver initializeDriver() throws IOException {

		String PropertyFilePath =System.getProperty("user.dir")+"\\src\\main\\java\\automationProject\\resources\\GlobalData.properties";

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(PropertyFilePath);
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if(browserName.equalsIgnoreCase("chrome")) {
			
			//Chrome diver will be automatically downloaded in your system based on the browser version
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
			
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			
			//firefox
		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			
			//edge
		}
		
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	return driver;
	}
	
	
	//Utility method to take a screenshot 
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
	
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		
		
		
		
	}
	
	@BeforeMethod
	public LoginPage launchApplication () throws IOException {
		driver = initializeDriver();
		loginpage = new LoginPage(driver);
		loginpage.goTo();
		return loginpage;
	}

	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	

}
