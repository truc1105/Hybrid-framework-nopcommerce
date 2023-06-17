package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminPostAddNewPageUI;

public class AdminPostAddNewPO extends BasePage{
	WebDriver driver;
	
	public AdminPostAddNewPO(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToPostTitle(String postTitleValue) {
		waitForElementVisible(driver, AdminPostAddNewPageUI.TITLE_VALUE);
		sendkeysToElement(driver, AdminPostAddNewPageUI.TITLE_VALUE, postTitleValue);
	}

	public void enterToPostBody(String postBodyValue) {
		waitForElementClickable(driver, AdminPostAddNewPageUI.BODY_VALUE);
		clickToElement(driver, AdminPostAddNewPageUI.BODY_VALUE);
		
		clearValueInElementByDeleteKey(driver, AdminPostAddNewPageUI.BODY_VALUE_AFTER_CLICK);
		
		sendkeysToElement(driver, AdminPostAddNewPageUI.BODY_VALUE_AFTER_CLICK, postBodyValue);
	}

	public void clickToPublishOrUpdateButton() {
		waitForElementClickable(driver, AdminPostAddNewPageUI.PUBLISH_OR_UPDATE_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.PUBLISH_OR_UPDATE_BUTTON);
	}

	public AdminPostSearchPO openSearchPostPageUrl(String searchPostUrl) {
		openPageURL(driver, searchPostUrl);
		return PageGeneratorManagerWP.getAdminPostSearchPage(driver);
	}

	public boolean isPostPublishOrUpdateMessageDisplayed(String postPublishMessage) {
		waitForElementVisible(driver, AdminPostAddNewPageUI.PUBLISH_OR_UPDATED_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, AdminPostAddNewPageUI.PUBLISH_OR_UPDATED_SUCCESS_MESSAGE, postPublishMessage);
	}

	public void clickToPrePublishButton() {
		waitForElementClickable(driver, AdminPostAddNewPageUI.AFTER_PUBLISH_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.AFTER_PUBLISH_BUTTON);
	}

}
