package rahulshetty.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void i_landed_on_Ecommerce_Page() throws IOException {
		// code
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add the product (.+) to cart$")
	public void i_add_the_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		cartPage = productCatalogue.goToCartPage();

		Assert.assertTrue(cartPage.verifyProductDisplay(productName));
		CheckOutPage checkoutPage = cartPage.goToCheckout();

		checkoutPage.selectCountry("Mexico");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then ("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_confirmationPage(String message) {
		Assert.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase(message));
		driver.close();
	}
	
	@Then ("{string} error message is displayed")
	public void error_message_is_displayed(String message) {
		Assert.assertEquals(landingPage.getErrorMessage(), message);
		driver.close();
	}

}
