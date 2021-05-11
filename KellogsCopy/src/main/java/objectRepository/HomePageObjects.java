package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObjects {
	WebDriver driverObj;

	public HomePageObjects(WebDriver driverObj) {
		// TODO Auto-generated constructor stub
		this.driverObj = driverObj;
		PageFactory.initElements(driverObj, this);
	}

	@FindBy(id = "shareFacebook")
	WebElement facebookSharingBtn;
	@FindBy(id = "shareTwitter")
	WebElement twitterSharingBtn;
	@FindBy(xpath="//input[@name='session[username_or_email]']")
	WebElement twitterUserNameField;
	@FindBy(xpath="//input[@name='session[password]']")
	WebElement twitterPasswordField;
	@FindBy(id="")
	WebElement facebookUserNameField;
	@FindBy(id="pass")
	WebElement facebookPasswordField;
	@FindBy(xpath = "//input[@name='login']")
	WebElement facebookLoginBtn;
	@FindBy(xpath = "//span[text()='Log in']")
	WebElement twitterLoginBtn;
	

	public WebElement sendObjectFacebookSharingBtn() {
		return facebookSharingBtn;
	}

	public WebElement sendObjectTwitterUserNameField() {
		return twitterUserNameField;
	}
	
	public WebElement sendObjectTwitterPasswordField() {
		return twitterPasswordField;
	}
	
	public WebElement sendObjectFacebookUserNameField() {
		return facebookUserNameField;
	}
	
	public WebElement sendObjectFacebookPasswordField() {
		return facebookPasswordField;
	}
	
	
	public WebElement sendObjectTwitterSharingBtn() {
		return twitterSharingBtn;
	}
	
	public WebElement sendObjectFacebookLoginBtn() {
		return facebookLoginBtn;
	}
	
	public WebElement sendObjectTwitterLoginBtn() {
		return facebookLoginBtn;
	}
	
}
