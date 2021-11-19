package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.bouncycastle.asn1.isismtt.x509.MonetaryLimit;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TranslationPage {
    private SelenideElement amount = $("[data-test-id=amount]");
    private SelenideElement from = $("[data-test-id=from]");
    private SelenideElement button =$("[data-test-id=action-transfer]");


  public DashboardPage translation(DataHelper.numberFirst info) {
      amount.setValue(info.getAmount());
      from.setValue(info.getNumberCard());
      button.click();
      return new DashboardPage();

  }

    }

