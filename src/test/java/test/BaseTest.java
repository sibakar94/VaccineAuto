package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;



public class BaseTest {

	protected WebDriver driver;
	
	@BeforeTest
	public void initializeBroswer() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Siba\\eclipse-workspace\\VaccineAuto\\src\\test\\resources\\driver\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.cowin.gov.in/home");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterTest
	public void close() {
		driver.close();
	}
}
