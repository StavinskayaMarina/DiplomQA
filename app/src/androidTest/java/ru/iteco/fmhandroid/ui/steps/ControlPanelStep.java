package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.data.DataHelper.elementWaiting;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;


public class ControlPanelStep {

    public static ViewInteraction logoPanel = onView(withId(R.id.trademark_image_view));
    public static ViewInteraction titlePagePanel = onView(withText("Control panel"));
    public static ViewInteraction newsListPanel = onView(withId(R.id.news_list_recycler_view));
    public static ViewInteraction sortButton = onView(withId(R.id.sort_news_material_button));
    public static ViewInteraction filterButton = onView(withId(R.id.filter_news_material_button));
    public static ViewInteraction addNewsButton = onView(withId(R.id.add_news_image_view));
    public static ViewInteraction okButtonMessagePanel = onView(withId(android.R.id.button1));

    public ViewInteraction deleteNewsButton(String titleDelete) {
        return onView(allOf(withId(R.id.delete_news_item_image_view),
                withParent(withParent(allOf(withId(R.id.news_item_material_card_view),
                        withChild(withChild(withText(titleDelete))))))));
    }


    @Step("Переход к разделу Панель управления через страницу Новости")
    public void openControlPanelPageFromNews() {
        NewsStep.controlPanelButton.perform(click());
        elementWaiting(withId(R.id.add_news_image_view), 5000);
    }

    @Step("Проверка формы Панель управления")
    public void checkingElementsControlPanel() {
        elementWaiting(withId(R.id.add_news_image_view), 5000);
        logoPanel.check(matches(isDisplayed()));
        titlePagePanel.check(matches(isDisplayed()));
        newsListPanel.check(matches(isDisplayed()));
        sortButton.check(matches(isDisplayed()));
        filterButton.check(matches(isDisplayed()));
        addNewsButton.check(matches(isDisplayed()));
    }

    @Step("Нажатие кнопки Фильтр на панели управления")
    public void openNewsFilter() {
        filterButton.perform(click());
    }

    @Step("Нажатие кнопки Добавить новость на панели управления")
    public void openCreateNews() {
        addNewsButton.perform(click());
    }

    @Step("Нажатие кнопки Удалить новость с представленным назаванием на панели управления")
    public void clickDeleteNews(String titleDelete) {
        deleteNewsButton(titleDelete).perform(click());
        okButtonMessagePanel.perform(click());
    }

    @Step("Проверка наличия новости по заголовку")
    public void checkNewsWithTitle(String titleText) {
        onView(allOf(withText(titleText), isDisplayed())).check(matches(isDisplayed()));
    }

    @Step("Проверка отсутствия новости по заголовку")
    public void checkNoNewsWithTitle(String titleText) {
        onView(allOf(withText(titleText), isDisplayed())).check(doesNotExist());
    }

}