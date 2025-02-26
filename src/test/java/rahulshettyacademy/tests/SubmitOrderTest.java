package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	String countryName = "Mexico";

	@Test(dataProvider = "getData", groups = { "purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));

		CartPage cartPage = productCatalogue.goToCartPage();

		Assert.assertTrue(cartPage.verifyProductDisplay(input.get("product")));
		CheckOutPage checkoutPage = cartPage.goToCheckout();

		checkoutPage.selectCountry("Mexico");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		Assert.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("poggers@gmail.com", "Poggers1");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJasonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
	
	//Extent Reports - 
	

	/*
	 * HashMap<String,String> map = new HashMap<String,String>(); map.put("email",
	 * "poggers@gmail.com"); map.put("password", "Poggers1"); map.put("product",
	 * "ZARA COAT 3");
	 * 
	 * HashMap<String,String> map1 = new HashMap<String,String>(); map1.put("email",
	 * "rahulshetty@gmail.com"); map1.put("password", "Iamking@000"); map1.put("product",
	 * "ADIDAS ORIGINAL");
	 */

	/*
	 * @DataProvider public Object[] [] getData(){ return new Object [] []
	 * {{"poggers@gmail.com","Poggers1","ZARA COAT 3"}
	 * {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}} }
	 */
}
