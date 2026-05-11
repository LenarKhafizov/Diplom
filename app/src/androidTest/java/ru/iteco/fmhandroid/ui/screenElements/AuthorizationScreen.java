package ru.iteco.fmhandroid.ui.screenElements;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;
public class AuthorizationScreen {
    public static Matcher<View> loginField = allOf(withHint("Login"),
            withParent(withParent(withId(R.id.login_text_input_layout))));

    public static Matcher<View> passwordField = allOf(withHint("Password"),
            withParent(withParent(withId(R.id.password_text_input_layout))));

    public static Matcher<View> enterButton = withId(R.id.enter_button);

    public static Matcher<View> textPageNews = withId(R.id.trademark_image_view);

    public static Matcher<View> logoutButton = allOf(withId(R.id.authorization_image_button),
            withContentDescription("Authorization"));

    public static Matcher<View> logoutConfirm = allOf(withId(android.R.id.title), withText("Log out"));

    public static Matcher<View> authorizationTitle = allOf(withText("Authorization"),
            withParent(withParent(withId(R.id.nav_host_fragment))));

    public static Matcher<View> errorMessage = allOf(withText("Something went wrong. Try again later."));
}
