package renesans.pages;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import renesans.core.Initial;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait waiting;
    public ArrayList<WebElement> listinputel = new ArrayList<>();

    public BasePage() {
        this.driver = Initial.getDriver();
        waiting = new WebDriverWait(driver, 5, 200);
        PageFactory.initElements(driver, this);
        pageWaitingLoad();
    }

    @Step("Ожидание, когда что элемент {element} появится")
    public WebElement waitelement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
        return waiting.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement elementbyxpath(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        return waitelement(element);
    }

    @Step("Клик на пункт {name} на странице")
    public void clickPoint(List<WebElement> listelemnts, String name) {
        for (WebElement item : listelemnts) {
            if (item.getText().equals(name)) {
                waitelement(item).click();
                return;
            }
        }
        Assert.fail(String.format("Элемент %s на странице не найден",name));
    }

    //@Step("Проверка, что элемент {element} существует")
    //public boolean checkelement(WebElement element) {
    //    try {
    //        waiting.until(ExpectedConditions.elementToBeClickable(element));
    //        return true;
    //    } catch (NoSuchElementException e) {
    //        return false;
    //    }
    //}

    @Step("Переход на следующую страницу {newwindow}")
    public void clickNextPage(WebElement element) {
        Set<String> oldsheet = driver.getWindowHandles();
        waitelement(element).click();
        Set<String> newsheet = driver.getWindowHandles();
        newsheet.removeAll(oldsheet);
        String newwindow = newsheet.iterator().next();
        driver.switchTo().window(newwindow);
    }

    @Step("Проверка, что поля заполнены (которые заполнялись)")
    public void checkfilledfields() {
        for (WebElement element : listinputel) {
            String actual = element.getAttribute("value");
            Assert.assertNotEquals("", actual);
        }
    }

    @Step("Заполение поля {name}")
    public void fillfield(String xpath, String name) {
        WebElement field = elementbyxpath(xpath);
        listinputel.add(field);
        field.sendKeys(name);
    }

    @Step("Выбор пунтка {choice} из списка")
    public void multiplechoice(String xpath, String choice) {
        WebElement order = driver.findElement(By.xpath(xpath));
        Select select = new Select(order);
        select.selectByVisibleText(choice);
    }

    @Step("Нажатие на кнопку {xpath}")
    public void clickradiobutton(String xpath) {
        WebElement radiobutton = elementbyxpath(xpath);
        radiobutton.click();
    }

    @Step("Проверка, что {field} соответствует ожидаемому")
    public void checkneededfield(String xpath, String field) {
        WebElement element = elementbyxpath(xpath);
        String actual = element.getText();
        Assert.assertEquals("Значние не соответствует ожидаемому",field,actual);
    }

    @Step("Проверка, что ошибка {expected} вышла на странице")
    public void chechErrors(String expected) {
        String xpath = String.format("//*[contains(text(),'%s')]", expected);
        String actual = elementbyxpath(xpath).getText();
        Assert.assertEquals(expected, actual);
    }

    @Step("Ожидание загрузки страницы")
    public void pageWaitingLoad() {
        elementbyxpath("//body");
    }


    public void markcheckbox(String xpath, String yesOrNot) {
        if (yesOrNot == null) return;
        yesOrNot = yesOrNot.toLowerCase();
        if (yesOrNot.equals("да")) clickradiobutton(xpath);
    }


    @Attachment(type = "image/png", value = "Screenshot")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
