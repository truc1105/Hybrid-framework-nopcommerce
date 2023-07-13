package com.wordpress.admin;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.wordpress.AdminDashboardPO;
import pageObjects.wordpress.AdminLoginPO;
import pageObjects.wordpress.AdminPostAddNewPO;
import pageObjects.wordpress.AdminPostSearchPO;
import pageObjects.wordpress.PageGeneratorManagerWP;
import pageObjects.wordpress.UserHomePO;
import pageObjects.wordpress.UserPostDetailPO;
import pageObjects.wordpress.UserSearchPostPO;

public class Post_01_Create_Read_Update_Delete_Search extends BaseTest{
	private WebDriver driver;
	String adminUsername = "automationfc";
	String adminPassword = "automationfc";
	String searchPostUrl;
	int randomNumber = generateFakeNumber();
	String postTitle = "Live Coding Title " + randomNumber;
	String postBody = "Live Coding Body " + randomNumber;
	String editPostTitle = "Edit Title " + randomNumber;
	String editPostBody = "Edit Body " + randomNumber;
	String authorName = "automationfc";
	String adminUrl, endUserUrl;
	String currentDay = getCurrentDay();
	
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPage;
	private AdminPostSearchPO adminPostSearchPage;
	private AdminPostAddNewPO adminPostAddNewPage;
	private UserHomePO userHomePage;
	private UserPostDetailPO userPostDetailPage;
	private UserSearchPostPO userSearchPostPage;
	
