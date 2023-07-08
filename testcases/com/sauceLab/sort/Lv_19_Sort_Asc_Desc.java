package com.sauceLab.sort;

import static org.testng.Assert.assertEquals;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.sauceLab.sort.LoginPageObject;
import pageObjects.sauceLab.sort.PageGeneratorManager;
import pageObjects.sauceLab.sort.ProductPageObject;

public class Lv_19_Sort_Asc_Desc extends BaseTest{
	private WebDriver driver;
	
	private LoginPageObject loginPage;
	private ProductPageObject productPage;
	
	private String username = "standard_user";
	private String password = "secret_sauce";

	
	@Parameters({"browser", "appUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		
		log.info("Pre-Condition - Step 01: Open browser and Login page");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		log.info("Pre-Condition - Step 02: Enter to Username textbox with value: " + username);
		loginPage.enterToUsernameTextbox(username);
		
		log.info("Pre-Condition - Step 03: Enter to Username textbox with value: " + password);
		loginPage.enterToPasswordTextbox(password);
		
		log.info("Pre-Condition - Step 04: Click to 'Login' button");
		productPage = loginPage.clickToLoginButton();
		
	}

	@Test
	public void Sort_01_Name() {
		//Asc
		productPage.selectItemInProductSortDropdown("Name (A to Z)");
		
		Assert.assertTrue(productPage.isProductNameSortByAscending());
		
		//Desc
		productPage.selectItemInProductSortDropdown("Name (Z to A)");
		Assert.assertTrue(productPage.isProductNameSortByDescending());
	}
	
	@Test
	public void Sort_02_Price() {
		//Asc
		productPage.selectItemInProductSortDropdown("Price (low to high)");
		Assert.assertTrue(productPage.isProductPriceSortByAscending());	
		
		//Desc
		productPage.selectItemInProductSortDropdown("Price (high to low)");
		Assert.assertTrue(productPage.isProductPriceSortByDescending());
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
