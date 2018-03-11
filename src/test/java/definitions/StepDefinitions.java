package definitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.Page;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class StepDefinitions {
    private static final Logger LOGGER = LoggerFactory.getLogger(StepDefinitions.class);
    //Drivers location
    private static final String CHROME_DRIVER_LOCATION = "C:\\webdrivers\\chromedriver.exe";
    private static final String FIREFOX_DRIVER_LOCATION = "C:\\webdrivers\\geckodriver.exe";
    private static final String IE_DRIVER_LOCATION = "C:\\webdrivers\\IEDriverServer.exe";
    private static final String CHROME_PROPERTY = "webdriver.chrome.driver";
    private static final String FIREFOX_PROPERTY = "webdriver.gecko.driver";
    private static final String IE_PROPERTY = "webdriver.ie.driver";
    //Driver
    WebDriver driver;
    private Page page;


    private void startBrowser(String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty(FIREFOX_PROPERTY, FIREFOX_DRIVER_LOCATION);
            driver = new FirefoxDriver();
            configureBrowser(browser);
        }
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty(CHROME_PROPERTY, CHROME_DRIVER_LOCATION);
            driver = new ChromeDriver();
            configureBrowser(browser);
        }
        if (browser.equalsIgnoreCase("ie")) {
            System.setProperty(IE_PROPERTY, IE_DRIVER_LOCATION);
            driver = new InternetExplorerDriver();
            configureBrowser(browser);
        }

    }

    private void configureBrowser(String browser) {
        LOGGER.info("Starting browser:" + browser);
        driver.manage().deleteAllCookies(); //delete cookies
        driver.manage().window().maximize(); //To maximize browser
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);   //Implicit wait
    }


    @Before
    public void before() {
        startBrowser("firefox");
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File src = screenshot.getScreenshotAs(OutputType.FILE); //capture screenshot
                String fileName = scenario.getName() + ".png";
                FileUtils.copyFile(src, new File("E:\\screenshots\\" + fileName)); //copy file to location
                LOGGER.info("Successfully captured a screenshot");
                LOGGER.info("Stored image:" + fileName + " at:" + "E:\\screenshots\\");
            } catch (Exception e) {
                LOGGER.info("Exception while taking screenshot " + e.getMessage());
            }
        }
        driver.quit();
    }

    @When("^I search for text:\"(.*)\"$")
    public void iSearchText(String text) {
        page.search(text);
    }

    @Given("^I hit enter from the keyboard$")
    public void hitKeyboardEnter() {
        page.hitEnter();
    }

    @Given("^posts should be visible$")
    public void postsShouldBeVisible() {
        page.verifyPostContainerDisplayed();
    }


    @Given("^user is at home page$")
    public void openHomePage() {
        page = new Page(driver);
        page.gotoPage();
    }



}
