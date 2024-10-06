package com.automation.pages.base;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.utility.AutomationConstants;
//import com.automation.utility.ExtentReportUtility;
import com.automation.utility.Log4jUtility;

public class BasePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Logger log;
	//protected ExtentReportUtility report=ExtentReportUtility.getInstance();
	protected Log4jUtility log4jUtil=Log4jUtility.getInstance();
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
		//log = LogManager.getLogger(BasePage.class.getName());
		log=log4jUtil.getLogger();
				
	}
	
	public  void enterTextValue(WebElement element, String data, String objName) {
		System.out.println("Entered enterTextValue.");
		
		if (element.isEnabled() && element.isDisplayed()) {
			//System.out.println("Entered if");
		    element.clear();  // Clear any pre-existing text
		    element.sendKeys(data);
		} else {
		    System.out.println("Element is not interactable.");
		}
		
		
	}
	
	public void enterTextValueJSE(WebElement element, String data) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value=\"<%= data %>\";", element);
	}
	
		
	public  void clickAction(WebElement element, String objName) {
		if(element.isDisplayed()) {
			element.click();
			log.info(objName+" is clicked ");
			//report.logTestinfo(objName+" is clicked ");
			
		}else {
			log.info(objName+" is not clicked");
			//report.logTestinfo(objName+" is not  clicked ");
		}
	}
	
	public void jsClickAction(WebElement element) {
		try {
			Thread.sleep(2000);
			element.click();
		  } catch (Exception e) {
		     JavascriptExecutor executor = (JavascriptExecutor) driver;
		     executor.executeScript("arguments[0].click();", element);
		     //log.info("saveButton field is clicked");
		  }
		
	}
	 
	
	
	public  void clearFieldData(WebElement element) {
		if(element.isDisplayed()) {
			element.clear();
			log.info("fieldData is clear");
		}else {
			System.out.println("fieldData is displayed");
		}
	}
	
	
	public void moveToElementAction(WebElement element,String objname) {
		Actions action= new Actions(driver);
		action.moveToElement(element).build().perform();
		log.info("cursor move to web element"+ objname);
		
	}
	public void contextClicktAction(WebElement element,String objname) {
		Actions action= new Actions(driver);
		action.clickAndHold(element).build().perform();
		log.info("right click performed on element"+ objname);
		
	}
	
	public  void fluentWaitForVisibility(WebElement element, String objname) {
		Wait<WebDriver> fluentwait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(ElementNotInteractableException.class);
		fluentwait.until(ExpectedConditions.visibilityOf(element));				
		
	}
	
	public  void waitElementToBeClickable(WebElement element, String objName) {
		log.info("waiting for element "+objName+" to be clickable");
		 wait = new WebDriverWait(driver,Duration.ofSeconds(70));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	public void waitElementToBeVisibile(WebElement userID) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	   wait.until(ExpectedConditions.visibilityOf(userID));
	}
	
	
	
	public  void waitUntilAlertIsPresent() {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.alertIsPresent());
		log.info("waiting for alert to be present");
		
		
	}
	
	public void performAction(String inputTxt) {
		Actions action = new Actions(driver);
		action.sendKeys(inputTxt).build().perform();
		
	}
	
	
	public void switchFrame(String frameName) {
		driver.switchTo().frame(frameName);
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	
	public  Alert switchToAlert() {
	Alert alert=driver.switchTo().alert();
		return alert;
		
	}
	
	public void dismisalAlert() {
		Alert alert=switchToAlert();
		alert.dismiss();
		log.info("Alert dismissed");
		
	}
	
	public static String getAlertText(Alert alert) {
		return alert.getText();
		
	}
	
	public static String getElementText(WebElement element) {
		return element.getText();
		
	}
	
	public void selectByVisibilityTextData(WebElement element, String text, String objName) {
		Select select=new Select(element);
		select.selectByVisibleText(text);
		log.info(objName+ "selected" + text);	
	}
	
	
	public void switchToWindowOpened(String mainWindowHandle) {
		Set<String> allWindowHandle = driver.getWindowHandles();
		for (String winHandle : allWindowHandle) {
			if (!mainWindowHandle.equalsIgnoreCase(winHandle)) {
				driver.switchTo().window(winHandle);	
			}
			log.info("Switched to new window");
		}

	}

	public File getScreenShotOfThePage() {
		
		String date= new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		TakesScreenshot screenshort=(TakesScreenshot)driver;
		File imgFile=screenshort.getScreenshotAs(OutputType.FILE);
		File destFile= new File(AutomationConstants.SCREENSHOTS_DIRECTORY_PATH+date+".png");
		try {
		FileUtils.copyFile(imgFile, destFile);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return destFile;
		
		
	}
	
	public void closeWindowUsingRobot() {
		try {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		}catch(Exception ex) {
			log.info("Cannot close window due to exception");
		}
	}
	
	public void popUpWindow() {
		try {
		Thread.sleep(4000); //PopUp window
		WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
		getScreenShotOfThePage();
		popUpWindow.click();
		log.info("Popup window close");
		}catch(Exception ex) {
			log.error("Cannot close Popupwindow due to exception");
			
		}
		
	}
	
	
	 
	
}
