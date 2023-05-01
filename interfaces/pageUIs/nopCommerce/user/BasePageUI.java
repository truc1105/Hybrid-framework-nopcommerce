package pageUIs.nopCommerce.user;

public class BasePageUI {
	public static final String ADDRESS_LINK = "Xpath=//li[@class='customer-addresses inactive']/a";
	public static final String MY_PRODUCT_REVIEWS_LINK = "xpath=//li[@class='customer-reviews inactive']/a";
	public static final String REWARD_POINTS_LINK = "xpath=//li[@class='reward-points inactive']/a";
	
	public static final String DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA = "xpath=//div[contains(@class,'account-navigation')]//a[text()='%s']";
	
	public static final String LOGOUT_LINK_AT_USER = "css=a[class='ico-logout']";
	public static final String LOGOUT_LINK_AT_ADMIN = "xpath=//a[text()='Logout']";
}
