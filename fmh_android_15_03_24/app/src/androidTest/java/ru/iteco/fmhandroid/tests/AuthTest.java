package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.pages.LoginPage;

@RunWith(AndroidJUnit4.class)
public class AuthTest extends BaseTest {
    @Override
    @Before
    public void setUp() {

        try {
            waitForElement(R.id.login_text_input_edit_text, 10000);
        } catch (Exception e) {

            mainPage.logoutIfLoggedIn();
            waitForElement(R.id.login_text_input_edit_text, 10000);
        }
    }

    @Override
    @After
    public void tearDown() {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());


        if (!device.hasObject(By.res("ru.iteco.fmhandroid:id/login_text_input_edit_text"))) {
            mainPage.logoutIfLoggedIn();


            waitForElement(R.id.login_text_input_edit_text, 5000);
        }
    }


    @Test
    public void successfulLoginTest() {
        loginPage.login();
        mainPage.checkMainPageLoaded();
    }

    @Test
    public void emptyFieldsLoginTest() {
        loginPage.login(LoginPage.EMPTY, LoginPage.EMPTY);


        waitForElement(R.id.login_text_input_edit_text, 5000);

        onView(withId(R.id.login_text_input_edit_text))
                .check(matches(isDisplayed()));
    }

    @Test
    public void invalidPasswordLoginTest() {
        loginPage.login(LoginPage.VALID_LOGIN, LoginPage.INVALID_PASSWORD);
        onView(withId(R.id.enter_button)).check(matches(isDisplayed()));
    }

    @Test
    public void invalidLoginLoginTest() {
        loginPage.login(LoginPage.INVALID_LOGIN, LoginPage.VALID_PASSWORD);
        onView(withId(R.id.enter_button)).check(matches(isDisplayed()));
    }
}