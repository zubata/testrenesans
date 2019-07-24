package renesans.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@class='services services_main']//div[@class='service__title']")
    List<WebElement> mainservices;

    @Step("Переход на страницу {point}")
    public void openServicePage(String point) {
        clickPoint(mainservices,point);
    }
}
