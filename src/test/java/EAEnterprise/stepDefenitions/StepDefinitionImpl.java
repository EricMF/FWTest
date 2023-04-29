package EAEnterprise.stepDefenitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import EAEnterprise.TestComponents.BaseTest;
import EAEnterprise.pageobjects.CartPage;
import EAEnterprise.pageobjects.CheckoutPage;
import EAEnterprise.pageobjects.ConfirmationPage;
import EAEnterprise.pageobjects.LandingPage;
import EAEnterprise.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {

		landingPage = launchApplication();
	}

	// (.+) represents any data type (str,int,char etc)
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {

		productCatalogue = landingPage.loginApplication(username, password);

	}

	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyItemsInCart(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.checkout();
		checkoutPage.chooseCountry("United");
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_ConfirmationPage(String string) {
		String confirmMessage = confirmationPage.VerifyConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));

		driver.close();
	}

	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String strArg1) {

		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}

}
