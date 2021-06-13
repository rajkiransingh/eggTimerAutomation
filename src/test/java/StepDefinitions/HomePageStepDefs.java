package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.TimerPage;
import setup.TestBase;
import utils.ElapsedTimeFinder;

import java.util.concurrent.TimeUnit;

import static utils.CommonConstants.*;

public class HomePageStepDefs {
    private static final Logger logger = LogManager.getLogger(HomePageStepDefs.class);
    WebDriver driver = null;
    HomePage homepage;
    TimerPage timerPage;
    TestBase testBase;
    ElapsedTimeFinder elapsedTimeFinder;
    Long getCurrentTime;
    Long getLatestTime;



    @Before
    public void startup() {
        logger.info("Starting the browser setup and driver initialization");

        testBase = new TestBase();
        driver = testBase.initializeWebDriver(testBase.getBrowserName());
    }

    @After
    public void teardown() throws InterruptedException {
        logger.info("Closing the browser session in 1 sec");
        TimeUnit.MILLISECONDS.sleep(1000); //seems there is a failure in Firefox when closing a session. So added 1 sec delay.
        driver.close();
        driver.quit();
    }

    @Given("I am on the EggTimer Homepage")
    public void i_am_on_egg_timer_homepage() {
        driver.get(URL);
        homepage = new HomePage(driver);
    }

    @When("^I provide (.*) seconds to the timer$")
    public void i_provide_seconds_to_the_timer(String time) {
        homepage.enterCountDownTime(time);
        EXPECTED_TIME = Integer.parseInt(time);
    }

    @When("^I enter invalid (.*) in seconds to the timer$")
    public void i_enter_invalid_in_seconds_to_the_timer(String time) {
        homepage.enterCountDownTime(time);
    }

    @When("I click on start button")
    public void i_click_on_start_button() {
        homepage.clickStartButton();
        getCurrentTime = System.currentTimeMillis();
    }

    @Then("I should see that timer has started")
    public void i_should_see_that_timer_has_started() {
        timerPage = new TimerPage(driver);
        assert timerPage.isDisplayed();
        getLatestTime = System.currentTimeMillis();
    }
    @Then("the seconds count should go down by one second each time")
    public void the_seconds_count_should_go_down_by_one_second_each_time() throws InterruptedException {
        while(EXPECTED_TIME>0) {
            String[] actualTime = timerPage.getTimeInSec().split(" ");
            elapsedTimeFinder = new ElapsedTimeFinder();
            EXPECTED_TIME = elapsedTimeFinder.getLoadDelay(getCurrentTime,getLatestTime);

            logger.info("This is the expected time: {}", EXPECTED_TIME);
            logger.info("This is the actual time: {}", actualTime[0]);

            assert EXPECTED_TIME == Integer.parseInt(actualTime[INDEX_ZERO]);
            EXPECTED_TIME = EXPECTED_TIME - 1;
            TimeUnit.MILLISECONDS.sleep(TIME_DELAY);
        }
        assert driver.switchTo().alert().getText().equals("Time Expired!");
        driver.switchTo().alert().accept();
    }

    @Then("I should see timer should not start")
    public void timer_should_not_start() {
        timerPage = new TimerPage(driver);
        assert homepage.isDisplayed();
    }
}
