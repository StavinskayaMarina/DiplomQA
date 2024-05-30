package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.CoreMatchers.not;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
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
public class AuthorizationTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));

    LoginStep loginSteps = new LoginStep();
    MainStep mainSteps = new MainStep();
    private View decorView;

    @Before
    public void setUp() {
        loginSteps.appDownload();
        try {
            loginSteps.loadLoginPage();
        } catch (
                Exception e) {
            mainSteps.logOut();
            loginSteps.loadLoginPage();
        }
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }

    @After
    public void tearDown() {
        try {
            mainSteps.logOut();
        } catch (Exception ignored) {
        }
    }

    @Test
    @DisplayName("Проверка отображения форм на странице авторизации")
    public void shouldAllElementsPresentLoginPage() {
        loginSteps.loadLoginPage();
        loginSteps.checkingElementsLoginBlock();
    }

    @Test
    @DisplayName("Авторизация с валидными данными")
    public void shouldAuthorizationUsingValidData() {
        loginSteps.validLogin();
        mainSteps.mainScreenLoad();
        mainSteps.checkingElementsMain();
    }

    @Test
    @DisplayName("Авторизация с неверным логином и паролем")
    public void shouldAuthorizationUsingNotValidData() {
        loginSteps.invalidLogin();
        loginSteps.checkToastMessageText("Something went wrong. Try again later.", decorView);
        loginSteps.titleAuthorization.check(matches(isDisplayed()));
        mainSteps.mainLogo.check(matches(not(isDisplayed())));
    }

    @Test
    @DisplayName("Авторизация с пустыми полями")
    public void shouldAuthorizationUsingEmptyData() {
        loginSteps.emptyLogin();
        loginSteps.checkToastMessageText("Login and password cannot be empty", decorView);
        loginSteps.titleAuthorization.check(matches(isDisplayed()));
        mainSteps.mainLogo.check(matches(not(isDisplayed())));
    }

    @Test
    @DisplayName("Выход из учетной записи")
    public void shouldLogOut() {
        loginSteps.validLogin();
        mainSteps.mainScreenLoad();
        mainSteps.logOut();
        loginSteps.checkingElementsLoginBlock();
    }
}
