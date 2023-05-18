package projetodesafio;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import helpers.ChromeHelper;

public class ECommerceTest {

	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = ChromeHelper.create("\"https://www.americanas.com.br/\"");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {

		WebElement searchInput = driver.findElement(By.id("h_search-input"));
		searchInput.sendKeys("Iphone 14");

		WebElement searchButton = driver.findElement(By.id("h_search-btn"));
		searchButton.click();

		WebElement searchResults = driver.findElement(By.className("product-grid"));
		
		if (searchResults.isDisplayed()) {
			System.out.println("A busca retornou resultados corretamente.");
		} else {
			System.out.println("A busca não retornou resultados ou ocorreu um erro.");
		}

		WebElement productLink = driver.findElement(By.cssSelector(".product-grid .product-item:first-child a"));
		String productName = productLink.getText();

		productLink.click();

		WebElement addToCartButton = driver.findElement(By.id("btn-buy"));
		addToCartButton.click();

		WebElement cartProduct = driver.findElement(By.cssSelector(".basket-productTitle a"));
		String cartProductName = cartProduct.getText();

		if (cartProductName.equals(productName)) {
			System.out.println("O produto foi adicionado corretamente ao carrinho.");
		} else {
			System.out.println("O produto não foi adicionado ao carrinho ou ocorreu um erro.");
		}		
	}
}
