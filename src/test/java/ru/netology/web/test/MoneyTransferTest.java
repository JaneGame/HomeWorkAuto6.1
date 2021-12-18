package ru.netology.web.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV1;
import ru.netology.web.page.TranslationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.getFirstCardInfo;
import static ru.netology.web.data.DataHelper.getSecondCardInfo;

public class MoneyTransferTest {



        @Test
        void shouldTransferFromFirstToSecond() {
            var loginPage = open("http://localhost:9999", LoginPageV1.class);
            var authInfo = DataHelper.getAuthInfo();
            var verificationPage = loginPage.validLogin(authInfo);
            var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
            var dashboardPage = verificationPage.validVerify(verificationCode);
            var firstCardInfo = getFirstCardInfo();
            var secondCardInfo = getSecondCardInfo();
            int amount = 1000;
            var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo) - amount;
            var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo) + amount;
            var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
            dashboardPage = transferPage.makeTransfer(String.valueOf(amount), firstCardInfo);
            var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
            var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
            assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
            assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
        }


    @Test
    void ifCardBalanceUnderAmount() {
        var loginPage = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        int amount = 100000;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo) - amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo) + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
            dashboardPage = transferPage.makeTransfer(String.valueOf(amount), firstCardInfo);
        if (dashboardPage.getCardBalance(firstCardInfo) < amount) {
            TranslationPage.error();
        } else {
            var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
            var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
            assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
            assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
        }
    }}

