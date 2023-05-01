package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class LoginPageObject extends BasePageFactory{
	private WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//-------------------------------------- Page UI
	@FindBy(xpath = "//button[@class='button-1 login-button']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement emailErrorMessage;
	
	@FindBy(id = "Email")
	private WebElement emailTextbox;
	
	@FindBy(xpath = "//input[@class='email']")
	private WebElement passwordTextbox;
	
	@FindBy(xpath = "//div[contains(@class,'validation-summary-errors')]")
	private WebElement unsuccessfulLoginMessage;
	
	//-------------------------------------------- Page Object/ Action
	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
	}
	
	public String getEmailErrorMessageText() {
		return getElementText(driver, emailErrorMessage);
	}
	
	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, emailTextbox);
		sendkeysToElement(driver, emailTextbox, email);
	}
	
	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeysToElement(driver, passwordTextbox, password);
	}
	
	public String getUnsuccessfulErrorMessageText() {
		return getElementText(driver, unsuccessfulLoginMessage);
	}
}
