package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminPostAddNewPageUI;
import pageUIs.wordpress.AdminPostSearchPageUI;

public class AdminPostSearchPO extends BasePage{
	WebDriver driver;
	
	public AdminPostSearchPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public AdminPostAddNewPO clickToAddNewButton() {
		waitForElementClickable(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
		return PageGeneratorManagerWP.getAdminPostAddNewPage(driver);
	}

	public void enterToSearchTextbox(String postTitle) {
		waitForElementVisible(driver, AdminPostSearchPageUI.SEARCH_POST_TEXTBOX);
		sendkeysToElement(driver, AdminPostSearchPageUI.SEARCH_POST_TEXTBOX, postTitle);
	}

	public void clickToSearchPostButton() {
		waitForElementClickable(driver, AdminPostSearchPageUI.SEARCH_POST_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.SEARCH_POST_BUTTON);
	}

	public boolean isPostSearchTableDisplayed(String colName, String value) {
		waitForElementVisible(driver, AdminPostSearchPageUI.POST_SEARCH_TABLE_DISPLAYED, colName);
		return isElementDisplayed(driver, AdminPostSearchPageUI.POST_SEARCH_TABLE_DISPLAYED, colName, value);
	}

	public AdminPostAddNewPO clickToPostTitleLink(String postTitle) {
		waitForElementClickable(driver, AdminPostSearchPageUI.POST_TITLE_TEXT, postTitle);
		clickToElement(driver, AdminPostSearchPageUI.POST_TITLE_TEXT, postTitle);
		return PageGeneratorManagerWP.getAdminPostAddNewPage(driver);
	}

	public void selectPostCheckByTitle(String editPostTitle) {
		waitForElementClickable(driver, AdminPostSearchPageUI.CHECKBOX_FOR_ACTION_BY_TITLE, editPostTitle);
		checkToDefaultCheckboxRadio(driver, AdminPostSearchPageUI.CHECKBOX_FOR_ACTION_BY_TITLE, editPostTitle);
	}

	public void selectTextItemInActionDropdown(String item) {
		waitForElementVisible(driver, AdminPostSearchPageUI.DROPDOWN_FOR_BULK_ACTION);
		selectItemDefaultDropdown(driver, AdminPostSearchPageUI.DROPDOWN_FOR_BULK_ACTION, item);
		
	}

	public void clickToApplyButton() {
		waitForElementClickable(driver, AdminPostSearchPageUI.APPLY_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.APPLY_BUTTON);
	}

	public boolean isMoveToTrashMessageDisplayed(String message) {
		waitForElementVisible(driver, AdminPostSearchPageUI.MOVED_TO_TRASH_SUCCESS_MESSAGE, message);
		return isElementDisplayed(driver, AdminPostSearchPageUI.MOVED_TO_TRASH_SUCCESS_MESSAGE, message);
	}

	public boolean isNoPostFoundMessageDisplayed(String message) {
		waitForElementVisible(driver, AdminPostSearchPageUI.NO_POSTS_FOUND_MESSAGE, message);
		return isElementDisplayed(driver, AdminPostSearchPageUI.NO_POSTS_FOUND_MESSAGE, message);
	}

	

}
