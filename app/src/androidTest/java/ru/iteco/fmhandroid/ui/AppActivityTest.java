package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppActivityTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void appActivityTest() {
        onView(allOf(withId(R.id.login_text_input_layout),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.login_text_input_layout),isDisplayed())).
                perform(replaceText("login2"), closeSoftKeyboard());

        onView(allOf(withId(R.id.password_text_input_layout), isDisplayed())).
                perform(replaceText("password2"), closeSoftKeyboard());

        onView(allOf(withId(R.id.enter_button), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.trademark_image_view),isDisplayed())).
                check(matches(isDisplayed()));
    }
}
