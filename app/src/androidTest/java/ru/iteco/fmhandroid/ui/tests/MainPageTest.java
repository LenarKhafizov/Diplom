package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Data;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.screenElements.MainScreen.ourMissionTitle;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class MainPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    private final MainSteps mainSteps = new MainSteps();

    @Test
    public void ourMissionTest() {
        authorizationSteps.fillLoginForm(Data.VALID_LOGIN, Data.VALID_PASSWORD);
        mainSteps.openOurMission();
        onView(ourMissionTitle).check(matches(withText("Love is all")));
        authorizationSteps.logout();
    }
}
