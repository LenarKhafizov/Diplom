package ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import static ru.iteco.fmhandroid.ui.data.ChildAtPositionMatcher.childAtPosition;

import android.view.View;

import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class NewsScreen {
    public static Matcher<View> deleteNewsButton = withId(R.id.delete_news_item_image_view);
    public static Matcher<View> confirmDeleteButton = withId(android.R.id.button1);
    public static Matcher<View> newsRecyclerView = withId(R.id.news_list_recycler_view);
    public static Matcher<View> menuButton = allOf(withId(R.id.main_menu_image_button),
            withContentDescription("Main menu"));

    public static Matcher<View> newsButton = allOf(withId(android.R.id.title), withText("News"));

    public static Matcher<View> addNewsControlPanelButton = allOf(withId(R.id.add_news_image_view),
            withContentDescription("Add news button"),
            childAtPosition(
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")),
                            1),
                    3));

    public static Matcher<View> addNewsButton = allOf(withId(R.id.edit_news_material_button),
            childAtPosition(
                    childAtPosition(
                            withId(R.id.container_list_news_include),
                            0),
                    3));

    public static Matcher<View> categoryDropdown = allOf(withContentDescription("Show dropdown menu"),
            childAtPosition(
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")),
                            1),
                    0));

    public static Matcher<View> choiceCategory = allOf(withId(R.id.news_item_category_text_auto_complete_text_view),
            childAtPosition(
                    childAtPosition(
                            withId(R.id.news_item_category_text_input_layout),
                            0),
                    0));

    public static Matcher<View> title = withId(R.id.news_item_title_text_input_edit_text);

    public static Matcher<View> dataButton = withId(R.id.news_item_publish_date_text_input_edit_text);

    public static Matcher<View> timeButton = allOf(withId(R.id.news_item_publish_time_text_input_edit_text),
            childAtPosition(childAtPosition(
                            withId(R.id.news_item_publish_time_text_input_layout),
                            0),
                    1));

    public static Matcher<View> okButton = allOf(withId(android.R.id.button1),
            withText("OK"),
            childAtPosition(childAtPosition(
                            withClassName(is("android.widget.ScrollView")),
                            0),
                    3));

    public static Matcher<View> description = withId(R.id.news_item_description_text_input_edit_text);

    public static Matcher<View> saveButton = allOf(withId(R.id.save_button),
            withText("Save"),
            withContentDescription("Save"),
            childAtPosition(
                    childAtPosition(
                            withClassName(is("com.google.android.material.card.MaterialCardView")),
                            0),
                    6));

    public static Matcher<View> sortButton = withId(R.id.sort_news_material_button);

    public static Matcher<View> openDescriptionButton = withId(R.id.news_list_recycler_view);

    public static Matcher<View> textDescription = allOf(withId(R.id.news_item_description_text_view),
            withText("Объявление"),
            withParent(withParent(withId(R.id.news_item_material_card_view))));

    public static Matcher<View> openFirstNewsButton = allOf(withId(R.id.news_list_recycler_view),
            childAtPosition(
                    withId(R.id.all_news_cards_block_constraint_layout),
                    0));

    public static Matcher<View> getNewsDescriptionByText(String expectedDescription) {
        return allOf(
                withId(R.id.news_item_description_text_view),
                withText(expectedDescription),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
        );
    }
}
