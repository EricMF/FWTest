package EAEnterprise.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(xpath = "//tbody/tr/td[2]")
	List<WebElement> ordersTitle;

	public Boolean VerifyOrdersDisplayed(String productName) {

		Boolean match = ordersTitle.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

}
