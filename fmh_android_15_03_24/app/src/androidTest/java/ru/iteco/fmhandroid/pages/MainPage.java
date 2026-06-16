package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.tests.BaseTest;


public class MainPage {

    public void openNews() {

        onView(withId(R.id.main_menu_image_button)).perform(click());


        onView(withText("News")).perform(click());


        BaseTest.waitForElement(R.id.news_list_recycler_view, 10000);
    }

    public void openAbout() {

        onView(withId(R.id.main_menu_image_button)).perform(click());


        onView(withText("About")).perform(click());
    }

    public void logout() {
        try {

            waitForView(R.id.authorization_image_button, 5000);

            onView(withId(R.id.authorization_image_button)).perform(click());
            onView(withText("Log out")).perform(click());
        } catch (Exception e) {

        }
    }

    public static ViewInteraction waitForView(int viewId, long timeout) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + timeout;

        do {
            try {
                return onView(withId(viewId)).check(matches(isDisplayed()));
            } catch (Exception | AssertionError e) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
            }
        } while (System.currentTimeMillis() < endTime);

        return onView(withId(viewId));
    }


    public void checkMainPageLoaded() {
        waitForView(R.id.trademark_image_view, 5000);
    }

    public void logoutIfLoggedIn() {
        try {

            onView(withId(R.id.authorization_image_button)).check(matches(isDisplayed()));
            onView(withId(R.id.authorization_image_button)).perform(click());
            onView(withText("Log out")).perform(click());
        } catch (Exception | AssertionError e) {

        }
    }
}