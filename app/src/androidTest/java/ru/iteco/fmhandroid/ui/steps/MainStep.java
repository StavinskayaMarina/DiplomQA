package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.data.DataHelper.elementWaiting;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;


public class MainStep {


    public static ViewInteraction mainLogo = onView(withId(R.id.trademark_image_view));
    public static ViewInteraction profileButton = onView(withId(R.id.authorization_image_button));
    public static ViewInteraction logOutButton = onView(withText("Log out"));
    public static ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    public static ViewInteraction mainOfMenu = onView(withText("Main"));
    public static ViewInteraction newsOfMenu = onView(withText("News"));
    public static ViewInteraction aboutOfMenu = onView(withText("About"));
    public static ViewInteraction butterflyButton = onView(withId(R.id.our_mission_image_button));
    public static ViewInteraction titleNewsContainer = onView(withText("News"));
    public static ViewInteraction allNewsButton = onView(withId(R.id.all_news_text_view));

    @Step("Ожидание загрузки главной страницы при входе в приложение")
    public void mainScreenLoad() {
        elementWaiting(withId(R.id.all_news_text_view), 5000);
    }

    @Step("Проверка формы Главная")
    public void checkingElementsMain() {
        mainLogo.check(matches(isDisplayed()));
        profileButton.check(matches(isDisplayed()));
        menuButton.check(matches(isDisplayed()));
        butterflyButton.check(matches(isDisplayed()));
        titleNewsContainer.check(matches(isDisplayed()));
        allNewsButton.check(matches(isDisplayed()));
    }

    @Step("Переход к разделу Новости через кнопку Меню")
    public void openNewsPage() {
        menuButton.perform(click());
        newsOfMenu.perform(click());
    }

    @Step("Переход к разделу О приложении через кнопку Меню")
    public void openAboutPage() {
        menuButton.perform(click());
        aboutOfMenu.perform(click());
    }

    @Step("Переход к разделу Цитаты")
    public void openQuotesPage() {
        butterflyButton.perform(click());
    }

    @Step("Выход из профиля")
    public void logOut() {
        profileButton.perform(click());
        logOutButton.perform(click());
    }

    @Step("Переход к разделу Новости через кнопку Все новости на Главной")
    public void openAllNews() {
        allNewsButton.perform(click());
    }
}