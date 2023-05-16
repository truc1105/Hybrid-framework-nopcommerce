package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;

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

public class Lv_15_ReportNG_Screenshot extends BaseTest{
	private WebDriver driver;
	private String firstName, lastName, EmailAddress, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserMyAccountPageObject myAccountPage;

	
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
		log.info("Register - Step 01: Navigate to 'Register' page");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Register - Step 02: Enter to First name textbox with value is " + firstName);
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Register - Step 03: Enter to Last name textbox with value is " + lastName);
		registerPage.inputToLastnameTextbox(lastName);
		
		log.info("Register - Step 04: Enter to Email address textbox with value is " + EmailAddress);
		registerPage.inputToEmailTextbox(EmailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		log.info("Register - Step 07: Click to 'Register' buttom");
		registerPage.clickToRegisterButton();
		
		log.info("Register - Step 08: Verify Register success message.");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed..");
	}
	
	@Test
	public void User_02_Login() {
		log.info("Login - Step 01: Navigate to Login page");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login - Step 02: Enter to Email address textbox with value is " + EmailAddress);
		loginPage.inputToEmailTextbox(EmailAddress);
		
		log.info("Login - Step 03: Enter to Password textbox with value is " + password);
		loginPage.inputToPasswordTextbox(password);

		homePage = loginPage.clickToLoginButton();
		
		log.info("Login - Step 05: Verify Login page link");
		Assert.assertEquals(loginPage.getCurrentURL(driver), "https://Facebook.com");
		
		myAccountPage = homePage.clickToMyAccountLink();
		
		log.info("Login - Step 07: Verify MyAccount page link");
		Assert.assertFalse(myAccountPage.isMyAccountPageDisplayed());
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
