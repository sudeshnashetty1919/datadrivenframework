package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	@FindBy(id="search_product")
	WebElement searchBoxElement;
	
	@FindBy(id="submit_search")
	WebElement searchButtonElement;
	
	@FindBy(css = ".features_items .productinfo p")
	List<WebElement> allProductsElements;
	
	public SearchPage(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	public void enter_SearchItem(String item) {
		
		searchBoxElement.sendKeys(item);
	}
	
	public void click_On_Search() {
		
		searchButtonElement.click();
	}
	
	public List<String> getAllSearchedProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement e : allProductsElements) {
            names.add(e.getText().trim());
        }
        return names;
    }

}
