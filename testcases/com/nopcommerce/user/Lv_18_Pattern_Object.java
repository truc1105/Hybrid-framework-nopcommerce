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

public class Lv_18_Pattern_Object extends BaseTest{
	private WebDriver driver;
	private String firstName, lastName, EmailAddress, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserMyAccountPageObject myAccountPage;
	private String day = "11";
	private String month = "May";
	private String year = "1999";

	
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
		
		log.info("Register - Step 02: Choose 'Gender' - Radio button");
		registerPage.clickToRadioButtonByLabel(driver, "Male");
		
		log.info("Register - Step 03: Enter to First name textbox with value is " + firstName);
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);
		
		log.info("Register - Step 04: Enter to Last name textbox with value is " + lastName);
		registerPage.inputToTextboxByID(driver, "LastName", lastName);
		
		log.info("Register - Step 05: Select date of Birth with value is " + day + "/" + month + "/" + year);
		registerPage.selectToDropdownByName(driver, "DateOfBirthDay", day);
		registerPage.selectToDropdownByName(driver, "DateOfBirthMonth", month);
		registerPage.selectToDropdownByName(driver, "DateOfBirthYear", year);
		
		log.info("Register - Step 06: Enter to Email address textbox with value is " + EmailAddress);
		registerPage.inputToTextboxByID(driver, "Email", EmailAddress);
		
		log.info("Register - Step 07: Click to checkbox 'Newsletter'");
		registerPage.clickToCheckboxByLabel(driver, "Newsletter");
		
		log.info("Register - Step 08: Enter to Password textbox with value is " + password);
		registerPage.inputToTextboxByID(driver, "Password", password);
		
		
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);
		
		log.info("Register - Step 09: Click to 'Register' buttom");
		registerPage.clickToButtonByText(driver, "Register");
		
		log.info("Register - Step 10: Verify Register success message.");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}
	
	@Test
	public void User_02_Login() {
		log.info("Login - Step 01: Navigate to Login page");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login - Step 02: Enter to Email address textbox with value is " + EmailAddress);
		loginPage.inputToEmailTextbox(EmailAddress);
		
		log.info("Login - Step 03: Enter to Password textbox with value is " + password);
		loginPage.inputToPasswordTextbox(password);

		log.info("Login - Step 04: Click to Login button");
		loginPage.clickToButtonByText(driver, "Log in");
		loginPage= PageGeneratorManager.getUserLoginPage(driver);
		
		log.info("Login - Step 05: Verify Login page link");
		Assert.assertEquals(loginPage.getCurrentURL(driver), "https://Facebook.com");
	}
	

	@Test
	public void User_03_My_Account() {
		myAccountPage = homePage.clickToMyAccountLink();
		
		log.info("Login - Step 01: Verify MyAccount page link");
		Assert.assertTrue(myAccountPage.isMyAccountPageDisplayed());
		
		log.info("Login - Step 02: Verify Firstname value is correct.");
		Assert.assertEquals(myAccountPage.getTextboxValueByID(driver, "FirstName"), firstName);
		
		log.info("Login - Step 03: Verify Lastname value is correct.");
		Assert.assertEquals(myAccountPage.getTextboxValueByID(driver, "LastName"), lastName);
	
		log.info("Login - Step 04: Verify Email value is correct.");
		Assert.assertEquals(myAccountPage.getTextboxValueByID(driver, "Email"), EmailAddress);
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
