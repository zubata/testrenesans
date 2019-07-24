package renesans.Steps;

import cucumber.api.java.hu.De;
import cucumber.api.java.ru.Допустим;
import renesans.core.Initial;
import renesans.pages.DepositPage;
import renesans.pages.HomePage;

import java.util.Map;

public class MyStepdefs {
    @Допустим("переход на страницу Ренесанса")
    public void переход_на_страницу_Ренесанса() {
        Initial.getDriver().get("https://rencredit.ru");
    }

    @Допустим("переход на страницу {string}")
    public void переход_на_страницу(String string) {
        HomePage homePage = new HomePage();
        homePage.openServicePage(string);
    }

    @Допустим("заполнение заявки на вклад")
    public void заполнение_заявки_на_вклад(io.cucumber.datatable.DataTable dataTable) {
        Map<String,String> map1 = dataTable.asMap(String.class,String.class);
        DepositPage depositPage = new DepositPage();
        depositPage.choosecurrency(map1.get("Валюта"));
        depositPage.inputAmount(map1.get("Сумма вклада"));
        depositPage.inputTimelimit(map1.get("Срок"));
        depositPage.inputMonthRefill(map1.get("Ежемесячное пополнение"));
        depositPage.capitalCheckbox(map1.get("Ежемесячная капитализация"));
        depositPage.partialOutCheckbox(map1.get("Частичное снятие"));
    }

    @Допустим("проверить расчёт по вкладу")
    public void проверить_расчёт_по_вкладу(io.cucumber.datatable.DataTable dataTable) {
        DepositPage depositPageforchek = new DepositPage();
        Map<String,String> map2 = dataTable.asMap(String.class,String.class);
        depositPageforchek.checkrate(map2.get("Ставка"));
        depositPageforchek.checkresult(map2.get("К снятию через месяц"));
        depositPageforchek.checkreplish(map2.get("Пополнение за 6 месяцев"));
        depositPageforchek.checkmoneyearned(map2.get("Начислено"));
    }

}
