package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.BasePage;

public class AccountPage extends BasePage {

	@FindBy(xpath = "//span[text()='Show Account Details']")
	WebElement showAccountDetail;

	@FindBy(xpath = "//span[text()='Log out']")
	WebElement logoutButton;

	@FindBy(xpath = "//button[@name='buttonok']")
	WebElement confirmationLogout;

	public AccountPage(WebDriver driver) {
		super(driver);

	}

	public void clickAccountDetail() throws InterruptedException {
		waitElementToBeVisibile(showAccountDetail);
		Thread.sleep(3000);
		clickAction(showAccountDetail, "Click on Account Detail");
		getScreenShotOfThePage();
		log.info("Clicked Account Detail");
	}

	public void clickLogOutButton() throws InterruptedException {
		Thread.sleep(4000);
		waitElementToBeVisibile(logoutButton);
		clickAction(logoutButton, "click LogOut Button");
		log.info("clicked logoutButton");

	}

	public WebDriver clickPopupOption() {
		waitElementToBeVisibile(confirmationLogout);
		getScreenShotOfThePage();
		clickAction(confirmationLogout, "Click Yes on confirmation ");
		log.info("Clicked Yes on confirmation popup ");
		return driver;
	}

	public String getCurrentPageTitle() {
		return getPageTitle();
	}

}
