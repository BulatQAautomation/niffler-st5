package guru.qa.niffler.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import guru.qa.grpc.niffler.grpc.CurrencyValues;
import guru.qa.niffler.jupiter.annotation.GenerateCategory;
import guru.qa.niffler.jupiter.annotation.Spend;
import guru.qa.niffler.jupiter.extension.BrowserExtension;
import guru.qa.niffler.jupiter.extension.SpendExtension;
import guru.qa.niffler.model.SpendJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@ExtendWith({BrowserExtension.class, SpendExtension.class})
public class SpendingTest {

    @BeforeEach
    void doLogin() {
        Selenide.open("http://127.0.0.1:3000/");
        $("a[href='/redirect']").click();
        $("input[name=username]").setValue("BULAT");
        $("input[name=password]").setValue("12345");
        $("button[type=submit]").submit();
    }

//    @GenerateCategory(
//
//    )
    @Spend(
            username = "Bulat",
            description = "QA.GURU Advanced 5",
            currency = CurrencyValues.RUB,
            category = "Обучение",
            amount = 65000.00
    )
    @Test
    void spendingShouldBeDeletedAfterTableAction(SpendJson spendJson) {
        SelenideElement rowWithSpending = $(".spending-table tbody")
                .$$("tr")
                .find(text(spendJson.description()));

        rowWithSpending.$$("td").first().click();
        $(".spendings__bulk-actions button")
                .click();

        $(".spendings-table tbody").$$("tr")
                .shouldHave(size(0));


    }
}
