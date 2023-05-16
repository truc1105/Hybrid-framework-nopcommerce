package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.common.Common_01_Register_End_User;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyAccountPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import reportConfig.ExtentTestManager;

public class Lv_16_Share_Data extends BaseTest{
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private String EmailAddress, password;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		EmailAddress = Common_01_Register_End_User.EmailAddress;
		password = Common_01_Register_End_User.password;
		
		homePage = PageGeneratorManager.getUserHomePage(driver);
	
		ExtentTestManager.getTest().log(Status.INFO,"Login - Step 01: Navigate to Login page");
		loginPage = homePage.clickToLoginLink();
		
		ExtentTestManager.getTest().log(Status.INFO,"Login - Step 02: Enter to Email address textbox with value is " + EmailAddress);
		loginPage.inputToEmailTextbox(EmailAddress);
		
		ExtentTestManager.getTest().log(Status.INFO,"Login - Step 03: Enter to Password textbox with value is " + password);
		loginPage.inputToPasswordTextbox(password);

		homePage = loginPage.clickToLoginButton();
		
		ExtentTestManager.getTest().log(Status.INFO,"Login - Step 05: Verify Login page link");
		Assert.assertEquals(loginPage.getCurrentURL(driver), "https://demo.nopcommerce.com/");
	}
	
	@Test
	public void Search_01_Empty_Data() {
		
	}
	
	@Test
	public void Search_02_Relative_Product_Name() {
		
	}
	
	@Test
	public void Search_03_Absolute_Product_Name() {
		
	}
	
	@Test
	public void Search_04_Parent_Category() {
		
	}
	
	@Test
	public void Search_05_Incorrect_Manuafactorer() {
		
	}
	
	@Test
	public void Search_06_Correct_Manufactorer() {
		
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
