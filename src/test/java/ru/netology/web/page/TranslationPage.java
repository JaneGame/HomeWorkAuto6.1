package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.bouncycastle.asn1.isismtt.x509.MonetaryLimit;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class TranslationPage {
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private SelenideElement amountInput = $("[data-test-id='amount'] input");
    private SelenideElement fromInput = $("[data-test-id='from'] input");
    private SelenideElement transferHead = $(byText("Пополнение карты"));
    private static SelenideElement error = $(withText("Ошибка! На карте нет нужной суммы!"));

    public TranslationPage() {
        transferHead.shouldBe(visible);
    }

    public DashboardPage makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
        return new DashboardPage();
    }


    public void getError() {
        error.shouldBe(visible);
    }
}

