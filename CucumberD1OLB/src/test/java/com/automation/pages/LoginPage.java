package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.pages.base.BasePage;
import com.automation.utility.AutomationConstants;

import io.cucumber.messages.types.Duration;

public class LoginPage extends BasePage {


	@FindBy(xpath = AutomationConstants.LOGIN_USER_ID_XPATH)
	WebElement userID;
	@FindBy(xpath = AutomationConstants.LOGIN_PASSWORD_XPATH)
	WebElement passwordele;
	@FindBy(xpath = AutomationConstants.LOGIN_SUBMIT_BTN_XPATH)
	WebElement LoginButton;
	
	

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void enterUserName(String userName) {

		waitElementToBeVisibile(userID);
		// enterTextValueJSE(userID, userName);
		jsClickAction(userID);
		enterTextValue(userID, userName, "userNameElement");
		log.info("Entered userName");
	}

	public void enterPassword(String password) {

		waitElementToBeVisibile(passwordele);
		//enterTextValueJSE(passwordele, password);
		jsClickAction(passwordele);
		enterTextValue(passwordele,password,"passwordNameElement");
		log.info("Entered enterPassword");
		
	}

	public WebDriver performLogin() {
		getScreenShotOfThePage();
		log.info("Entered performLogin");
		// clickAction(LoginButton,"clickLoginButton");
		jsClickAction(LoginButton);
		log.info("Entered performLogin");
		return driver;
	}

}
