package EAEnterprise.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	// creating web-driver object
	WebDriver driver;

	// creating constructions same name as a class then referring to
	public LandingPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

	// creating PageFactory using @FindBy

	@FindBy(id = "userEmail")
	WebElement username;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement submit;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	// creating login action to send user-info
	public ProductCatalogue loginApplication(String user, String pass) {

		username.sendKeys(user);
		password.sendKeys(pass);
		submit.click();

		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;

	}

	public String getErrorMessage() {
		waitForWebElementToAppears(errorMessage);
		return errorMessage.getText();
	}

}
