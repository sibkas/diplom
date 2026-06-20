package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import androidx.test.espresso.ViewInteraction;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.tests.BaseTest;

public class MainPage {

    // Статические константы
    private static final int MENU_BUTTON = R.id.main_menu_image_button;
    private static final int NEWS_LIST = R.id.news_list_recycler_view;
    private static final int AUTH_BUTTON = R.id.authorization_image_button;
    private static final int TRADEMARK = R.id.trademark_image_view;

    public void openNews() {
        onView(withId(MENU_BUTTON)).perform(click());
        onView(withText("News")).perform(click());
        BaseTest.waitForElement(NEWS_LIST, 10000);
    }

    public void openAbout() {
        onView(withId(MENU_BUTTON)).perform(click());
        onView(withText("About")).perform(click());
    }

    public void logout() {
        try {
            waitForView(AUTH_BUTTON, 5000);
            onView(withId(AUTH_BUTTON)).perform(click());
            onView(withText("Log out")).perform(click());
        } catch (Exception e) {

        }
    }

    public void logoutIfLoggedIn() {
        try {
            onView(withId(AUTH_BUTTON)).check(matches(isDisplayed()));
            onView(withId(AUTH_BUTTON)).perform(click());
            onView(withText("Log out")).perform(click());
        } catch (Exception | AssertionError e) {

        }
    }

    public void returnToMain() {
        onView(withId(MENU_BUTTON)).perform(click());
        onView(withText("Main")).perform(click());
    }

    public void checkMainPageLoaded() {
        waitForView(TRADEMARK, 5000);
    }

    public static ViewInteraction waitForView(int viewId, long timeout) {
        await().atMost(timeout, MILLISECONDS).untilAsserted(() ->
                onView(withId(viewId)).check(matches(isDisplayed()))
        );
        return onView(withId(viewId));
    }
}