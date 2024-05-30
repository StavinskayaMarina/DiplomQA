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
import ru.iteco.fmhandroid.ui.steps.LoginStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));

    MainStep mainSteps = new MainStep();
    LoginStep loginSteps = new LoginStep();

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
    @DisplayName("Проверка отображения форм на Главной странице")
    public void shouldAllElementsPresentMainPage() {
        mainSteps.checkingElementsMain();
    }

    @Test
    @DisplayName("Переход с вкладки Main на  News  с помощью кнопки All News")
    public void shouldGoToMainFromNews() {
        mainSteps.openAllNews();
    }

    @Test
    @DisplayName("Переход с вкладки Main на  News  через Меню")
    public void shouldGoToMainFromAboutViaMenuButton() {
        mainSteps.openAboutPage();
    }

}
