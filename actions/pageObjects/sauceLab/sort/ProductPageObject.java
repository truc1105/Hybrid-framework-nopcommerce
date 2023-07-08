package pageObjects.sauceLab.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.sauceLab.sort.productPageUI;

public class ProductPageObject extends BasePage{
	WebDriver driver;
	
	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInProductSortDropdown(String item) {
		waitForElementVisible(driver, productPageUI.SORT_DROPDOWN);
		selectItemDefaultDropdown(driver, productPageUI.SORT_DROPDOWN, item);
		
	}

	public boolean isProductNameSortByAscending() {
		// khai báo 1 ArrayList để chứa các product name trên UI
		ArrayList<String> productUIList = new ArrayList<String>();
		
		//Lấy ra hết tất cả các text product name
		List<WebElement> productNameText = getListWebElement(driver, productPageUI.PRODUCT_NAME_TEXT);
		
		//dùng vòng lặp để getText và add vào ArrayList trên
		for(WebElement productName : productNameText) {
			productUIList.add(productName.getText());
		}
		
		// tạo ra 1 ArrayList mới để sort dữ liệu trong ArrayList cũ xem đúng hay không
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}
		
		//Sort productSortList
		Collections.sort(productSortList);
		
		//so sánh 2 List đã bằng nhau
		return productSortList.equals(productUIList);
	}

	public boolean isProductNameSortByDescending() {
		// khai báo 1 ArrayList để chứa các product name trên UI
		ArrayList<String> productUIList = new ArrayList<String>();
		
		//Lấy ra hết tất cả các text product name
		List<WebElement> productNameText = getListWebElement(driver, productPageUI.PRODUCT_NAME_TEXT);
		
		//dùng vòng lặp để getText và add vào ArrayList trên
		for(WebElement productName : productNameText) {
			productUIList.add(productName.getText());
		}
		
		// tạo ra 1 ArrayList mới để sort dữ liệu trong ArrayList cũ xem đúng hay không
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}
		
		//Sort productSortList
		Collections.sort(productSortList);
		
		//Reverse productSortList
		Collections.reverse(productSortList);
		
		//so sánh 2 List đã bằng nhau
		return productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortByAscending() {
		// khai báo 1 ArrayList để chứa các product name trên UI
		ArrayList<Float> productUIList = new ArrayList<Float>();

		// Lấy ra hết tất cả các text product price
		List<WebElement> productPriceText = getListWebElement(driver, productPageUI.PRODUCT_PRICE_TEXT);

		// dùng vòng lặp để getText và add vào ArrayList trên
		for (WebElement productPrice : productPriceText) {
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}

		// tạo ra 1 ArrayList mới để sort dữ liệu trong ArrayList cũ xem đúng hay không
		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUIList) {
			productSortList.add(product);
		}

		// Sort productSortList
		Collections.sort(productSortList);

		// so sánh 2 List đã bằng nhau
		return productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortByDescending() {
		// khai báo 1 ArrayList để chứa các product name trên UI
		ArrayList<Float> productUIList = new ArrayList<Float>();

		// Lấy ra hết tất cả các text product name
		List<WebElement> productPriceText = getListWebElement(driver, productPageUI.PRODUCT_PRICE_TEXT);

		// dùng vòng lặp để getText và add vào ArrayList trên
		for (WebElement productPrice : productPriceText) {
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}

		// tạo ra 1 ArrayList mới để sort dữ liệu trong ArrayList cũ xem đúng hay không
		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUIList) {
			productSortList.add(product);
		}

		// Sort productSortList
		Collections.sort(productSortList);

		// Reverse productSortList
		Collections.reverse(productSortList);

		// so sánh 2 List đã bằng nhau
		return productSortList.equals(productUIList);
	}
}