	@Parameters({"browser", "urlAdmin", "urlUser"})
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String endUserUrl) {
		log.info("Pre-Condition - Step 01: Open browser and Admin site");
		this.adminUrl = adminUrl;
		this.endUserUrl = endUserUrl;
		driver = getBrowserDriverURL(browserName, this.adminUrl);
		adminLoginPage = PageGeneratorManagerWP.getAdminLoginPage(driver);
		
		log.info("Pre-Condition - Step 02: Enter to Username textbox with value: " + adminUsername);
		adminLoginPage.enterToUsernameTextbox(adminUsername);
		
		log.info("Pre-Condition - Step 03: Enter to Password textbox with value: " + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);
		
		log.info("Pre-Condition - Step 04: Click to Login button");
		adminDashboardPage =  adminLoginPage.clickToLoginButton();
	}

	@Test
	public void Post_01_Create_New_Post() {
		
		  log.info("Create_Post - Step 01: Click to 'Posts' menu link"); 
		  adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		  
		  log.info("Create_Post - Step 02: Get 'Search Posts' page url");
		  searchPostUrl= adminPostSearchPage.getCurrentURL(driver);
		  
		  log.info("Create_Post - Step 03: Click to 'Add New' button");
		  adminPostAddNewPage = adminPostSearchPage.clickToAddNewButton();
		  
		  log.info("Create_Post - Step 04: Enter to post title");
		  adminPostAddNewPage.enterToPostTitle(postTitle);
		  
		  log.info("Create_Post - Step 05: Enter to body");
		  adminPostAddNewPage.enterToPostBody(postBody);
		  
		  log.info("Create_Post - Step 06: Click to 'Publish' button");
		  adminPostAddNewPage.clickToPublishOrUpdateButton();
		  
		  log.info("Create_Post - Step 07: Click to 'Pre-Publish' button");
		  adminPostAddNewPage.clickToPrePublishButton();
		  
		  log.info("Create_Post - Step 08: Verify 'Post published' message is displayed");
		  verifyTrue(adminPostAddNewPage.isPostPublishOrUpdateMessageDisplayed("Post published."));
	}
	
	@Test
	public void Post_02_Search_Post_And_View_Post() {
		log.info("Search_Post - Step 01: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);
		
		log.info("Search_Post - Step 02: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitle);
		
		log.info("Search_Post - Step 03: Click to 'Search Post' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Search_Post - Step 04: Verify Search table contains: " + postTitle);
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("Title", postTitle));
		
		log.info("Search_Post - Step 05: Verify Search table contains: " + authorName);
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("Author", authorName));
		
		log.info("Search_Post - Step 06: Open End User site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Search_Post - Step 07: Verify Post info displayed at Home page");
		verifyTrue(userHomePage.isPostInfoDisplayedWithPostTitle(postTitle));
		verifyTrue(userHomePage.isPostInfoDisplayedWithPostBody(postTitle, postBody));
		verifyTrue(userHomePage.isPostInfoDisplayedWithPostAuthor(postTitle, authorName));
		verifyTrue(userHomePage.isPostInfoDisplayedWithPostCurrentDay(postTitle, currentDay));
		
		log.info("Search_Post - Step 08: Click to Post title");
		userPostDetailPage = userHomePage.clickToPostTitle(postTitle);
		
		log.info("Search_Post - Step 09: Verify Post info displayed at Post Detail page");
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithPostTitle(postTitle));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithPostBody(postTitle, postBody));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithPostAuthor(postTitle, authorName));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithPostCurrentDay(postTitle, currentDay));
	}
	
	
	@Test
	public void Post_03_Edit_Post() {
		log.info("Edit_Post - Step 01: Open admin site");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);
		
		log.info("Edit_Post - Step 02: Click to 'Posts' menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		log.info("Edit_Post - Step 03: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitle);
		
		log.info("Edit_Post - Step 04: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Edit_Post - Step 05: Click to Post title link and navigate to Edit Post page");
		adminPostAddNewPage = adminPostSearchPage.clickToPostTitleLink(postTitle);
		
		log.info("Edit_Post - Step 06: Edit post title");
		adminPostAddNewPage.enterToPostTitle(editPostTitle);
		  
		log.info("Edit_Post - Step 07: Edit post body");
		adminPostAddNewPage.enterToPostBody(editPostBody);
		
		log.info("Edit_Post - Step 08: Click to 'Update' button");
		adminPostAddNewPage.clickToPublishOrUpdateButton();
		
		log.info("Edit_Post - Step 09: Verify 'Post updated.' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishOrUpdateMessageDisplayed("Post updated."));
		
		// Quay lai Page Search Post de verify lai
		
		log.info("Search_Post - Step 01: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);
		
		log.info("Search_Post - Step 02: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);
		
		log.info("Search_Post - Step 03: Click to 'Search Post' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Search_Post - Step 04: Verify Search table contains: " + editPostTitle);
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("Title", editPostTitle));
		
		log.info("Search_Post - Step 05: Verify Search table contains: " + authorName);
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("Author", authorName));
		
		log.info("Search_Post - Step 06: Open End User site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Search_Post - Step 07: Verify Post info displayed at Home page");
		verifyTrue(userHomePage.isPostInfoDisplayedWithPostTitle(editPostTitle));
		verifyTrue(userHomePage.isPostInfoDisplayedWithPostBody(editPostTitle, editPostBody));
		verifyTrue(userHomePage.isPostInfoDisplayedWithPostAuthor(editPostTitle, authorName));
		verifyTrue(userHomePage.isPostInfoDisplayedWithPostCurrentDay(editPostTitle, currentDay));
		
		log.info("Search_Post - Step 08: Click to Post title");
		userPostDetailPage = userHomePage.clickToPostTitle(editPostTitle);
		
		log.info("Search_Post - Step 09: Verify Post info displayed at Post Detail page");
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithPostTitle(editPostTitle));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithPostBody(editPostTitle, editPostBody));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithPostAuthor(editPostTitle, authorName));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithPostCurrentDay(editPostTitle, currentDay));
	}
	
	@Test
	public void Post_04_Delete_Post() {
		log.info("Delete_Post - Step 01: Open admin site");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);
		
		log.info("Delete_Post - Step 02: Click to 'Posts' menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		log.info("Delete_Post - Step 03: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);
		
		log.info("Delete_Post - Step 04: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Delete_Post - Step 05: Select Post detail checkbox");
		adminPostSearchPage.selectPostCheckByTitle(editPostTitle);
		
		log.info("Delete_Post - Step 06: Select 'Move to trash' item in dropdown");
		adminPostSearchPage.selectTextItemInActionDropdown("Move to Trash");
		
		log.info("Delete_Post - Step 07: Click to 'Apply' button");
		adminPostSearchPage.clickToApplyButton();
		
		log.info("Delete_Post - Step 08: Verify '1 post moved to the Trash.' message is displayed");
		verifyTrue(adminPostSearchPage.isMoveToTrashMessageDisplayed("1 post moved to the Trash."));
		
		log.info("Delete_Post - Step 09: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);
		
		log.info("Delete_Post - Step 10: Click to 'Search Post' button");
		adminPostSearchPage.clickToSearchPostButton();
	
		log.info("Delete_Post - Step 11: Verify 'No posts found.' message is displayed");
		verifyTrue(adminPostSearchPage.isNoPostFoundMessageDisplayed("No posts found."));
		
		log.info("Delete_Post - Step 12: Open End User site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
	
		log.info("Delete_Post - Step 13: Verify Post title undisplayed at Home page");
		verifyTrue(userHomePage.isPostInfoUndisplayedWithPostTitle(editPostTitle));
		
		log.info("Delete_Post - Step 14: Enter to Search textbox");
		userHomePage.enterToSearchTextbox(editPostTitle);
		
		log.info("Delete_Post - Step 15: Click to 'Search Post' button");
		userSearchPostPage = userHomePage.clickToSearchButton();
		
		log.info("Delete_Post - Step 16: Verify 'Nothing Found' message is displayed");
		verifyTrue(userSearchPostPage.isNothingFoundMessageDisplayed("Nothing Found"));
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
