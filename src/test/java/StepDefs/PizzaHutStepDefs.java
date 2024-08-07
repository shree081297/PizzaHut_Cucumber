package StepDefs;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class PizzaHutStepDefs {
	WebDriver driver = Hooks.driver;
	WebDriverWait wait;
	Map<String, String> my;
	DataTable dataTable;

	@Given("I have launched the application")
	public void i_have_launched_the_application() {
		driver.get("https://www.pizzahut.co.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@When("I enter the location as {string}")
	public void i_enter_the_location_as(String location) throws InterruptedException {
		WebElement locationFun = driver.findElement(By.xpath("//input[@type='text']"));
		locationFun.sendKeys(location);
		Thread.sleep(8000);
		locationFun.submit();

	}

	@When("I select the very first suggestion from the list")
	public void i_select_the_very_first_suggestion_from_the_list() throws InterruptedException {
		WebElement loClick = driver.findElement(By.xpath("(//div[@data-synth='offer-collection-hut'])[1]"));
		loClick.click();
		//Thread.sleep(8000);
		//WebElement OrderTime = driver.findElement(By.xpath("//button[@class='button button--secondary text-center']"));
	//	OrderTime.click();
	}

	@Then("I should land on the Deals page")
	public void i_should_land_on_the_Deals_page() throws InterruptedException {
		Thread.sleep(4000);
		String ActualTitle = driver.getTitle();
		String ExpectedTitle="Online Pizza Order, Pizza Deals, Pizza Hut Online Orders | Pizza Hut India";
		//System.out.println(ActualTitle);
		if(ExpectedTitle.equals(ActualTitle)) {
			System.out.println("Title matched");
		}else {
			System.out.println("Tilte unmatch");
		}
		Assert.assertEquals(ActualTitle, ExpectedTitle);


	}

	@Then("I select the tab as {string}")
	public void i_select_the_tab_as(String string) throws InterruptedException {
		WebElement PizzaTab = driver.findElement(By.xpath("//a[contains(@data-synth,'link--pizzas--side')]"));
		// wait.until(ExpectedConditions.elementToBeClickable(PizzaTab));
		Thread.sleep(8000);

		PizzaTab.click();
	}

	@Then("I add {string} to the basket")
	public void i_add_to_the_basket(String string) throws InterruptedException {
		WebElement AddToCart = driver.findElement(
				By.xpath("//button[@data-synth='button--schezwan-margherita-recommended-pan-personal--one-tap']"));
		AddToCart.click();
	}

	@Then("I note down the price displayed on the screen")
	public void i_note_down_the_price_displayed_on_the_screen() {
		WebElement price = driver.findElement(By.xpath("//span[@class='subtotal']"));
		String ActualPrice = price.getText();
		String ExpectedPrice="₹199.00";
		System.out.println(ActualPrice);
		if(ActualPrice.equals(ExpectedPrice)) {
			System.out.println("price matched");
		}else {
			System.out.println("unmatched");
		}
		Assert.assertEquals(ActualPrice, ExpectedPrice);
	}

	@Then("I should see the pizza {string} is added to the cart")
	public void i_should_see_the_pizza_is_added_to_the_cart() throws InterruptedException {
		Thread.sleep(4000);
		WebElement pan=driver.findElement(By.xpath("//div[text()='Pan']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pan);
		Thread.sleep(4000);
		WebElement product = driver.findElement(By.xpath("//div[@data-synth='basket-item-type--pizza']"));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@data-synth='basket-item-type--pizza']")));
		//String ActualProduct=product.getText();
		//System.out.println(ActualProduct);
	//	Assert.assertTrue(product.isDisplayed());
		System.out.println(product.getText());
		if(product.isDisplayed()) {
			System.out.println("Product displayed");
		}else {
			System.out.println("Product not displayed");
		}
		
	}

	@Then("price is also displayed correctly")
	public void price_is_also_displayed_correctly() {
		WebElement TotalPrice = driver.findElement(By.xpath("//span[@class='amountdue']"));
		String ActualPrice=TotalPrice.getText();
		System.out.println(ActualPrice);
		Assert.assertTrue(TotalPrice.isDisplayed());
	}

	@Then("I click on the Checkout button")
	public void i_click_on_the_Checkout_button() {
		WebElement CheckOut = driver.findElement(By.xpath("//*[text()='Checkout']"));
		CheckOut.click();
	}

	@Then("I should be landed on the secured checkout page")
	public void i_should_be_landed_on_the_secured_checkout_page() {
		String ActualTitle = driver.getTitle();
		String ExpectedTitle="Order Pizza Online - Delivery and Takeaway";
		if(ActualTitle.equals(ExpectedTitle)) {
			System.out.println("Title matched");
		}else {
			System.out.println("Title unmatch");
		}
		Assert.assertEquals(ActualTitle, ExpectedTitle);
	
	}

	@Then("I enter the personal details")
	public void i_enter_the_personal_details(DataTable dataTable) throws InterruptedException {

		List<Map<String, String>> testdata = dataTable.asMaps(String.class, String.class);
		System.out.println(testdata);

		for (Map<String, String> e : testdata) {
			WebElement name = driver.findElement(By.xpath("//input[@name='name']"));
			name.sendKeys(e.get("Name"));
			Thread.sleep(4000);

			WebElement mobile = driver.findElement(By.id("checkout__phone"));
			mobile.sendKeys(e.get("Mobile"));
			WebElement email = driver.findElement(By.id("checkout__email"));
			email.sendKeys(e.get("Email"));
		}

	}

	@Then("I enter the address details")
	public void i_enter_the_address_details(io.cucumber.datatable.DataTable dataTable) {
		List<String> addressDetails = dataTable.asList(String.class);
		for (String addressDetail : addressDetails) {
			driver.findElement(By.id("checkout__deliveryAddress.interior")).sendKeys(addressDetail);
		}

	}
}
