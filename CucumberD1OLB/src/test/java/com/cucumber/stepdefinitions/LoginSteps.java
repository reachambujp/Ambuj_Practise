package com.cucumber.stepdefinitions;

import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.pages.AccountPage;
import com.automation.pages.DashboardPage;
import com.automation.pages.LoginPage;
import com.automation.utility.AutomationConstants;
import com.automation.utility.Log4jUtility;
import com.automation.utility.PropertiesUtility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginSteps {
	protected static Logger log;
	public static WebDriver driver;
	protected static Log4jUtility logObject = Log4jUtility.getInstance();
	LoginPage login;
	DashboardPage dashboard;
	AccountPage accountPg;
	Properties appDataProperties;
	protected WebDriverWait wait;

	public void goToUrl(String url) {
		
		driver.get(url);
		log.info(url + "is entered");
	}

	@Before
	public void setUpEachScenario() {
		log = logObject.getLogger();
		launchBrowser("chrome");

	}

	@After
	public void closeBrowser() {
		log.info("current browser closed");
		if (driver != null) {
			driver.quit(); // Closes all browser windows and ends the WebDriver session
		}

	}

	@BeforeClass
	public static void setUpBeforeAllScenarios() {
		//log = logObject.getLogger();
	}

	/*@AfterClass
	public static File getScreenShotOfThePage() {

		String date = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		TakesScreenshot screenshort = (TakesScreenshot) driver;
		File imgFile = screenshort.getScreenshotAs(OutputType.FILE);
		File destFile = new File(AutomationConstants.SCREENSHOTS_DIRECTORY_PATH + date + ".png");
		try {
			FileUtils.copyFile(imgFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destFile;

	}*/

	public void launchBrowser(String browserName) {
		switch (browserName) {
		case "firefox":
			WebDriverManager.firefoxdriver().browserVersion("109.0.1").setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();

			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();

			break;
		}
		System.out.println(browserName + " browser opened");

	}

	@Given("User opens D1 OLB application")
	public void user_open_d1_olb_application() throws InterruptedException {
		//log.info("Step 1 File Path="+AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);

		PropertiesUtility propUtil = new PropertiesUtility();
		appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		
		if (null == appDataProperties) {
			log.info("Failed to load appDataProperties");
		} else {
			log.info("appDataProperties loaded fine");
		}
		
		
		String url = appDataProperties.getProperty("app.url");
		log.info("URL Value="+url);

		goToUrl(url);
		Thread.sleep(2000);
		System.out.println("driver in baseTest=" + driver);
	}

	@When("User is on {string}")
	public void user_is_on(String pageName) {
		log.info("Page Name=" + pageName);
		if ("LoginPage".equalsIgnoreCase(pageName)) {
			login = new LoginPage(driver);
		} else if ("AccountPage".equalsIgnoreCase(pageName)) {
			accountPg = new AccountPage(driver);
		} else if ("DashboardPage".equalsIgnoreCase(pageName)) {
			dashboard = new DashboardPage(driver);
		}
	}

	@When("User enters username")
	public void user_enters_username() throws InterruptedException {
		log.info("Username =" + appDataProperties.getProperty("valid.userid"));

		login.enterUserName(appDataProperties.getProperty("valid.userid"));

	}

	@When("User enters password")
	public void user_enters_valid_password() throws InterruptedException {
		login.enterPassword(appDataProperties.getProperty("valid.password"));
	}

	@When("User clicks on login button")
	public void clicks_on_login_button() {
		driver = login.performLogin();
	}

	@Then("Verify DashboardPage Title")
	public void user_is_navigated_to_dashboard() throws InterruptedException {
		log.info("Landed on Dashboard");
		Thread.sleep(4000);
		// String actualText = dashboard.getCurrentPageTitle();
		String actualText = dashboard.getPageTitle();
		log.info("Page title=" + actualText);
		// Assert.assertEquals(actualText,
		// appDataProperties.getProperty("dashboard.title"));
	}

	@When("User click on Tile View")
	public void user_click_on_tile_view() throws InterruptedException {
		dashboard.clickTileView();
		log.info("Tile view icon is clicked");
	}

	@When("User click on MMA Account tile")
	public void user_click_on_mma_account_tile() throws InterruptedException {
		driver = dashboard.clickMMAAccountTile();
		log.info("MMA Account tile is clicked");
	}

	@When("User clicks on Show Accout Details")
	public void user_clicks_on_show_accout_details() throws InterruptedException {
		accountPg.clickAccountDetail();
		log.info("Account detail icon is clicked");
	}

	@When("User clicks on Logout")
	public void user_clicks_on_logout() throws InterruptedException {
		accountPg.clickLogOutButton();
		log.info("User clicked on logout button");
	}

	@When("User clicks Yes on confirmation")
	public void user_clicks_yes_on_confirmation() {
		driver = accountPg.clickPopupOption();
		log.info("User clicks Yes on confirmation");
	}

	@Then("Verify Login Title")
	public void verify_login_title() {
		log.info("Landed on accountPage");
		String actualText = login.getPageTitle();
		log.info("Page title=" + actualText);
		// Assert.assertEquals(actualText,
		// appDataProperties.getProperty("loginpage.title"));
	}

}
