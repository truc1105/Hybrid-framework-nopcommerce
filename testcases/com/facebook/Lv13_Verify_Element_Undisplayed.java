package com.facebook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;

public class Lv13_Verify_Element_Undisplayed extends BaseTest{
	private WebDriver driver;
	LoginPageObject loginPage;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}
	
	@Test
	public void TC_01_Verify_Element_Displayed() {
		loginPage.clickToCreateNewAccountButton();
		
		verifyTrue(loginPage.isEmailAddressTextboxDisplayed());
	}
	
	@Test
	public void TC_02_Verify_Element_Undisplayed_But_In_DOM() {
		loginPage.enterToEmailTextbox(" ");
		
		//verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
	}
	
	@Test
	public void TC_03_Verify_Element_Undisplayed_Not_In_DOM() {
		loginPage.clickToClosePopupSignUp();
		loginPage.sleepInSecond(2);
		
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
