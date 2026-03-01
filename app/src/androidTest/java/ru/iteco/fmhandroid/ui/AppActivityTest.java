package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;

//import androidx.test.espresso.IdlingRegistry;
//import androidx.test.espresso.IdlingResource;
import android.view.View;
import android.widget.EditText;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

//import org.junit.After;
//import org.junit.Before;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppActivityTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

//    @Before
  //  public void setUp() {
    //    IdlingRegistry.getInstance().register((IdlingResource) EspressoIdlingResources.getIdlingResource());
   // }

//    @After
  //  public void tearDown() {
    //    IdlingRegistry.getInstance().unregister();
    //}

    @Test
    public void testActivityLaunches() {
        // Самый простой тест - просто проверяем, что активити запустилась
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Проверяем наличие любого view
        onView(withId(android.R.id.content)).check(matches(isDisplayed()));
    }

    @Test
    public void appActivityTest() throws InterruptedException {
        // onView(isRoot()).perform(waitDisplayed(R.id.login_text_input_layout, 5000));

        // Нажатие на TextInputLayout для отображения клавиатуры
        onView(allOf(withId(R.id.login_text_input_layout), isDisplayed())).perform(click());

        // Поиск EditText внутри login_text_input_layout и ввод текста
        onView(allOf(withClassName(Matchers.equalTo(EditText.class.getName())),
                withParent(withId(R.id.login_text_input_layout))))
                .perform(replaceText("login2"), closeSoftKeyboard());

        // Аналогично для password_text_input_layout
        onView(allOf(withClassName(Matchers.equalTo(EditText.class.getName())),
                withParent(withId(R.id.password_text_input_layout))))
                .perform(replaceText("password2"), closeSoftKeyboard());

        // Далее нажимаем кнопку входа
        onView(allOf(withId(R.id.enter_button), isDisplayed())).perform(click());

        // Проверяем наличие логотипа приложения
        onView(allOf(withId(R.id.trademark_image_view), isDisplayed()))
                .check(matches(isDisplayed()));

        // onView(isRoot()).perform(waitDisplayed(R.id.trademark_image_view, 5000));

        // onView(allOf(withId(R.id.trademark_image_view),isDisplayed())).
           //     check(matches(isDisplayed()));

        // Проверка, что главный экран загрузился
        onView(allOf(withId(R.id.container_list_news_include), isDisplayed()))
                .check(matches(isDisplayed()));
    }

    public static ViewAction waitDisplayed(final int viewId, final long millis) {
        return new ViewAction() {
            @Override
            public org.hamcrest.Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> has been displayed during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> matchId = withId(viewId);
                final org.hamcrest.Matcher<View> matchDisplayed = isDisplayed();

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        if (matchId.matches(child) && matchDisplayed.matches(child)) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);

                // timeout happens
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }
}
