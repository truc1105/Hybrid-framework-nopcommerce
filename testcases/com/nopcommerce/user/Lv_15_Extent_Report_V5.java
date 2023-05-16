package com.nopcommerce.user;

import java.lang.reflect.Method;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyAccountPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import reportConfig.ExtentTestManager;

public class Lv_15_Extent_Report_V5 extends BaseTest{
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
	public void User_01_Register(Method method) {
		ExtentTestManager.startTest(method.getName(), "Register to system with valid email and password.");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register' page");
		registerPage = homePage.clickToRegisterLink();
		
		ExtentTestManager.getTest().log(Status.PASS,"Register - Step 01.1: Verify navigate to 'Register' page success.");
		Assert.assertEquals(registerPage.getCurrentURL(driver), "https://com.nopcommerce.com/register?returnUrl=%2F");
		
		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 02: Enter to First name textbox with value is " + firstName);
		registerPage.inputToFirstnameTextbox(firstName);
		
		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 03: Enter to Last name textbox with value is " + lastName);
		registerPage.inputToLastnameTextbox(lastName);
		
		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 04: Enter to Email address textbox with value is " + EmailAddress);
		registerPage.inputToEmailTextbox(EmailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 07: Click to 'Register' buttom");
		registerPage.clickToRegisterButton();
		
		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 08: Verify Register success message.");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed..");
	}
	
	@Test
	public void User_02_Login(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login to system with valid email and password.");
		ExtentTestManager.getTest().log(Status.INFO,"Login - Step 01: Navigate to Login page");
		loginPage = homePage.clickToLoginLink();
		
		ExtentTestManager.getTest().log(Status.INFO,"Login - Step 02: Enter to Email address textbox with value is " + EmailAddress);
		loginPage.inputToEmailTextbox(EmailAddress);
		
		ExtentTestManager.getTest().log(Status.INFO,"Login - Step 03: Enter to Password textbox with value is " + password);
		loginPage.inputToPasswordTextbox(password);

		homePage = loginPage.clickToLoginButton();
		
		ExtentTestManager.getTest().log(Status.INFO,"Login - Step 05: Verify Login page link");
		Assert.assertEquals(loginPage.getCurrentURL(driver), "https://Facebook.com");
		
		myAccountPage = homePage.clickToMyAccountLink();
		
		ExtentTestManager.getTest().log(Status.INFO,"Login - Step 07: Verify MyAccount page link");
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
