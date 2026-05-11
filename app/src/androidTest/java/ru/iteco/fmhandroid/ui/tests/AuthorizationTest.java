package ru.iteco.fmhandroid.ui.tests;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Data;
import ru.iteco.fmhandroid.ui.data.ViewMatchersUtil;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.screenElements.MainScreen.allNewsButton;
import static ru.iteco.fmhandroid.ui.screenElements.MainScreen.newsListMain;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class AuthorizationTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private final AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    private View decorView;

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            decorView = activity.getWindow().getDecorView();
        });
    }

    @Test
    public void successAuthorizationTest() {
        authorizationSteps.fillLoginForm(Data.VALID_LOGIN, Data.VALID_PASSWORD);

        ViewMatchersUtil.waitForView(newsListMain, 8000);
        onView(allNewsButton).check(matches(isDisplayed()));

        authorizationSteps.logout();
    }

    @Test
    public void emptyFields() {
        authorizationSteps.fillEmptyForm();

        onView(withText(Data.AUTHORIZATION_EMPTY))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void simbolInTheLoginField() {
        authorizationSteps.fillLoginForm(Data.INVALID_CREDENTIALS, Data.VALID_PASSWORD);

        onView(withText(Data.AUTHORIZATION_FAIL))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }
}
