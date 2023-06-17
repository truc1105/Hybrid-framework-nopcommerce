package pageUIs.wordpress;

public class AdminPostSearchPageUI {
	public static final String ADD_NEW_BUTTON = "xpath=//h1/following-sibling::a[text()='Add New']";
	public static final String SEARCH_POST_TEXTBOX = "css=input#post-search-input";
	public static final String SEARCH_POST_BUTTON = "css=input#search-submit";
	public static final String POST_SEARCH_TABLE_DISPLAYED = "xpath=//td[@data-colname='%s']";
	public static final String POST_TITLE_TEXT = "xpath=//a[text()='%s']";
	
	public static final String CHECKBOX_FOR_ACTION_BY_TITLE = "xpath=//a[text()='%s']/ancestor::td/preceding-sibling::th/input";
	public static final String DROPDOWN_FOR_BULK_ACTION = "css=select#bulk-action-selector-top";
	public static final String APPLY_BUTTON = "css=input#doaction";
	public static final String MOVED_TO_TRASH_SUCCESS_MESSAGE = "xpath=//div[@id='message']/p[contains(text(),'%s')]";
	public static final String NO_POSTS_FOUND_MESSAGE = "xpath=//td[text()='%s']";
}
