package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static ru.iteco.fmhandroid.ui.screenElements.AuthorizationScreen.*;

import ru.iteco.fmhandroid.ui.data.ViewMatchersUtil;

public class AuthorizationSteps {
    public void fillLoginForm(String login, String password) {
        ViewMatchersUtil.waitForView(loginField, 8000);
        onView(loginField).check(matches(isDisplayed()));
        onView(loginField).perform(replaceText(login), closeSoftKeyboard());

        onView(passwordField).check(matches(isDisplayed()));
        onView(passwordField).perform(replaceText(password), closeSoftKeyboard());

        onView(enterButton).check(matches(isDisplayed()));
        onView(enterButton).perform(click());
    }

    public void fillEmptyForm() {
        ViewMatchersUtil.waitForView(loginField, 8000);
        onView(enterButton).check(matches(isDisplayed()));
        onView(enterButton).perform(click());
    }

    public void logout() {
        ViewMatchersUtil.waitForView(logoutButton, 8000);
        onView(logoutButton).check(matches(isDisplayed()));
        onView(logoutButton).perform(click());

        ViewMatchersUtil.waitForView(logoutConfirm, 8000);
        onView(logoutConfirm).check(matches(isDisplayed()));
        onView(logoutConfirm).perform(click());
    }
}
