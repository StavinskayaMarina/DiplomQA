package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;


public class NewsCreationStep {

    public static ViewInteraction titlePage = onView(withId(R.id.custom_app_bar_title_text_view));
    public static ViewInteraction categoryText =
            onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction titleText = onView(withId(R.id.news_item_title_text_input_edit_text));
    public static ViewInteraction descriptionText =
            onView(withId(R.id.news_item_description_text_input_edit_text));
    public static ViewInteraction publicationDate = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public static ViewInteraction time = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public static ViewInteraction switcher = onView(withId(R.id.switcher));
    public static ViewInteraction saveButton = onView(withId(R.id.save_button));
    public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));

    @Step("проверка формы Создание новости")
    public void checkingElementsCreateNews() {
        titlePage.check(matches(isDisplayed()));
        categoryText.check(matches(isDisplayed()));
        titleText.check(matches(isDisplayed()));
        descriptionText.check(matches(isDisplayed()));
        publicationDate.check(matches(isDisplayed()));
        time.check(matches(isDisplayed()));
        switcher.check(matches(isDisplayed()));
        saveButton.check(matches(isDisplayed()));
        cancelButton.check(matches(isDisplayed()));
    }

    @Step("Заполнение поля Категория")
    public void enteringDataCategoryNews(String text) {
        categoryText.perform(replaceText(text));
    }

    @Step("Заполнение поля Заголовок")
    public void enteringDataTitleNews(String text) {
        titleText.perform(replaceText(text));
    }

    @Step("Заполнение поля Дата")
    public void enteringDataPublicDateNews(String text) {
        publicationDate.perform(replaceText(text));
    }

    @Step("Заполнение поля Время")
    public void enteringDataTimeNews(String text) {
        time.perform(replaceText(text));
    }

    @Step("Заполнение поля Описание")
    public void enteringDataDescriptionNews(String text) {
        descriptionText.perform(replaceText(text));
    }

    @Step("Заполнение полей для создания новости")
    public void createNews(String category, String title, String publicationDate,
                           String publicationTime, String description) {
        enteringDataCategoryNews(category);
        enteringDataTitleNews(title);
        enteringDataPublicDateNews(publicationDate);
        enteringDataTimeNews(publicationTime);
        enteringDataDescriptionNews(description);
    }

    @Step("Нажать кнопку Сохранить")
    public void clickSaveButton() {
        saveButton.perform(click());
    }

    @Step("Проверка текста сообщения при создании новости")
    public void checkToastMessageText(String text, View decorView) {
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

}