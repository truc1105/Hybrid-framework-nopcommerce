package pageUIs.wordpress;

public class AdminPostAddNewPageUI {
	public static final String TITLE_VALUE = "css=h1.wp-block";
	public static final String BODY_VALUE = "css=p.block-editor-default-block-appender__content";
	public static final String BODY_VALUE_AFTER_CLICK = "xpath=//p[@role='document']";
	public static final String PUBLISH_OR_UPDATE_BUTTON = "css=div[aria-label='Editor top bar'] button[class*='editor-post-publish-button']";
	public static final String AFTER_PUBLISH_BUTTON = "css=div[aria-label='Editor publish'] button[class*='editor-post-publish-button']";
	public static final String PUBLISH_OR_UPDATED_SUCCESS_MESSAGE = "css=div.components-snackbar__content";
}
