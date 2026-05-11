package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.screenElements.MainScreen.newsListMain;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Data;
import ru.iteco.fmhandroid.ui.data.ViewMatchersUtil;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class NewsTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    private final NewsSteps newsSteps = new NewsSteps();

    @Test
    public void addNewsValidTest() {
        authorizationSteps.fillLoginForm(Data.VALID_LOGIN, Data.VALID_PASSWORD);
        ViewMatchersUtil.waitForView(newsListMain, 6000);

        String uniqueTitle = "Test News " + System.currentTimeMillis();
        String uniqueDescription = "Test Description " + System.currentTimeMillis();
        newsSteps.openNewsPanel();
        newsSteps.addNews(Data.NEWS_CATEGORY_ANNOUNCEMENT, uniqueTitle, uniqueDescription);

        newsSteps.openNewsByTitle(uniqueTitle);
        newsSteps.verifyNewsDescription(uniqueDescription);

        authorizationSteps.logout();
    }

    @Test
    public void deleteNewsTest() {
        authorizationSteps.fillLoginForm(Data.VALID_LOGIN, Data.VALID_PASSWORD);
        ViewMatchersUtil.waitForView(newsListMain, 6000);

        newsSteps.openNewsPanel();
        newsSteps.deleteFirstNews();
        newsSteps.confirmDelete();
        newsSteps.checkNewsListDisplayed();

        authorizationSteps.logout();
    }
}
