package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void ErrorLoginValidation() throws IOException, InterruptedException{

		ProductCatalogue productCatalogue = landingPage.loginApplication("pogers@gmail.com", "Poggerssss");
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
	}

	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";

		ProductCatalogue productCatalogue = landingPage.loginApplication("poggers@gmail.com", "Poggers1");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);

		CartPage cartPage = productCatalogue.goToCartPage();

		Assert.assertFalse(cartPage.verifyProductDisplay("ZARA COAT 33"));
	}
}
