package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUIs.nopCommerce.user.RegisterPageUI;

public class RegisterPageObject extends BasePageFactory{
	private WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Page UI
	@FindBy(id = "FirstName")
	private WebElement firstNameTextbox;
	
	@FindBy(id = "LastName")
	private WebElement lastNameTextbox;
	
	@FindBy(id = "Email")
	private WebElement emailTextbox;
	
	@FindBy(id = "Password")
	private WebElement passwordTextbox;
	
	@FindBy(id = "ConfirmPassword")
	private WebElement confirmPasswordTextbox;
	
	@FindBy(id = "register-button")
	private WebElement registerButton;
	
	@FindBy(id = "FirstName-error")
	private WebElement firstNameErrorMessage;
	
	@FindBy(id = "LastName-error")
	private WebElement lastNameErrorMessage;
	
	@FindBy(id = "Email-error")
	private WebElement emailErrorMessage;
	
	@FindBy(id = "Password-error")
	private WebElement passwordErrorMessage;
	
	@FindBy(id = "ConfirmPassword-error")
	private WebElement confirmPasswordErrorMessage;
	
	@FindBy(xpath = "//div[@class='result']")
	private WebElement registerSuccessMessage;
	
	@FindBy(xpath = "//a[@class='ico-logout']") 
	private WebElement logoutLink;
	
	@FindBy(xpath = "//div[contains(@class,'message-error')]//li")
	private WebElement existingEmailErrorMessage;
	
	// Page Object/ Action
	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton);
	    clickToElement(driver, registerButton);
	}

	public String getFirstNameErrorMessage() {
		waitForElementVisible( driver, firstNameErrorMessage);
		return getElementText(driver, firstNameErrorMessage);
	}

	public String getLastNameErrorMessage() {
		waitForElementVisible(driver, lastNameErrorMessage);
		return getElementText(driver, lastNameErrorMessage);
	}

	public String getEmailErrorMessage() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	public String getPasswordErrorMessage() {
		waitForElementVisible(driver, passwordErrorMessage);
		return getElementText(driver, passwordErrorMessage);
	}

	public String getConfirmPasswordErrorMessage() {
		waitForElementVisible(driver, confirmPasswordErrorMessage);
		return getElementText(driver, confirmPasswordErrorMessage);
	}

	public void inputToFirstnameTextbox(String firstName) {
		waitForElementVisible(driver, firstNameTextbox);
		sendkeysToElement(driver, firstNameTextbox, firstName);
	}

	public void inputToLastnameTextbox(String lastName) {
		waitForElementVisible(driver, lastNameTextbox);
		sendkeysToElement(driver, lastNameTextbox, lastName);
	}

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, emailTextbox);
		sendkeysToElement(driver, emailTextbox, email);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeysToElement(driver, passwordTextbox, password);
	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, confirmPasswordTextbox);
		sendkeysToElement(driver, confirmPasswordTextbox, confirmPassword);
	}

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, registerSuccessMessage);
		return getElementText(driver, registerSuccessMessage);
	}

	public String getErrorExistingEmailMessage() {
		waitForElementVisible(driver, existingEmailErrorMessage);
		return getElementText(driver, existingEmailErrorMessage);
	}
}
