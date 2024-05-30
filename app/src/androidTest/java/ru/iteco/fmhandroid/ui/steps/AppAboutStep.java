package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.data.DataHelper.elementWaiting;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class AppAboutStep {


    public static ViewInteraction logoAbout = onView(withId(R.id.trademark_image_view));
    public static ViewInteraction backButtonAbout = onView(withId(R.id.about_back_image_button));
    public static ViewInteraction versionTitleField = onView(withId(R.id.about_version_title_text_view));
    public static ViewInteraction versionNumberField = onView(withId(R.id.about_version_value_text_view));
    public static ViewInteraction policyText = onView(withId(R.id.about_privacy_policy_label_text_view));
    public static ViewInteraction termsOfUseText = onView(withId(R.id.about_terms_of_use_label_text_view));
    public static ViewInteraction infoCompany = onView(withId(R.id.about_company_info_label_text_view));
    public static ViewInteraction privacyPolicyValue = onView(withId(R.id.about_privacy_policy_value_text_view));
    public static ViewInteraction termsOfUseValue = onView(withId(R.id.about_terms_of_use_value_text_view));


    @Step("Проверка формы О приложении")
    public void checkingElementsAbout() {
        elementWaiting(withId(R.id.about_company_info_label_text_view), 7000);
        logoAbout.check(matches(isDisplayed()));
        backButtonAbout.check(matches(isDisplayed()));
        versionTitleField.check(matches(isDisplayed()));
        versionNumberField.check(matches(isDisplayed()));
        policyText.check(matches(isDisplayed()));
        termsOfUseText.check(matches(isDisplayed()));
        infoCompany.check(matches(isDisplayed()));
        privacyPolicyValue.check(matches(isDisplayed()));
        termsOfUseValue.check(matches(isDisplayed()));
    }

    @Step("Переход на главную страницу")
    public void backHomePage() {
        backButtonAbout.perform(click());
    }

}