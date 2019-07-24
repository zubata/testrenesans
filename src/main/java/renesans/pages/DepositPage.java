package renesans.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DepositPage extends BasePage {

    @FindBy(xpath = "//span[@class='calculator__currency-field-text']")
    List<WebElement> currency;

    /*@FindBy(xpath = "//*[@class='js-calc-rate']")
    WebElement rate;

    @FindBy(xpath = "//*[@class='js-calc-result']")
    WebElement result;

    @FindBy(xpath = "//*[@class='js-calc-replenish']")
    WebElement replenish;

    @FindBy(xpath = "//*[@class='js-calc-earned']")
    WebElement moneyearned;*/

    public void choosecurrency(String currencyname) {
        clickPoint(currency, currencyname);
    }
    public void inputAmount(String amount) {
        fillfield("//input[@name='amount']", amount);
    }
    public void inputTimelimit(String timelimit) { multiplechoice("//*[@name='period']", timelimit); }
    public void inputMonthRefill(String monthRefill) { fillfield("//*[@name='replenish']", monthRefill); }
    public void capitalCheckbox(String yesOrNot) { markcheckbox("//input[@name='capitalization']/parent::div",yesOrNot);}
    public void partialOutCheckbox(String yesOrNot) { markcheckbox("//input[@name='partial_out']/parent::div",yesOrNot); takeScreenshot();}

    public void checkrate(String srate) { checkneededfield("//*[@class='js-calc-rate']", srate); }
    public void checkresult(String sresult) { checkneededfield("//*[@class='js-calc-result']", sresult); }
    public void checkreplish(String sreplish) { checkneededfield("//*[@class='js-calc-replenish']", sreplish); }
    public void checkmoneyearned(String smoneyearned) { checkneededfield("//*[@class='js-calc-earned']", smoneyearned); takeScreenshot();}
}
