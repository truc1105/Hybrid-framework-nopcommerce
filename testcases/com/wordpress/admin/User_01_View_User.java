package com.wordpress.admin;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.wordpress.AdminDashboardPO;
import pageObjects.wordpress.AdminLoginPO;
import pageObjects.wordpress.AdminPostAddNewPO;
import pageObjects.wordpress.AdminPostSearchPO;
import pageObjects.wordpress.AdminUserPO;
import pageObjects.wordpress.PageGeneratorManagerWP;
import pageObjects.wordpress.UserHomePO;
import pageObjects.wordpress.UserPostDetailPO;
import pageObjects.wordpress.UserSearchPostPO;

public class User_01_View_User extends BaseTest{
	private WebDriver driver;
	String adminUsername = "automationfc";
	String adminPassword = "automationfc";
	String searchPostUrl;
	int randomNumber = generateFakeNumber();
	String postTitle = "Live Coding Title " + randomNumber;
	String postBody = "Live Coding Body " + randomNumber;
	String editPostTitle = "Edit Title " + randomNumber;
	String editPostBody = "Edit Body " + randomNumber;
	String authorName = "automationfc";
	String adminUrl, endUserUrl;
	String currentDay = getCurrentDay();
	
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPage;
	private AdminUserPO adminUserPage;

	
	@Parameters({"browser", "urlAdmin", "urlUser"})
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String endUserUrl) {
		log.info("Pre-Condition - Step 01: Open browser and Admin site");
		this.adminUrl = adminUrl;
		this.endUserUrl = endUserUrl;
		driver = getBrowserDriverURL(browserName, this.adminUrl);
		adminLoginPage = PageGeneratorManagerWP.getAdminLoginPage(driver);
		
		log.info("Pre-Condition - Step 02: Enter to Username textbox with value: " + adminUsername);
		adminLoginPage.enterToUsernameTextbox(adminUsername);
		
		log.info("Pre-Condition - Step 03: Enter to Password textbox with value: " + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);
		
		log.info("Pre-Condition - Step 04: Click to Login button");
		adminDashboardPage =  adminLoginPage.clickToLoginButton();
	}

	@Test
	public void TC_01_View_User() {
		
		  log.info("View_User - Step 01: Click to 'User' menu link"); 
		  adminUserPage = adminDashboardPage.clickToUserMenuLink();
		  
		  log.info("View_User - Step 02: Get all users from UI");
		  int totalNumberUserAtUI = adminUserPage.getUserNumberAtUI();
		  
		  log.info("View_User - Step 03: Get all users from DB");
		  int totalNumberUserAtDB = adminUserPage.getUserNumberAtDB();
		  
		  log.info("View_User - Step 04: Verify the User are matching");
		  verifyEquals(totalNumberUserAtUI, totalNumberUserAtDB);

	}
	
	
	
	

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		closeBrowserDriver();
	}
	
	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
