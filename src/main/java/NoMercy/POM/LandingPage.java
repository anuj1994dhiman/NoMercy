package NoMercy.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "userEmail")
	WebElement userName;
	
	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(css = ".toast-message")
	WebElement errorMessage;
	
	public ProductsPage login(String username, String password) {
		userName.sendKeys(username);
		userPassword.sendKeys(password);
		login.click();
		ProductsPage productsPage = new ProductsPage(driver);
		return productsPage;
	}

	public void goTo() {
		// TODO Auto-generated method stub
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	public String getErrorMsg() throws InterruptedException {
		Thread.sleep(1000);
		return errorMessage.getText();
	}

}
