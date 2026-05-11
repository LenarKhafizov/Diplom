package ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import static ru.iteco.fmhandroid.ui.data.ChildAtPositionMatcher.childAtPosition;

import android.view.View;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class AboutScreen {
    public static Matcher<View> menuButton = allOf(withId(R.id.main_menu_image_button),
            withContentDescription("Main menu"),
            childAtPosition(
                    allOf(withId(R.id.container_custom_app_bar_include_on_fragment_main),
                            childAtPosition(withClassName(is("android.widget.LinearLayout")), 0)),
                    0));

    public static Matcher<View> aboutButton = allOf(withId(android.R.id.title), withText("About"));

    public static Matcher<View> backButton = allOf(withId(R.id.about_back_image_button),
            childAtPosition(
                    allOf(withId(R.id.container_custom_app_bar_include_on_fragment_about),
                            childAtPosition(withClassName(is("android.widget.LinearLayout")), 0)),
                    1));

    public static Matcher<View> versionTitle = allOf(withId(R.id.about_version_title_text_view),
            withText("Version:"));

    public static Matcher<View> versionValue = allOf(withId(R.id.about_version_value_text_view),
            withText("1.0.0"));

    public static Matcher<View> privacyPolicyLink = withId(R.id.about_privacy_policy_value_text_view);

    public static Matcher<View> termsOfUseLink = withId(R.id.about_terms_of_use_value_text_view);
}
