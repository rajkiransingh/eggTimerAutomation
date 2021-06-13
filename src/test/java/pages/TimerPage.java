package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TimerPage {
    protected WebDriver driver;

    @FindBy(xpath = "//*[@id='root']//p")
    WebElement counter;

    @FindBy(xpath = "//*[@id='root']//p/span")
    WebElement timeInSec;

    public boolean isDisplayed() { return counter.isDisplayed(); }
    public boolean isSelected() { return counter.isSelected(); }
    public String getTimeInSec() { return timeInSec.getText();}

    public TimerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
