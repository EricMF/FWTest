package EAEnterprise.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> productTitle;

	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;

	public Boolean VerifyItemsInCart(String productName) {

		Boolean match = productTitle.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckoutPage checkout() {
		checkoutButton.click();

		return new CheckoutPage(driver);

	}
}
