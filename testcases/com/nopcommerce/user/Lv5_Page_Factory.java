package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageFactory.HomePageObject;
import pageFactory.LoginPageObject;
import pageFactory.RegisterPageObject;

public class Lv5_Page_Factory {
	private WebDriver driver;
	private String firstName, lastName, EmailAddress, password, invalidEmail, emailNotFound;
	private String projectPath = System.getProperty("user.dir");
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("https://demo.nopcommerce.com/");
		homePage = new HomePageObject(driver);
		
		firstName = "Automation";
		lastName = "Test";
		EmailAddress = "AutomationFC_" + generateFakeNumber() + "@gmail.com";
		password = "123456789";
		
		invalidEmail = "1234";
		emailNotFound = "AutomationFC_" + generateFakeNumber() + "@gmail.com";
		
		registerPage = new RegisterPageObject(driver);
		homePage.clickToRegisterLink();
		
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
		loginPage = new LoginPageObject(driver);
		homePage.clickToLoginLink();
		
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getEmailErrorMessageText(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		homePage.clickToLoginLink();
		
		loginPage.inputToEmailTextbox(invalidEmail);
		
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getEmailErrorMessageText(), "Wrong email");
	}
	
	@Test
	public void Login_03_Email_Not_Found() {
		homePage.clickToLoginLink();

		loginPage.inputToEmailTextbox(emailNotFound);
		loginPage.inputToPasswordTextbox(password);

		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessfulErrorMessageText(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Existing_Email_No_Password() {
		homePage.clickToLoginLink();

		loginPage.inputToEmailTextbox(EmailAddress);
		loginPage.inputToPasswordTextbox("");

		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessfulErrorMessageText(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_05_Existing_Email_Wrong_Password() {
		homePage.clickToLoginLink();

		loginPage.inputToEmailTextbox(EmailAddress);
		loginPage.inputToPasswordTextbox("1234567");

		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessfulErrorMessageText(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Register_06_Login_Success() {
		homePage.clickToLoginLink();

		loginPage.inputToEmailTextbox(EmailAddress);
		loginPage.inputToPasswordTextbox(password);

		loginPage.clickToLoginButton();
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
