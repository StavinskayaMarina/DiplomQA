package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fmhandroid.ui.data.DataHelper.childAtPosition;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;


public class QuotesStep {

    public static ViewInteraction logoQuotes = onView(withId(R.id.trademark_image_view));
    public static ViewInteraction titleQuotes = onView(withId(R.id.our_mission_title_text_view));
    public static ViewInteraction butterflyList = onView(withId(R.id.our_mission_item_list_recycler_view));
    public static ViewInteraction butterflyConstraintLayout = onView(allOf(withId(R.id.our_mission_item_list_recycler_view),
            childAtPosition(withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")), 0)));

    @Step("Проверка формы Цитаты")
    public void checkingElementsQuotes() {
        logoQuotes.check(matches(isDisplayed()));
        titleQuotes.check(matches(isDisplayed()));
        butterflyList.check(matches(isDisplayed()));
    }

    @Step("Раскрытие/закрытие цитат")
    public void checkQuote(int number) {
        butterflyConstraintLayout.check(matches(isDisplayed()));
        butterflyConstraintLayout.perform(actionOnItemAtPosition(number, click()));
    }

    @Step("Проверка видимости продолжения цитаты")
    public void displayAddQuote(String text) {
        onView(allOf(
                withId(R.id.our_mission_item_description_text_view),
                withText(text),
                isCompletelyDisplayed()))
                .check(matches(isDisplayed()));
    }


}