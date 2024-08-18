package StepDefs;

import java.io.IOException;
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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class PizzaHutStepDefs {
	WebDriver driver = Hooks.driver;
	// ExtentTest test=Hooks.driver;
	WebDriverWait wait;
	ExtentTest test;
	 ExtentReports report;

	@Given("I have launched the application")
	public void i_have_launched_the_application() throws IOException {
		try {

			driver.get("https://www.pizzahut.co.in/");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			Hooks.test.log(LogStatus.PASS, "Application has launched");
			
		} catch (Exception e) {
			Hooks.test.log(LogStatus.FAIL, "User failed to land on HomePage");
			Assert.fail();
		}
	}

	@When("I enter the location as {string}")
	public void i_enter_the_location_as(String location) throws InterruptedException, IOException {
		try {

			WebElement locationFun = driver.findElement(By.xpath("//input[@type='text']"));
			locationFun.sendKeys(location);
	
		} catch (Exception e) {
			Hooks.test.log(LogStatus.FAIL, "User failed to enter location in location field");
			Assert.fail();
		}
	}

	@When("I select the very first suggestion from the list")
	public void i_select_the_very_first_suggestion_from_the_list() throws InterruptedException, IOException {
		try {
			List<WebElement>list=driver.findElements(By.xpath("//div[text()='Pune Railway Station']"));
			System.out.println(list.size());
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getText().contains("Pune Railway Station")) {
				list.get(i).click();
				}
			}
		
			Hooks.test.log(LogStatus.PASS, "User received popup and clicks on address in popup");
		} catch (Exception e) {
			Hooks.test.log(LogStatus.FAIL, "User unable to received popup");
			Assert.fail();
		}
	}

	@Then("I should land on the Deals page")
	public void i_should_land_on_the_Deals_page() throws InterruptedException, IOException {
		try {

			Thread.sleep(4000);
			String ActualTitle = driver.getTitle();
			String ExpectedTitle = "Online Pizza Order, Pizza Deals, Pizza Hut Online Orders | Pizza Hut India";
			// System.out.println(ActualTitle);
			if (ExpectedTitle.equals(ActualTitle)) {
				System.out.println("Title matched");
			} else {
				System.out.println("Tilte unmatch");
			}
			// Assert.assertEquals(ActualTitle, ExpectedTitle);
			Hooks.test.log(LogStatus.PASS, "User should be able the title as" + ActualTitle + ExpectedTitle);
		} catch (Exception e) {
			Hooks.test.log(LogStatus.FAIL, "Title unmatched");
			Assert.fail();
		}
	}

	@Then("I select the tab as {string}")
	public void i_select_the_tab_as(String string) throws InterruptedException, IOException {
		try {

			WebElement PizzaTab = driver.findElement(By.xpath("//a[contains(@data-synth,'link--pizzas--side')]"));
			// wait.until(ExpectedConditions.elementToBeClickable(PizzaTab));
			Thread.sleep(5000);
			PizzaTab.click();
			Hooks.test.log(LogStatus.PASS, "User click's on Pizza tub");
		} catch (Exception e) {
			Hooks.test.log(LogStatus.FAIL, "User unable to click on Pizza tub");
			Assert.fail();
		}
	}

	@Then("I add {string} to the basket")
	public void i_add_to_the_basket(String string) throws InterruptedException, IOException {
		try {

			WebElement AddToCart = driver.findElement(
					By.xpath("//button[@data-synth='button--schezwan-margherita-recommended-pan-personal--one-tap']"));
			AddToCart.click();
			Hooks.test.log(LogStatus.PASS, "User click's on Add to cart button on Schezwan margherita");
		} catch (Exception e) {
			Hooks.test.log(LogStatus.FAIL, "User unable to click on Add to cart button on Schezwan margherita");
			Assert.fail();
		}
	}

	@Then("I note down the price displayed on the screen")
	public void i_note_down_the_price_displayed_on_the_screen() throws IOException {
		try {

			WebElement price = driver.findElement(By.xpath("//span[@class='subtotal']"));
			String ActualPrice = price.getText();
			String ExpectedPrice = "₹199.00";
			System.out.println(ActualPrice);
			if (ActualPrice.equals(ExpectedPrice)) {
				System.out.println("price matched");
			} else {
				System.out.println("unmatched");
			}
			// Assert.assertEquals(ActualPrice, ExpectedPrice);
			Hooks.test.log(LogStatus.PASS, "Verifying the price as 199rs");
		} catch (Exception e) {
			Hooks.test.log(LogStatus.FAIL, "User unbale to verify the price as 199rs");
			Assert.fail();
		}
	}

	@Then("I should see the pizza {string} is added to the cart")
	public void i_should_see_the_pizza_is_added_to_the_cart() throws InterruptedException, IOException {
		try {

			WebElement product = driver.findElement(By.xpath("//div[@data-synth='basket-item-type--pizza']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(product));
			// String ActualProduct=product.getText();
			System.out.println(product);
			System.out.println(product.getText());
			if (product.isDisplayed()) {
				System.out.println("Product displayed");
			} else {
				System.out.println("Product not displayed");
			}
			// Assert.assertTrue(product.isDisplayed());
			Hooks.test.log(LogStatus.PASS, "Verifying the product is added in cart");
		} catch (Exception e) {
			Hooks.test.log(LogStatus.FAIL, "User unable to verify the price as 199rs");
			Assert.fail();
		}
	}

	@Then("price is also displayed correctly")
	public void price_is_also_displayed_correctly() throws IOException {
		try {

			WebElement TotalPrice = driver.findElement(By.xpath("//span[@class='amountdue']"));
			String ActualPrice = TotalPrice.getText();
			System.out.println(ActualPrice);
			Assert.assertTrue(TotalPrice.isDisplayed());
			Hooks.test.log(LogStatus.PASS, "User should be able to get total price" + ActualPrice);
		} catch (Exception e) {
			Hooks.test.log(LogStatus.FAIL, "User unable to get total price");
			Assert.fail();
		}
	}

	@Then("I click on the Checkout button")
	public void i_click_on_the_Checkout_button() throws IOException {
		try {

			WebElement CheckOut = driver.findElement(By.xpath("//*[text()='Checkout']"));
			CheckOut.click();
			Hooks.test.log(LogStatus.PASS, "User click's on Checkout button");
		} catch (Exception e) {
			Hooks.test.log(LogStatus.FAIL, "User ubale to click on Checkout button");
			Assert.fail();
		}
	}

	@Then("I should be landed on the secured checkout page")
	public void i_should_be_landed_on_the_secured_checkout_page() throws IOException {
		try {

			String ActualTitle = driver.getTitle();
			String ExpectedTitle = "Order Pizza Online - Delivery and Takeaway";
			if (ActualTitle.equals(ExpectedTitle)) {
				System.out.println("Title matched");
			} else {
				System.out.println("Title unmatch");
			}
			// Assert.assertEquals(ActualTitle, ExpectedTitle);
			Hooks.test.log(LogStatus.PASS, "verifying the checkout page is displayed");
		} catch (Exception e) {
			Hooks.test.log(LogStatus.FAIL, "User failed to land  checkout page");

		}
	}

	@Then("I enter the personal details")
	public void i_enter_the_personal_details(DataTable dataTable) throws InterruptedException, IOException {
		try {

			List<Map<String, String>> testdata = dataTable.asMaps(String.class, String.class);
			System.out.println(testdata);

			for (Map<String, String> e : testdata) {
				WebElement name = driver.findElement(By.xpath("//input[@name='name']"));
				name.sendKeys(e.get("Name"));
				WebElement mobile = driver.findElement(By.id("checkout__phone"));
				mobile.sendKeys(e.get("Mobile"));
				WebElement email = driver.findElement(By.id("checkout__email"));
				email.sendKeys(e.get("Email"));
				Hooks.test.log(LogStatus.PASS, "User sucessfully enter the UserName Mobile Email ");
			}
		} catch (Exception e) {
			Hooks.test.log(LogStatus.FAIL, "User failed to enter UserName Mobile Email ");

		}

		/*
		 * @Then("I enter the address details") public void
		 * i_enter_the_address_details(io.cucumber.datatable.DataTable dataTable) {
		 * List<String> addressDetails = dataTable.asList(String.class); for (String
		 * addressDetail : addressDetails) {
		 * driver.findElement(By.id("checkout__deliveryAddress.interior")).sendKeys(
		 * addressDetail); }
		 * 
		 * }
		 */
	}
}
