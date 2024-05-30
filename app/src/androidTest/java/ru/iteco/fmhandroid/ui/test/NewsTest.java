package ru.iteco.fmhandroid.ui.test;

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
import ru.iteco.fmhandroid.ui.steps.AppAboutStep;
import ru.iteco.fmhandroid.ui.steps.ControlPanelStep;
import ru.iteco.fmhandroid.ui.steps.LoginStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;
import ru.iteco.fmhandroid.ui.steps.NewsStep;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));

    MainStep mainSteps = new MainStep();
    LoginStep loginSteps = new LoginStep();
    NewsStep newsSteps = new NewsStep();
    ControlPanelStep controlPanelSteps = new ControlPanelStep();
    AppAboutStep aboutSteps = new AppAboutStep();

    @Before
    public void setUp() {
        loginSteps.appDownload();
        try {
            mainSteps.mainScreenLoad();
        } catch (Exception e) {
            loginSteps.validLogin();
            mainSteps.mainScreenLoad();
        }
    }

    @Test
    @DisplayName("Проверка отображения форм на странице Новости")
    public void shouldAllElementsPresentNewsPage() {
        mainSteps.openNewsPage();
        newsSteps.checkingElementsNews();
    }

    @Test
    @DisplayName("Открытие описания новости на странице Новости")
    public void shouldExpandSingleNewsOnHomePag() {
        mainSteps.openNewsPage();
        newsSteps.clickNewsItem(0);
    }

    @Test
    @DisplayName("Вход и выход из фильтрации новостей")
    public void shouldFilterOutputWithoutFiltering() {
        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPageFromNews();
        controlPanelSteps.openNewsFilter();
        newsSteps.clickCancelButton();
        controlPanelSteps.checkingElementsControlPanel();
    }

    @Test
    @DisplayName("Возврат на Главную страницу со страницы Новости")
    public void shouldReturnToMainFromNews() {
        mainSteps.openNewsPage();
        newsSteps.checkingElementsNews();
        newsSteps.goBackMainFromNews();
        mainSteps.checkingElementsMain();
    }

    /*Тест перехода на страницу О приложении со страницы Новости через кнопку Меню (Негативный, кнопка некликабельна)*/
    @Test
    @DisplayName("Переход к разделу О приложении со страницы Новости")
    public void shouldGoToNewsFromAbout() {
        mainSteps.openNewsPage();
        newsSteps.checkingElementsNews();
        newsSteps.goBackMainFromAbout();
        aboutSteps.checkingElementsAbout();
    }
}
