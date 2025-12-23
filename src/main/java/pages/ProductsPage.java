package pages;

import org.apache.poi.ss.usermodel.PrintCellComments;
import org.apache.xmlbeans.impl.xb.xmlconfig.NamespaceList.Member2.Item;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v141.emulation.model.UserAgentBrandVersion;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.github.dockerjava.api.model.SwarmNodeAvailability;

public class ProductsPage {
	
	WebDriver driver;

	@FindBy(xpath = "//a[@href='/products']")
	WebElement productButtonElement;
	
	@FindBy(xpath = "//h2[@class='title text-center']")
	WebElement productPagesHeadingElement;
	
	@FindBy(xpath = "//a[@href='/product_details/1']")
	WebElement viewProductElement;
	
	@FindBy(css = "[class='btn btn-default cart']")
	WebElement addToCartElement;
	
	@FindBy(xpath="//h2[text()='Blue Top']")
	WebElement itemNamElement;

	@FindBy(xpath = "//p[text()='Category: Women > Tops']" )		
	WebElement  categoryOFProduct;
	
	@FindBy(xpath = "//span[text()='Rs. 500']")
	WebElement price;
	
	@FindBy(xpath = "//b[normalize-space()='Availability:']")
	WebElement availability;
	
	@FindBy(xpath ="//b[text()='Condition:']")
	WebElement conditionOfProduct;
	
	@FindBy(xpath = "//b[text()='Brand:']")
	WebElement brand;
	
	public ProductsPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getConditionOfProduct() {
		
		String conditionOfPRString = conditionOfProduct.getText();
		
		return conditionOfPRString;
	}
	
	public String getAvailability() {
		
		String availabilityOfProductString = availability.getText();
		
		return availabilityOfProductString;
	}
	
	public String getPrice() {
		
		String priceOfProductString = price.getText();
		
		return priceOfProductString;
	}
	
	public String gecategoryOFProduct() {
		
		String categoryName= categoryOFProduct.getText();
		
		return categoryName;
	}
	
	public String getProductName() {
		
		String productNameString= itemNamElement.getText();
		
		return productNameString;
	}
	
	public boolean checkAddToCart() {
		
		boolean isVisible = addToCartElement.isDisplayed();
		
		return isVisible;
	}
	
	public void click_On_view() {
		
		viewProductElement.click();
	}
	
	public String getProductPageHeading() {
		
		String headingString = productPagesHeadingElement.getText();
		
		return headingString;
	}
	
	public void click_On_Product() {
		
		productButtonElement.click();
	}
	
	public String getBrandOfProduct() {
		
		String brandNameString= brand.getText();
		
		return brandNameString;
	}
}
