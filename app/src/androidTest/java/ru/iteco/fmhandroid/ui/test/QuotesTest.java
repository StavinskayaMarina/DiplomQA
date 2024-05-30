package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.action.ViewActions.click;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.LoginStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;
import ru.iteco.fmhandroid.ui.steps.QuotesStep;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class QuotesTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));

    LoginStep loginSteps = new LoginStep();
    LoginStep authorizationSteps = new LoginStep();
    MainStep mainSteps = new MainStep();
    QuotesStep quotesSteps = new QuotesStep();

    @Before
    public void setUp() {
        loginSteps.appDownload();
        try {
            mainSteps.mainScreenLoad();
        } catch (Exception e) {
            authorizationSteps.validLogin();
            mainSteps.mainScreenLoad();
        }
    }

    @Test
    @DisplayName("Проверка отображения форм на странице Цитаты")
    public void shouldAllElementsPresentQuotesPage() {
        mainSteps.openQuotesPage();
        quotesSteps.checkingElementsQuotes();
    }

    @Test
    @DisplayName("Развернуть/свернуть цитату")
    public void shouldExpandAndCollapseQuote() {
        String quoteText = "Все сотрудники хосписа - это адвокаты пациента, его прав и потребностей. Поиск путей решения различных задач - это и есть хосписный индивидуальный подход к паллиативной помощи.";
        mainSteps.openQuotesPage();
        quotesSteps.checkQuote(2);
        quotesSteps.displayAddQuote(quoteText);
        quotesSteps.checkQuote(2);
    }

    @Test
    @DisplayName("переход со страницы О приложении на Главную страницу")
    public void shouldGoToMainFromAbout() {
        mainSteps.openQuotesPage();
        mainSteps.menuButton.perform(click());
        mainSteps.mainOfMenu.perform(click());
        mainSteps.checkingElementsMain();
    }
}
