package EAEnterprise.tests;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import EAEnterprise.TestComponents.BaseTest;
import EAEnterprise.TestComponents.Retry;
import EAEnterprise.pageobjects.CartPage;
import EAEnterprise.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException {

		ProductCatalogue productCatalogue = landingPage.loginApplication("erpolat2@gmail.com", "Qwerty1243$");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException {

		// creating product catalog object and sending user-info
		ProductCatalogue productCatalogue = landingPage.loginApplication("erpolat2@gmail.com", "Qwerty123$");
		List<WebElement> products = productCatalogue.getProductList();
		String productName = "ZARA COAT 3";
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyItemsInCart("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
