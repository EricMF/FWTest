package EAEnterprise.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import EAEnterprise.TestComponents.BaseTest;
import EAEnterprise.pageobjects.CartPage;
import EAEnterprise.pageobjects.CheckoutPage;
import EAEnterprise.pageobjects.ConfirmationPage;
import EAEnterprise.pageobjects.OrderPage;
import EAEnterprise.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyItemsInCart(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.checkout();
		checkoutPage.chooseCountry("United");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.VerifyConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("erpolat@gmail.com", "Qwerty12$");
		OrderPage orderPage = productCatalogue.goToOrderPage();

		Assert.assertTrue(orderPage.VerifyOrdersDisplayed(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "/src/test/java/EAEnterprise/data/PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}

//HashMap<String, String> map = new HashMap<String, String>();
//map.put("email", "erpolat@gmail.com");
//map.put("password", "Qwerty12$");
//map.put("product", "ZARA COAT 3");
//
//HashMap<String, String> map1 = new HashMap<String, String>();
//map1.put("email", "erpolat2@gmail.com");
//map1.put("password", "Qwerty123$");
//map1.put("product", "ADIDAS ORIGINAL");