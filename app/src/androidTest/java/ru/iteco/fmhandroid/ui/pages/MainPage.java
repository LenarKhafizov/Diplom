package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import android.view.View;
import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;
public class MainPage {
    public Matcher<View> getButtonAllNews;
    public Matcher<View> getListNewsMain;
    public Matcher<View> getButtonOurMission;
    public Matcher<View> getTitleOurMission;

    public MainPage() {
        getListNewsMain = allOf(withId(R.id.container_list_news_include_on_fragment_main));
        getTitleOurMission = allOf(withId(R.id.our_mission_title_text_view),withText("Love is all"));
    }
    public void openOurMission() {
        onView(getButtonOurMission).check(matches(isDisplayed()));
        onView(getButtonOurMission).perform(click());
    }
}
