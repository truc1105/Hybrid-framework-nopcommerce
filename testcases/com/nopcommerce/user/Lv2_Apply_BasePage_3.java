package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Lv2_Apply_BasePage_3 extends BasePage{
	WebDriver driver;
	String EmailAddress;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		EmailAddress = "AutomationFC_" + generateFakeNumber() + "@gmail.com";
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");
		
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");
		
		clickToElement(driver, "//input[@id='gender-female']");
		sendkeysToElement(driver, "//input[@id='FirstName']", "Layla");
		sendkeysToElement(driver, "//input[@id='LastName']", "Lyle");
		sendkeysToElement(driver, "//input[@id='Email']", "LaylaLyle");
		
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}
	
	@Test
	public void TC_03_Register_Success() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");
		
		clickToElement(driver, "//input[@id='gender-female']");
		sendkeysToElement(driver, "//input[@id='FirstName']", "Layla");
		sendkeysToElement(driver, "//input[@id='LastName']", "Lyle");
		sendkeysToElement(driver, "//input[@id='Email']", EmailAddress);
		sendkeysToElement(driver, "//input[@id='Password']", "a123456789");
		sendkeysToElement(driver, "//input[@id='ConfirmPassword']", "a123456789");
		
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
	}

	@Test
	public void TC_04_Register_Existing_Email() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");

		clickToElement(driver, "//input[@id='gender-female']");
		sendkeysToElement(driver, "//input[@id='FirstName']", "Layla");
		sendkeysToElement(driver, "//input[@id='LastName']", "Lyle");
		sendkeysToElement(driver, "//input[@id='Email']", EmailAddress);
		sendkeysToElement(driver, "//input[@id='Password']", "a123456789");
		sendkeysToElement(driver, "//input[@id='ConfirmPassword']", "a123456789");

		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");

	}
	
	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");
		
		clickToElement(driver, "//input[@id='gender-female']");
		sendkeysToElement(driver, "//input[@id='FirstName']", "Layla");
		sendkeysToElement(driver, "//input[@id='LastName']", "Lyle");
		sendkeysToElement(driver, "//input[@id='Email']", EmailAddress);
		sendkeysToElement(driver, "//input[@id='Password']", "a123");
		sendkeysToElement(driver, "//input[@id='ConfirmPassword']", "a123");
		
		//driver.findElement(By.cssSelector("button#register-button")).click();
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
	}
	
	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");

		clickToElement(driver, "//input[@id='gender-female']");
		sendkeysToElement(driver, "//input[@id='FirstName']", "Layla");
		sendkeysToElement(driver, "//input[@id='LastName']", "Lyle");
		sendkeysToElement(driver, "//input[@id='Email']", EmailAddress);
		sendkeysToElement(driver, "//input[@id='Password']", "a123456789");
		sendkeysToElement(driver, "//input[@id='ConfirmPassword']", "a123456987");

		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
