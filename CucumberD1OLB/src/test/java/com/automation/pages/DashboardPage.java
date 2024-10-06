package com.automation.pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.BasePage;
import com.automation.utility.AutomationConstants;

public class DashboardPage extends BasePage {
	
	
	@FindBy(xpath = "//button[@class='btn app-button btn-transparent btn-center-align-icon pull-right']")
	WebElement titleViewButton;
	
	/*@FindBy(xpath = "//span[text()='List View']")
	WebElement titleViewButton;*/
	
	/*@FindBy(xpath = "//i[@class='app-icon icon-card-layout']")
	WebElement titleViewButton;*/
	
	@FindBy(xpath = AutomationConstants.SELECTED_ACCT_XPATH)
	WebElement selectedAcct;
	
	
	/*@FindBy(xpath = "//div[@widget-id='widget-id4348']")
	WebElement account;*/
	
	

	public DashboardPage(WebDriver driver) {
		super(driver);
	}
	

	public void clickTileView() throws InterruptedException {
		Thread.sleep(3000);
		waitElementToBeVisibile(titleViewButton);
		clickAction(titleViewButton, "TitleViewicon");
		log.info("click titleViewButton");
		//report.logTestinfo("Entered clickUserMenuDD");
		
	}

	public WebDriver clickMMAAccountTile() throws InterruptedException {
		

		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedAcct);
		//element.click();
		waitElementToBeVisibile(selectedAcct);
		try {
			jsClickAction(selectedAcct);
			//clickAction(selectedAcct, "selectedAcct");
		} catch (ElementClickInterceptedException e) {
		    // Optionally wait or handle the issue here
		    Thread.sleep(1000); // wait before retrying
		    //clickAction(selectedAcct, "selectedAcct");
		    getScreenShotOfThePage();
		    jsClickAction(selectedAcct);
		}
		
		log.info("click on EverBank Savings Account");
		return driver;
		
	}

	public String getCurrentPageTitle() {
		return getPageTitle();		
	}
	
	
}
