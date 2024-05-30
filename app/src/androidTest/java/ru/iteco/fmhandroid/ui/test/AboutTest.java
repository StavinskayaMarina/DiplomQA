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
import ru.iteco.fmhandroid.ui.steps.LoginStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));
    LoginStep loginSteps = new LoginStep();
    AppAboutStep aboutSteps = new AppAboutStep();
    LoginStep authorizationSteps = new LoginStep();
    MainStep mainSteps = new MainStep();


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
    @DisplayName("Проверка отображения форм на странице О приложении")
    public void shouldAllElementsPresentAboutPage() {
        mainSteps.openAboutPage();
        aboutSteps.checkingElementsAbout();
    }

    @Test
    @DisplayName("переход со страницы О приложении на Главную страницу")
    public void shouldGoToMainFromAbout() {
        mainSteps.openAboutPage();
        aboutSteps.backHomePage();
        mainSteps.mainScreenLoad();
        mainSteps.checkingElementsMain();
    }
}
