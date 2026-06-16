package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;

public class AboutPage {
    private int privacyPolicyId = R.id.about_privacy_policy_value_text_view;
    private int termsOfUseId = R.id.about_terms_of_use_value_text_view;

    public void clickPrivacyPolicy() {
        onView(withId(privacyPolicyId)).perform(click());
    }

    public void clickTermsOfUse() {
        onView(withId(termsOfUseId)).perform(click());
    }
}