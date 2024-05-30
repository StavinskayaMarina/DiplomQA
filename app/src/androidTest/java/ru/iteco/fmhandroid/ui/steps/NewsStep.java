package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
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


public class NewsStep {
    public static ViewInteraction logoNews = onView(withId(R.id.trademark_image_view));
    public static ViewInteraction titleNews = onView(withText("News"));
    public static ViewInteraction sort = onView(withId(R.id.sort_news_material_button));
    public static ViewInteraction filter = onView(withId(R.id.filter_news_material_button));
    public static ViewInteraction controlPanelButton = onView(withId(R.id.edit_news_material_button));
    public static ViewInteraction allNewsBlock = onView(withId(R.id.all_news_cards_block_constraint_layout));
    public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public ViewInteraction okButtonMessage = onView(withText("OK"));
    public ViewInteraction childNewsButton = onView(allOf(withId(R.id.news_list_recycler_view),
            childAtPosition(withClassName(is("android.widget.LinearLayout")),
                    withId(R.id.all_news_cards_block_constraint_layout), 0)));


    @Step("Проверка формы Новости")
    public void checkingElementsNews() {
        logoNews.check(matches(isDisplayed()));
        titleNews.check(matches(isDisplayed()));
        sort.check(matches(isDisplayed()));
        filter.check(matches(isDisplayed()));
        controlPanelButton.check(matches(isDisplayed()));
        allNewsBlock.check(matches(isDisplayed()));
    }

    @Step("Переход на Главную со страницы Новости")
    public void goBackMainFromNews() {
        MainStep.menuButton.perform(click());
        MainStep.mainOfMenu.perform(click());
    }

    @Step("Переход на страницу О приложении со страницы Новости")

    public void goBackMainFromAbout() {
        MainStep.menuButton.perform(click());
        MainStep.aboutOfMenu.perform(click());
    }

    @Step("Открыть/закрыть новость")
    public void clickNewsItem(int index) {
        childNewsButton.perform(actionOnItemAtPosition(index, click()));
    }

    @Step("Нажатие кнопки Отмена")
    public void clickCancelButton() {
        cancelButton.perform(click());
    }

    @Step("Нажатие кнопки ОК в сообщении")
    public void clickOKButton() {
        okButtonMessage.perform(click());
    }
}