package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.util.HumanReadables.describe;
import static ru.iteco.fmhandroid.ui.screenElements.NewsScreen.*;

import android.view.View;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.ViewMatchersUtil;

public class NewsSteps {
    public void openNewsPanel() {
        onView(menuButton).check(matches(isDisplayed()));
        onView(menuButton).perform(click());

        onView(newsButton).check(matches(isDisplayed()));
        onView(newsButton).perform(click());

        ViewMatchersUtil.waitForView(addNewsButton, 6000);
        onView(addNewsButton).check(matches(isDisplayed()));
        onView(addNewsButton).perform(click());
    }

    public void addNews(String category, String titleText, String textDescription) {
        ViewMatchersUtil.waitForView(addNewsControlPanelButton, 6000);
        onView(addNewsControlPanelButton).check(matches(isDisplayed()));
        onView(addNewsControlPanelButton).perform(click());

        ViewMatchersUtil.waitForView(categoryDropdown, 6000);
        onView(categoryDropdown).check(matches(isDisplayed()));
        onView(categoryDropdown).perform(click());

        ViewMatchersUtil.waitForView(choiceCategory, 6000);
        onView(choiceCategory).check(matches(isDisplayed()));
        onView(choiceCategory).perform(replaceText(category), closeSoftKeyboard());

        onView(title).check(matches(isDisplayed()));
        onView(title).perform(replaceText(titleText));

        onView(dataButton).check(matches(isDisplayed()));
        onView(dataButton).perform(click());

        onView(okButton).check(matches(isDisplayed()));
        onView(okButton).perform(click());

        onView(timeButton).check(matches(isDisplayed()));
        onView(timeButton).perform(click());

        onView(okButton).check(matches(isDisplayed()));
        onView(okButton).perform(click());

        onView(description).check(matches(isDisplayed()));
        onView(description).perform(replaceText(textDescription));

        onView(saveButton).check(matches(isDisplayed()));
        onView(saveButton).perform(click());
    }

    public void openNewsByTitle(String title) {
        ViewMatchersUtil.waitForView(newsRecyclerView, 5000);
        onView(withText(title)).perform(click());
    }

    public void verifyNewsDescription(String expectedDescription) {
        ViewMatchersUtil.waitForView(getNewsDescriptionByText(expectedDescription), 5000);
        onView(getNewsDescriptionByText(expectedDescription)).check(matches(isDisplayed()));
    }

    public void deleteFirstNews() {
        ViewMatchersUtil.waitForView(newsRecyclerView, 5000);
        onView(newsRecyclerView).perform(
                actionOnItemAtPosition(0, clickOnViewWithId(R.id.delete_news_item_image_view))
        );
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void confirmDelete() {
        ViewMatchersUtil.waitForView(confirmDeleteButton, 5000);

        onView(confirmDeleteButton).perform(click());
    }

    public void checkNewsListDisplayed() {
        ViewMatchersUtil.waitForView(openDescriptionButton, 5000);
        onView(openDescriptionButton).check(matches(isDisplayed()));
    }

    public void clickSortButton() {
        onView(sortButton).check(matches(isDisplayed()));
        onView(sortButton).perform(click());
    }

    private ViewAction clickOnViewWithId(final int viewId) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(View.class);
            }

            @Override
            public String getDescription() {
                return "Click on view with ID: " + viewId;
            }

            @Override
            public void perform(UiController uiController, View view) {
                View targetView = view.findViewById(viewId);
                if (targetView != null && targetView.isClickable()) {
                    targetView.performClick();
                } else {
                    throw new PerformException.Builder()
                            .withActionDescription(this.getDescription())
                            .withViewDescription(describe(view))
                            .withCause(new IllegalStateException("View with ID " + viewId + " not found or not clickable"))
                            .build();
                }
            }
        };
    }
}
