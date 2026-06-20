package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static org.hamcrest.Matchers.allOf;

import android.content.Intent;

import androidx.test.espresso.intent.Intents;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AboutTest extends BaseTest {

    @Before
    public void initIntents() {
        Intents.init();
    }

    @After
    public void releaseIntents() {
        Intents.release();
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
        aboutPage.clickBackButton();


        mainPage.checkMainPageLoaded();
    }
}