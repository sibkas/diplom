package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.iteco.fmhandroid.R;

public class NewsTest extends BaseTest {
    @Before
    public void goToNews() {
        mainPage.openNews();
    }

    @After
    public void clearState() {

        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("Main")).perform(click());
    }


    @Test
    public void aboutButtonShouldBeClickableInNews() {
        newsPage.openAboutViaBurger();
        onView(withId(R.id.about_version_title_text_view)).check(matches(isDisplayed()));
    }

    @Test
    public void filterNewsByCategoryTest() {
        newsPage.openFilter();
        newsPage.filterBy("Массаж", "", "");

        BaseTest.waitForElement(R.id.news_list_recycler_view, 5000);
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    public void filterNewsByDateTest() {
        newsPage.openFilter();

        newsPage.filterBy("", "01.05.2025", "31.06.2026");

        newsPage.checkNewsListIsDisplayed();
    }

    @Test
    public void sortNewsByDateButtonTest() {
        mainPage.openNews();
        newsPage.openSort();
        newsPage.checkNewsListIsDisplayed();
    }

    @Test
    public void newsListShouldBeDisplayed() {

        mainPage.openNews();


        BaseTest.waitForElement(R.id.news_list_recycler_view, 10000);


        onView(withId(R.id.news_list_recycler_view))
                .check(matches(isDisplayed()));
    }

    @Test
    public void expandNewsCardTest() {

        BaseTest.waitForElement(R.id.news_list_recycler_view, 10000);

        onView(withIndex(withId(R.id.view_news_item_image_view), 0))
                .perform(click());

        onView(withIndex(withId(R.id.news_item_description_text_view), 0))
                .check(matches(isDisplayed()));
    }
}
