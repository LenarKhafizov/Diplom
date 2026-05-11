package ru.iteco.fmhandroid.ui.steps;

import static ru.iteco.fmhandroid.ui.screenElements.AboutScreen.*;
import static ru.iteco.fmhandroid.ui.screenElements.MainScreen.newsListMain;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import ru.iteco.fmhandroid.ui.data.ViewMatchersUtil;
public class AboutSteps {
    public void goToAbout() {
        ViewMatchersUtil.waitForView(newsListMain, 6000);
        onView(menuButton).check(matches(isDisplayed()));
        onView(menuButton).perform(click());

        ViewMatchersUtil.waitForView(aboutButton, 5000);
        onView(aboutButton).check(matches(isDisplayed()));
        onView(aboutButton).perform(click());
    }

    public void goBackToMainPage() {
        ViewMatchersUtil.waitForView(backButton, 5000);
        onView(backButton).check(matches(isDisplayed()));
        onView(backButton).perform(click());
    }

    public void checkVersionDisplayed() {
        onView(versionTitle).check(matches(isDisplayed()));
        onView(versionValue).check(matches(isDisplayed()));
    }

    public void checkPrivacyPolicyLink() {
        onView(privacyPolicyLink).check(matches(isDisplayed()));
    }

    public void checkTermsOfUseLink() {
        onView(termsOfUseLink).check(matches(isDisplayed()));
    }
}
