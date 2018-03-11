package pages;

import org.fest.assertions.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import util.PageAction;

public class Page {
    private PageAction action;


    @FindBy(how = How.ID, using = "searchsubmit")
    private WebElement searchField;

    @FindBy(how = How.ID, using = "okmsg")
    private WebElement addSuccessMessage;

    @FindBy(how = How.XPATH, using = "//div[@class='post_container']")
    private WebElement postContainer;

    public Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
        action = new PageAction(driver);
    }

    public void search(String text) {
        action.typeText(searchField, text);
    }

    public void verifyPostContainerDisplayed(){
        Assertions.assertThat(postContainer.isDisplayed()).as("Posts Displayed").isEqualTo(true);
    }

    public void hitEnter() {
        searchField.sendKeys(Keys.ENTER);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void gotoPage() {
        action.gotoPage("http://pragmatic.bg", "");
    }
}
