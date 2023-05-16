package com.jquery;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jquery.uploadFiles.HomePageObject;
import pageObjects.jquery.uploadFiles.PageGeneratorManager;


public class Lv11_Upload_Files extends BaseTest{
	private WebDriver driver;
	HomePageObject homePage;
	
	String imageUpl = "imageUpl.png";
	String imageUpl_1 = "imageUpl1.jpg";
	String imageUpl_2 = "imageUpl2.png";
	
	String[] multipleFileNames = {imageUpl, imageUpl_1, imageUpl_2};
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		
		homePage = PageGeneratorManager.getHomePage(driver);
	}
	
	@Test
	public void Upload_01_One_File_Per_Time() {
		homePage.uploadMultipleFiles(driver, imageUpl);
		Assert.assertTrue(homePage.isFileLoadingByName(imageUpl));
		homePage.clickToStartButton();
		
		Assert.assertTrue(homePage.isFileLinkUploadedByName(imageUpl));
		Assert.assertTrue(homePage.isFileImageUploadedByName(imageUpl));
	}

	@Test
	public void Upload_02_Multiple_File_Per_Time() {
		homePage.refreshCurrentPage(driver);
		
		homePage.uploadMultipleFiles(driver, multipleFileNames);
		Assert.assertTrue(homePage.isFileLoadingByName(imageUpl));
		Assert.assertTrue(homePage.isFileLoadingByName(imageUpl_1));
		Assert.assertTrue(homePage.isFileLoadingByName(imageUpl_2));
		
		homePage.clickToStartButton();
		
		Assert.assertTrue(homePage.isFileLinkUploadedByName(imageUpl));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(imageUpl_1));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(imageUpl_2));
		
		Assert.assertTrue(homePage.isFileImageUploadedByName(imageUpl));
		Assert.assertTrue(homePage.isFileImageUploadedByName(imageUpl_1));
		Assert.assertTrue(homePage.isFileImageUploadedByName(imageUpl_2));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
