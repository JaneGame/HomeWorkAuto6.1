package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection button = $$("[data-test-id=action-deposit]");

    public TranslationPage dashboardPage() {
        button.first().click();
        return new TranslationPage();
}}
