package pageObjects.sauceLab.sort;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.sauceLab.sort.loginPageUI;


public class LoginPageObject extends BasePage{
	WebDriver driver;
	
	public LoginPageObject (WebDriver driver) {
		this.driver = driver;
	}

	public void enterToUsernameTextbox(String username) {
		waitForElementVisible(driver, loginPageUI.USERNAME_TEXTBOX);
		sendkeysToElement(driver, loginPageUI.USERNAME_TEXTBOX, username);
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, loginPageUI.PASSWORD_TEXTBOX);
		sendkeysToElement(driver, loginPageUI.PASSWORD_TEXTBOX, password);
	}

	public ProductPageObject clickToLoginButton() {
		waitForElementClickable(driver, loginPageUI.LOGIN_BUTTON);
		clickToElement(driver, loginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getProductPage(driver);
	}

}
