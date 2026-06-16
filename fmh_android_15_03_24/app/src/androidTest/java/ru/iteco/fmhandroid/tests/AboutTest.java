package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import android.content.Intent;

import androidx.test.espresso.intent.Intents;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.iteco.fmhandroid.R;

public class AboutTest extends BaseTest {

    @Before
    public void initIntents() {
        Intents.init(); // Инициализируем перехват Intent-ов
    }

    @After
    public void releaseIntents() {
        Intents.release(); // Обязательно закрываем
    }

    @Test
    public void privacyPolicyLinkTest() {
        mainPage.openAbout();
        aboutPage.clickPrivacyPolicy();


        intended(allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData("https://vhospice.org/#/privacy-policy")
        ));
    }

    @Test
    public void backButtonTest() {

        mainPage.openAbout();


        onView(withId(R.id.about_back_image_button)).perform(click());


        onView(withId(R.id.main_menu_image_button))
                .check(matches(isDisplayed()));
    }
}