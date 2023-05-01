package pageObjects.facebook;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.facebook.LoginPageUI;

public class LoginPageObject extends BasePage{
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToCreateNewAccountButton() {
		waitForElementClickable(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
		clickToElement(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
	}

	public boolean isEmailAddressTextboxDisplayed() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		return isElementDisplayed(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
	}

	public boolean isConfirmEmailAddressTextboxDisplayed() {
		return isElementDisplayed(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
	}

	public void enterToEmailTextbox(String email) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendkeysToElement(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX, email);
	}

	public void clickToClosePopupSignUp() {
		waitForElementClickable(driver, LoginPageUI.SIGN_UP_CLOSE_BUTTON);
		clickToElement(driver, LoginPageUI.SIGN_UP_CLOSE_BUTTON);
	}

	public boolean isConfirmEmailAddressTextboxUndisplayed() {	
		return isElementUndisplayed(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
	}
}
