package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.ui.utils.ViewMatchersUtil;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AuthorizationTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    private View decorView;

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            decorView = activity.getWindow().getDecorView();
        });
    }

    @Test
    public void successAuthorizationTest() { //авторизация с валидными данными и выход из аккаунта
        ViewMatchersUtil.waitForView(authorizationPage.getAuthorLoginField, 8000);
        onView(authorizationPage.getAuthorLoginField).check(matches(isDisplayed()));
        onView(authorizationPage.getAuthorLoginField).perform(replaceText(authorizationPage.validLogin), closeSoftKeyboard());
        onView(authorizationPage.getAuthorPasswordField).check(matches(isDisplayed()));
        onView(authorizationPage.getAuthorPasswordField).perform(replaceText(authorizationPage.validPassword), closeSoftKeyboard());
        onView(authorizationPage.getEnterButton).check(matches(isDisplayed()));
        onView(authorizationPage.getEnterButton).perform(click());
        ViewMatchersUtil.waitForView(mainPage.getListNewsMain, 8000);
        onView(mainPage.getButtonAllNews).check(matches(isDisplayed()));
        authorizationPage.logOut();
    }

    @Test
    public void emptyFieldsTest(){ // авторизация с незаполненными полями ввода
        ViewMatchersUtil.waitForView(authorizationPage.getAuthorLoginField, 8000);
        onView(authorizationPage.getEnterButton).check(matches(isDisplayed()));
        onView(authorizationPage.getEnterButton).perform(click());
        onView(withText("Login and password cannot be empty"))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void wrongLoginTest(){ // авторизация с неверным логином
        ViewMatchersUtil.waitForView(authorizationPage.getAuthorLoginField, 8000);
        authorizationPage.login(authorizationPage.simbols, authorizationPage.validPassword);
        onView(withText("Something went wrong. Try again later."))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }
}
