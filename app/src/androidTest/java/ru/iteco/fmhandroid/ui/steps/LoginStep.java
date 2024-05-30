package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.data.DataHelper.elementWaiting;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;


public class LoginStep {
    public static ViewInteraction titleAuthorization = onView(withText("Authorization"));
    public static ViewInteraction loginField = onView(withHint("Login"));
    public static ViewInteraction passwordField = onView(withHint("Password"));
    public static ViewInteraction loginButton = onView(withId(R.id.enter_button));


    @Step("Загрузка приложения")
    public void appDownload() {
        elementWaiting(withId(R.id.splashscreen_image_view), 10000);
    }

    @Step("Ожидание загрузки страницы авторизации")
    public void loadLoginPage() {
        elementWaiting(withId(R.id.enter_button), 5000);
    }

    @Step("Проверка формы Авторизация")
    public void checkingElementsLoginBlock() {
        titleAuthorization.check(matches(isDisplayed()));
        loginField.check(matches(isDisplayed()));
        passwordField.check(matches(isDisplayed()));
        loginButton.check(matches(isDisplayed()));
    }

    @Step("Вход в приложение с валидными данными")
    public void validLogin() {
        DataHelper help = new DataHelper();
        loginField.perform(typeText(help.getValidUser().getLogin()), closeSoftKeyboard());
        passwordField.perform(typeText(help.getValidUser().getPassword()), closeSoftKeyboard());
        loginButton.perform(click());
    }

    @Step("Вход в приложение с невалидными данными")
    public void invalidLogin() {
        DataHelper helper = new DataHelper();
        loginField.perform(typeText(helper.getInalidUser().getLogin()), closeSoftKeyboard());
        passwordField.perform(typeText(helper.getInalidUser().getPassword()), closeSoftKeyboard());
        loginButton.perform(click());
    }

    @Step("Вход в приложение с незаполненными полями")
    public void emptyLogin() {
        loginButton.perform(click());
    }

    @Step("Проверка отображения сообщения об ошибке при авторизации")
    public void checkToastMessageText(String text, View decorView) {
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }
}
