package ru.iteco.fmhandroid.ui.test;

import static ru.iteco.fmhandroid.ui.data.DataHelper.RandomCategory.randomCategory;
import static ru.iteco.fmhandroid.ui.data.DataHelper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.data.DataHelper.getCurrentTime;

import android.view.View;

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
import ru.iteco.fmhandroid.ui.steps.ControlPanelStep;
import ru.iteco.fmhandroid.ui.steps.LoginStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;
import ru.iteco.fmhandroid.ui.steps.NewsCreationStep;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ControlPanelTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));

    LoginStep loginSteps = new LoginStep();
    MainStep mainSteps = new MainStep();
    LoginStep authorizationSteps = new LoginStep();
    ControlPanelStep controlPanelSteps = new ControlPanelStep();
    NewsCreationStep createNewsSteps = new NewsCreationStep();
    private View decorView;

    @Before
    public void setUp() {
        loginSteps.appDownload();
        try {
            mainSteps.mainScreenLoad();
        } catch (Exception e) {
            authorizationSteps.validLogin();
            mainSteps.mainScreenLoad();
        }
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }

    @Test
    @DisplayName("Проверка отображения форм на Панели управления")
    public void shouldGoToControlPanel() {
        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPageFromNews();
        controlPanelSteps.checkingElementsControlPanel();
    }

    @Test
    @DisplayName("Проверка отображения форм на странице создания новости")
    public void shouldAllElementsPresentCreateNewsPage() {
        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPageFromNews();
        controlPanelSteps.openCreateNews();
        createNewsSteps.checkingElementsCreateNews();
    }

    @Test
    @DisplayName("Cоздание новости с валидными данными")
    public void shouldCreatingNewsWithValidData() {
        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();
        String title = "TestTitleValidData";
        String description = "Test description data valid";
        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPageFromNews();
        controlPanelSteps.openCreateNews();
        createNewsSteps.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        createNewsSteps.clickSaveButton();
        controlPanelSteps.checkNewsWithTitle(title);
    }

    /*Тест на проверку создания новости с пустыми данными (Негативный)*/
    @Test
    @DisplayName("Cоздание новости с пустыми данными")
    public void shouldCreatingNewsWithEmptyData() {
        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPageFromNews();
        controlPanelSteps.openCreateNews();
        createNewsSteps.clickSaveButton();
        createNewsSteps.checkToastMessageText("Fill empty fields", decorView);
    }

    @Test
    @DisplayName("Удаление новости")
    public void shouldDeletingNews() {

        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();

        String title = "TestDelete";
        String description = "TestDescriptionDelete";

        mainSteps.openNewsPage();
        controlPanelSteps.openControlPanelPageFromNews();
        controlPanelSteps.openCreateNews();
        createNewsSteps.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        createNewsSteps.clickSaveButton();
        controlPanelSteps.clickDeleteNews(title);
        controlPanelSteps.checkingElementsControlPanel();
        controlPanelSteps.checkNoNewsWithTitle(title);
    }
}
