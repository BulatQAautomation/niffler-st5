package guru.qa.niffler.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private final SelenideElement spendingsTableBody = $(".table.spendings-table tbody");
    private final SelenideElement spendingsBulkActionsButton = $(".spendings__bulk-actions button");

    public SelenideElement spendingRow(String description) {
        return spendingsTableBody.$$("tr").find(text(description));
    }

    public ElementsCollection spendingRows() {
        return spendingsTableBody.$$("tr");
    }

    public SelenideElement spendingBulkActionsButton() {
        return spendingsBulkActionsButton;
    }
}
