package com.nopcommerce.common;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import reportConfig.ExtentTestManager;

public class Common_01_Register_End_User extends BaseTest{
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private String firstName, lastName; 
	public static String EmailAddress, password;

	
	@Parameters("browser")
	@BeforeTest(description = "Create new common User for all class test")
	public void Register(String browserName) {
		driver = getBrowserDriver(browserName);
		
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		firstName = "Automation";
		lastName = "Test";
		EmailAddress = "AutomationFC_" + generateFakeNumber() + "@gmail.com";
		password = "123456789";
		
		ExtentTestManager.startTest("Common Class", "Register to system with valid email and password.");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register' page");
		registerPage = homePage.clickToRegisterLink();
		
		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 02: Enter to First name textbox with value is " + firstName);
		registerPage.inputToFirstnameTextbox(firstName);
		
		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 03: Enter to Last name textbox with value is " + lastName);
		registerPage.inputToLastnameTextbox(lastName);
		
		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 04: Enter to Email address textbox with value is " + EmailAddress);
		registerPage.inputToEmailTextbox(EmailAddress);
		
		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 05: Enter to Password textbox with value is " + password);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 07: Click to 'Register' buttom");
		registerPage.clickToRegisterButton();
		
		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 08: Verify Register success message.");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	
		driver.quit();
	}
	
	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
