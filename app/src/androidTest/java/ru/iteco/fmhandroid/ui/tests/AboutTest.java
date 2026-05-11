package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Data;
import ru.iteco.fmhandroid.ui.steps.AboutSteps;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.screenElements.AboutScreen.*;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class AboutTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    private final AboutSteps aboutSteps = new AboutSteps();

    @Test
    public void intentsTestPrivacyPolicy() {
        authorizationSteps.fillLoginForm(Data.VALID_LOGIN, Data.VALID_PASSWORD);
        aboutSteps.goToAbout();
        onView(privacyPolicyLink).check(matches(isDisplayed()))
                .check(matches(withText("https://vhospice.org/#/privacy-policy/")));
        aboutSteps.goBackToMainPage();
        authorizationSteps.logout();
    }

    @Test
    public void intentsTermsUseTest() {
        authorizationSteps.fillLoginForm(Data.VALID_LOGIN, Data.VALID_PASSWORD);
        aboutSteps.goToAbout();
        onView(termsOfUseLink).check(matches(isDisplayed()))
                .check(matches(withText("https://vhospice.org/#/terms-of-use")));
        aboutSteps.goBackToMainPage();
        authorizationSteps.logout();
    }
}
