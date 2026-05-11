package ru.iteco.fmhandroid.ui.steps;

import static ru.iteco.fmhandroid.ui.screenElements.MainScreen.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import ru.iteco.fmhandroid.ui.data.ViewMatchersUtil;

public class MainSteps {
    public void openNews() {
        onView(menuButton).check(matches(isDisplayed()));
        onView(menuButton).perform(click());

        onView(newsItem).check(matches(isDisplayed()));
        onView(newsItem).perform(click());
    }

    public void openAbout() {
        onView(menuButton).check(matches(isDisplayed()));
        onView(menuButton).perform(click());

        onView(aboutButton).check(matches(isDisplayed()));
        onView(aboutButton).perform(click());
    }

    public void openOurMission() {
        ViewMatchersUtil.waitForView(newsListMain, 6000);
        onView(ourMissionButton).check(matches(isDisplayed()));
        onView(ourMissionButton).perform(click());
    }

    public void checkOurMissionTitle() {
        onView(ourMissionTitle).check(matches(isDisplayed()));
    }
}
