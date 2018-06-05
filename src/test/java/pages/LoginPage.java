package pages;

import org.fest.assertions.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import util.PageAction;

public class LoginPage {
    private PageAction action;


    @FindBy(how = How.NAME, using = "userName")
    private WebElement usernameField;

    @FindBy(how = How.NAME, using = "password")
    private WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//div[@class='content-container']")
    private WebElement contentContainer;

    @FindBy(how = How.XPATH, using = "//ul[@class='logout-items']")
    private WebElement logoutIcon;

    @FindBy(how = How.XPATH, using = "//table[@class='loginform']")
    private WebElement loginForm;

    @FindBy(how = How.XPATH, using = "//a[@title='Deutsch']")
    private WebElement germanLanguageLink;

    @FindBy(how = How.XPATH, using = "//a[@title='English']")
    private WebElement englishLanguageLink;


    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        action = new PageAction(driver);
    }

    public void enterUsername(String username) {
        action.typeText(usernameField, username);
    }

    public void enterPassword(String username) {
        action.typeText(passwordField, username);
    }

    public String getContentContainerText() {
        return contentContainer.getText();
    }


    public void gotoLoginPage() {
        action.gotoPage("https://cockpit.eqs.com/ssl_e.html", "");
    }

    public void pressLoginButton() {
        action.clickLinkByText("Login");

    }

    public boolean isLogoutDisplayed() {
        return logoutIcon.isDisplayed();
    }

    public String getLoginFormText() {
        return action.getText(loginForm);
    }

    public void clickLinkByText(String text) {
        action.clickLinkByText(text);
    }


    public void switchToGerman() {
        action.clickButton(germanLanguageLink);
    }

    public void switchToEnglish() {
        action.clickButton(englishLanguageLink);
    }
}
