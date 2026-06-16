package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.pages.AboutPage;
import ru.iteco.fmhandroid.pages.ControlPanelPage;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.MainPage;
import ru.iteco.fmhandroid.pages.NewsPage;
import ru.iteco.fmhandroid.pages.QuotesPage;
import ru.iteco.fmhandroid.ui.AppActivity;

public class BaseTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    protected LoginPage loginPage = new LoginPage();
    protected MainPage mainPage = new MainPage();
    protected NewsPage newsPage = new NewsPage();
    protected QuotesPage quotesPage = new QuotesPage();
    protected AboutPage aboutPage = new AboutPage();

    @Before
    public void setUp() {

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());


        boolean isLoginPageVisible = device.wait(
                androidx.test.uiautomator.Until.hasObject(By.res("ru.iteco.fmhandroid:id/login_text_input_edit_text")),
                5000
        );

        if (isLoginPageVisible) {
            loginPage.login();

            waitForMainScreen();
        }
    }

    public static void waitForMainScreen() {

        waitForElement(R.id.main_menu_image_button, 20000);
    }

    public static void waitForElement(int viewId, long timeout) {
        waitForElement(withId(viewId), timeout);
    }

    public static void waitForElement(Matcher<View> matcher, long timeout) {
        long endTime = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < endTime) {
            try {

                onView(matcher).check(matches(isDisplayed()));
                return;
            } catch (Throwable e) {

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
            }
        }

        onView(matcher).check(matches(isDisplayed()));
    }

    public static void waitForSplashScreenToDisappear() {

        long endTime = System.currentTimeMillis() + 15000;
        while (System.currentTimeMillis() < endTime) {
            try {

                onView(withId(R.id.splash_screen_circular_progress_indicator)).check(matches(isDisplayed()));
                Thread.sleep(500);
            } catch (Throwable e) {

                return;
            }
        }
    }

    @After
    public void tearDown() {

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        if (!device.hasObject(By.res("ru.iteco.fmhandroid:id/login_text_input_edit_text"))) {
            mainPage.logoutIfLoggedIn();
        }
    }


    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: " + index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }

    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        return new androidx.test.espresso.matcher.BoundedMatcher<View, androidx.recyclerview.widget.RecyclerView>(androidx.recyclerview.widget.RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(androidx.recyclerview.widget.RecyclerView recyclerView) {
                androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

    protected ControlPanelPage controlPanelPage = new ControlPanelPage();
}