package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    protected WebDriver driver;

    @FindBy(id = "EggTimer-start-time-input-text")
    WebElement txtTimerField;

    @FindBy(xpath = "//div[@class='EggTimer-start-time-input']/button")
    WebElement btnStart;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        assert driver.getTitle().equals("e.ggtimer - a simple countdown timer");
    }

    public boolean isDisplayed() {
        return txtTimerField.isDisplayed();
    }

    public void enterCountDownTime(String timeInSec) { txtTimerField.sendKeys(timeInSec);}

    public void clickStartButton() {
        btnStart.click();
    }
}
