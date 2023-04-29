package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import EAEnterprise.pageobjects.CartPage;
import EAEnterprise.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void waitForElementToAppears(By findBy) {

		WebDriverWait waitA = new WebDriverWait(driver, Duration.ofSeconds(5));
		waitA.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

	public void waitForWebElementToAppears(WebElement findBy) {

		WebDriverWait waitA = new WebDriverWait(driver, Duration.ofSeconds(5));
		waitA.until(ExpectedConditions.visibilityOf(findBy));

	}

	public void waitForElementToDisappears(WebElement element) throws InterruptedException {
		Thread.sleep(2000);
		// WebDriverWait waitD = new WebDriverWait(driver, Duration.ofSeconds(5));
		// waitD.until(ExpectedConditions.invisibilityOf(element));

	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartButton;

	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement orderButton;

	public CartPage goToCartPage() {

		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	public OrderPage goToOrderPage() {

		orderButton.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}
