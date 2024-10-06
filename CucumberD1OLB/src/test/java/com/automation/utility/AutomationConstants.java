package com.automation.utility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AutomationConstants {
	
	public static final String CUR_DIR = System.getProperty("user.dir");	
	public static final String APP_DATA_PROPERTIES_FILE_PATH = CUR_DIR+"/src/test/resources/applicationdata.properties";
	public static final String SPARKS_HTML_REPORT_PATH = CUR_DIR+"/reports/testReport/sparks.html";
	public static final String SCREENSHOTS_DIRECTORY_PATH = CUR_DIR+"/screenshorts/screenshort";
	
	public static final String LOGIN_USER_ID_XPATH = "//input[@name='j_username_formWidget']";
	public static final String LOGIN_PASSWORD_XPATH = "//input[@name='j_password_formWidget']";
	public static final String LOGIN_SUBMIT_BTN_XPATH = "//button[@type='submit']";
	
	//public static final String USER_NAME_ACCOUNT_XPATH = "//*[@id=\"ng-app\"]/app-root/app-page-main/div/main/div/div[2]/div/div/div[1]/section/app-prefab-lcd_dashboard_accountlisting/div/div[3]/div[2]/ul/li[10]/div/div[3]/div/div/div[1]/div[1]/div/div[1]/div[1]";
	public static final String SELECTED_ACCT_XPATH = "//label[text()='Backup Savings']";

}

