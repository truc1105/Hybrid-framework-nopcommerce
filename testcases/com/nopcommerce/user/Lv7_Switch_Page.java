package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyAccountPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewsPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointsPageObject;

public class Lv7_Switch_Page extends BaseTest{
	private WebDriver driver;
	private String firstName, lastName, EmailAddress, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserMyAccountPageObject myAccountPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewsPageObject myProductReviewsPage;
	private UserRewardPointsPageObject rewardPointsPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		firstName = "Automation";
		lastName = "Test";
		EmailAddress = "AutomationFC_" + generateFakeNumber() + "@gmail.com";
		password = "123456789";
		
	}

	@Test
	public void User_01_Register() {
		
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(EmailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Test
	public void User_02_login() {
		loginPage = homePage.clickToLoginLink();
		
		loginPage.inputToEmailTextbox(EmailAddress);
		loginPage.inputToPasswordTextbox(password);

		homePage = loginPage.clickToLoginButton();
	}
	
	@Test
	public void User_03_My_Account() {
		myAccountPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(myAccountPage.isMyAccountPageDisplayed());
	}

	@Test
	public void User_04_Switch_Page() {
		addressPage = myAccountPage.openAddressPage(driver);
		
		myProductReviewsPage = addressPage.openMyProductReviewsPage(driver);
		
		rewardPointsPage = myProductReviewsPage.openRewardPointsPage(driver);
		
		addressPage = rewardPointsPage.openAddressPage(driver);
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
