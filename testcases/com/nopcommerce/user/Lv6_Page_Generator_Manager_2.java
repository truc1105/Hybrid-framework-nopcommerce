package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Lv6_Page_Generator_Manager_2 extends BaseTest{
	private WebDriver driver;
	private String firstName, lastName, EmailAddress, password, invalidEmail, emailNotFound;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		homePage = new UserHomePageObject(driver);
		
		firstName = "Automation";
		lastName = "Test";
		EmailAddress = "AutomationFC_" + generateFakeNumber() + "@gmail.com";
		password = "123456789";
		
		invalidEmail = "1234";
		emailNotFound = "AutomationFC_" + generateFakeNumber() + "@gmail.com";
		
		
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
	public void Login_01_Empty_Data() {
		
		loginPage = homePage.clickToLoginLink();
		
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getEmailErrorMessageText(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		loginPage = homePage.clickToLoginLink();
		
		loginPage.inputToEmailTextbox(invalidEmail);
		
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getEmailErrorMessageText(), "Wrong email");
	}
	
	@Test
	public void Login_03_Email_Not_Found() {
		loginPage = homePage.clickToLoginLink();
		
		loginPage.inputToEmailTextbox(emailNotFound);
		loginPage.inputToPasswordTextbox(password);

		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessfulErrorMessageText(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Existing_Email_No_Password() {
		loginPage = homePage.clickToLoginLink();

		loginPage.inputToEmailTextbox(EmailAddress);
		loginPage.inputToPasswordTextbox("");

		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessfulErrorMessageText(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_05_Existing_Email_Wrong_Password() {
		loginPage = homePage.clickToLoginLink();

		loginPage.inputToEmailTextbox(EmailAddress);
		loginPage.inputToPasswordTextbox("1234567");

		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessfulErrorMessageText(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Register_06_Login_Success() {
		loginPage = homePage.clickToLoginLink();
		
		loginPage.inputToEmailTextbox(EmailAddress);
		loginPage.inputToPasswordTextbox(password);

		homePage = loginPage.clickToLoginButton();
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
