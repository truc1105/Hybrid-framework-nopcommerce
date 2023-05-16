package com.jquery;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jquery.dataTable.HomePageObject;
import pageObjects.jquery.dataTable.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyAccountPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewsPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointsPageObject;

public class Lv10_DataTable_DataGrid extends BaseTest{
	private WebDriver driver;
	HomePageObject homePage;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	
	public void Table_01_Paging() {
		homePage.openPagingByPageNumber("10");
		homePage.sleepInSecond(2);
		Assert.assertTrue(homePage.isPageNumberActive("10"));
		
		homePage.openPagingByPageNumber("20");
		homePage.sleepInSecond(2);
		Assert.assertTrue(homePage.isPageNumberActive("20"));
		
		homePage.openPagingByPageNumber("5");
		Assert.assertTrue(homePage.isPageNumberActive("5"));
	}

	
	public void Table_02_Enter_To_Header() {
		homePage.refreshCurrentPage(driver);
		
		homePage.enterToHeaderTextboxByLabel("Country", "Aruba");
		homePage.enterToHeaderTextboxByLabel("Females", "750");

		homePage.sleepInSecond(2);
	}
	
	
	public void Table_03_Get_All_Value_Of_Table() {
		homePage.getValueEachRowAtAllPage();
	}
	
	@Test
	public void Table_04_() {
		// Value de nhap du lieu - tham so 1
		// Row number: tai row nao
		// Ex: Nhap vao textbox tai dong so 3/5/...
		// Column name: Album/ Artist/ Year/...
		homePage.enterToTextboxByColumnNameAtRowNumber("Company", "1", "Welch LLC");
		homePage.enterToTextboxByColumnNameAtRowNumber("Contact Person", "1", "Monique Zebedee");

		homePage.selectDropdownByColumnNameAtRowNumber("Country", "1", "Taiwan");
		homePage.sleepInSecond(2);
		homePage.selectDropdownByColumnNameAtRowNumber("Country", "1", "Japan");
		
		homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?", "1");
		homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?", "3");
		homePage.sleepInSecond(2);
		
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("NPO?", "1");
	
		homePage.clickToIconByRowNumber("1", "Insert Row Above");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("2", "Remove Current Row");

	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
