package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> productesOrdered;
	
	@FindBy(css=".totalRow button")
	WebElement submitButton;
	
	public boolean verifyOrderDisplay(String productName) {
		boolean match = productesOrdered.stream().anyMatch(productOrdered -> productOrdered.getText().equalsIgnoreCase(productName));
		return match;
	}
}
