package page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "mat-input-0")
	private WebElement pinCode;
	
	@FindBy(id = "flexRadioDefault1")
	private WebElement age;

	@FindBy(xpath = "//button[@class='pin-search-btn']")
	private WebElement searchButton;

	@FindBy(xpath = "//span[@class='age-limit' and text()='Age 18+']")
	private List<WebElement> availableFor18;

	@FindBy(xpath = "//span[@class='age-limit' and text()='Age 18+']/../../a[text()=' Booked ']")
	private List<WebElement> availableFor18Booked;

//	@FindBy(xpath = "//span[@class='age-limit' and text()='Age 45+']")
//	private List<WebElement> availableFor45;
//
//	@FindBy(xpath = "//span[@class='age-limit' and text()='Age 45+']/../../a[text()=' Booked ']")
//	private List<WebElement> availableFor45Booked;
	
	@FindBy(xpath = "//p[text()='No Vaccination center is available for booking.']")
	private WebElement stopMsg;
	
	@FindBy(xpath = "//a[@class='right carousel-control carousel-control-next']")
	private WebElement swipeRight;
	
	@FindBy(xpath = "//a[@class='left carousel-control carousel-control-prev']")
	private WebElement swipeLeft;
	
	
	

	public void chooseAge() {
		age.click();
	}
	public void enterPinCode(String pin) {
		pinCode.clear();
		pinCode.sendKeys(pin);
	}

	public void clickOnsearchButton() {
		searchButton.click();
	}

	public int findAvailableSlotsFor18() {
		int freeSlotsFor18=availableFor18.size()-availableFor18Booked.size();
		return freeSlotsFor18;
	}

//	public int findAvailableSlotsFor45() {
//		int freeSlotsFor45=availableFor45.size()-availableFor45Booked.size();
//		return freeSlotsFor45;
//	}
	
	public void rightSwipe() {
		swipeRight.click();
	}
	
	public void leftSwipe() {
		swipeLeft.click();
	}
	
	public boolean stopMsgCheck() throws InterruptedException {
		boolean flag=false;
		Thread.sleep(1000);
		try {
			flag=stopMsg.isDisplayed();
		} catch (Exception e) {
			System.out.println("stopMsg not present");
		}
		return flag;
	}

}
