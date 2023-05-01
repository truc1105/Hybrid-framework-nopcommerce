package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageFactory.RegisterPageObject;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyAccountPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewsPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointsPageObject;

public class Lv8_Switch_Role extends BaseTest{
	private WebDriver driver;
	private String userEmailAddress, userPassword, adminEmailAddress, adminPassword;
	private UserRegisterPageObject userRegisterPage;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserMyAccountPageObject userMyAccountPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		
		userEmailAddress = "automationFC143@gmail.com.vn";
		userPassword = "123456";
		
		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";
		
		userRegisterPage = userHomePage.clickToRegisterLink();
		
		userRegisterPage.inputToFirstnameTextbox("Automation");
		userRegisterPage.inputToLastnameTextbox("FC");
		userRegisterPage.inputToEmailTextbox(userEmailAddress);
		userRegisterPage.inputToPasswordTextbox(userPassword);
		userRegisterPage.inputToConfirmPasswordTextbox(userPassword);
		
		userRegisterPage.clickToRegisterButton();
	}

	@Test
	public void Role_01_User_To_Admin() {
		// login as user role
		userLoginPage = userHomePage.clickToLoginLink();
		
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);

		// Home page -> Cust info (My account)
		userMyAccountPage = userHomePage.openMyAccountPage(driver);
		
		//Cust info -> logout -> Home page
		userHomePage = userMyAccountPage.clickToLogoutLinkAtUserPage(driver);
		
		//User home page -> Open admin page -> login page
		userHomePage.openPageURL(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		// login as admin role
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
		
		// dashboard page -> logout -> login page(admin)
		adminLoginPage = adminDashboardPage.clickToLogoutLinkAdminPage(driver);
	}

	@Test
	public void Role_02_Admin() {
		// login page(admin) -> Open User url -> Homepage(User)
		adminLoginPage.openPageURL(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
		
		// Homepage -> Login page(User)
		adminLoginPage = adminDashboardPage.clickToLogoutLinkAdminPage(driver);
		userHomePage.openPageURL(driver, GlobalConstants.PORTAL_PAGE_URL);
		
		// login as User role
		userLoginPage = userHomePage.clickToLoginLink();
		
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
	}
	

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
