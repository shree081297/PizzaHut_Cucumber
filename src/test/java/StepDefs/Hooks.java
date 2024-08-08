package StepDefs;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.base.Ticker;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks {
	public static WebDriver driver;
	public static ExtentReports report = new ExtentReports("HtmlExtentReport.html", false);
	public static ExtentTest test;

	@Before
	public void StartBrowser(Scenario Scenario) {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		test = report.startTest(Scenario.getName());
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	}

	@After
	public void tearDown() {
		driver.quit();
		report.flush();
		report.endTest(test);

	}

	public static String CaptureScreen(WebDriver driver) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File target = new File("screenshot/" + ".png");

		String absolutepath = target.getAbsolutePath();

		FileUtils.copyFile(src, target);
		return absolutepath;

	}
}
