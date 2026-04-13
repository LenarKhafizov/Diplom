package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.ViewMatchersUtil;

public class AuthorizationPage {
    public Matcher<View> getAuthorLoginField;
    public Matcher<View> getAuthorPasswordField;
    public Matcher<View> getEnterButton;
    public String getTextPageAuthorizationEmpty;
    public String validLogin = "login2";
    public String validPassword = "password2";
    public String simbols = "{} [] , /";
    public Matcher<View> textPageNews;
    public Matcher<View> getButtonLogOut;

    public Matcher<View> getButtonLogout2;

    public Matcher<View> getTextPageAuthorization;

    public Matcher<View> getAuthorizationImage;

    public Matcher<View> getTextNotAuthorization;



    public AuthorizationPage() {
        getAuthorLoginField = allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout))));
        getAuthorPasswordField = allOf(withHint("Password"), withParent(withParent(withId(R.id.password_text_input_layout))));
        getEnterButton = withId(R.id.enter_button);
        validLogin = validLogin;
        validPassword = validPassword;
        simbols = simbols;
        textPageNews = withId(R.id.trademark_image_view);
        getButtonLogOut =
                allOf(withId(R.id.authorization_image_button), withContentDescription("Authorization"));
        getButtonLogout2 = allOf(withId(android.R.id.title), withText("Log out"));
        getTextPageAuthorization =
                allOf(withText("Authorization"),
                        withParent(withParent(withId(R.id.nav_host_fragment))));
        getTextPageAuthorizationEmpty = "Login and password cannot be empty";

        getAuthorizationImage = withId(R.id.authorization_image_button);

        getTextNotAuthorization =allOf( withText("Something went wrong. Try again later."));

    }

    public void login (String login ,String password){
        ViewMatchersUtil.waitForView(getAuthorLoginField , 8000);
        onView(getAuthorLoginField).check(matches(isDisplayed()));
        onView(getAuthorLoginField).perform(replaceText(login), closeSoftKeyboard());
        onView(getAuthorPasswordField).check(matches(isDisplayed()));
        onView(getAuthorPasswordField).perform(replaceText(password), closeSoftKeyboard());
        onView(getEnterButton).check(matches(isDisplayed()));
        onView(getEnterButton).perform(click());
    }
    public void logOut (){
        ViewMatchersUtil.waitForView(getButtonLogOut , 8000);
        onView(getButtonLogOut).check(matches(isDisplayed()));
        onView(getButtonLogOut).perform(click());
        ViewMatchersUtil.waitForView(getButtonLogout2 , 8000);
        onView(getButtonLogout2).check(matches(isDisplayed()));
        onView(getButtonLogout2).perform(click());
    }
}
