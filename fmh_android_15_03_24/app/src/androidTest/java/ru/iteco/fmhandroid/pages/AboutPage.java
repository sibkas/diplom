package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;

public class AboutPage {

    // Статические константы
    private static final int PRIVACY_POLICY_LINK = R.id.about_privacy_policy_value_text_view;
    private static final int TERMS_OF_USE_LINK = R.id.about_terms_of_use_value_text_view;
    private static final int BACK_BUTTON = R.id.about_back_image_button;
    private static final int VERSION_TITLE = R.id.about_version_title_text_view;

    // Методы действий
    public void clickPrivacyPolicy() {
        onView(withId(PRIVACY_POLICY_LINK)).perform(click());
    }

    public void clickTermsOfUse() {
        onView(withId(TERMS_OF_USE_LINK)).perform(click());
    }

    public void clickBackButton() {
        onView(withId(BACK_BUTTON)).perform(click());
    }

    public void checkVersionDisplayed() {
        onView(withId(VERSION_TITLE)).check(matches(isDisplayed()));
    }
}