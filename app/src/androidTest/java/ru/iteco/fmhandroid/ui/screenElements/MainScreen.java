package ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import android.view.View;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;
public class MainScreen {
    public static Matcher<View> menuButton = allOf(withId(R.id.main_menu_image_button),
            withContentDescription("Main menu"));

    public static Matcher<View> newsItem = allOf(withId(android.R.id.title),
            withText("News"));

    public static Matcher<View> aboutButton = allOf(withId(android.R.id.title),
            withText("About"));
    public static Matcher<View> allNewsButton = withId(R.id.all_news_text_view);

    public static Matcher<View> ourMissionButton = allOf(withId(R.id.our_mission_image_button));

    public static Matcher<View> ourMissionTitle = allOf(withId(R.id.our_mission_title_text_view),
            withText("Love is all"));

    public static Matcher<View> newsListMain = withId(R.id.container_list_news_include_on_fragment_main);
}
