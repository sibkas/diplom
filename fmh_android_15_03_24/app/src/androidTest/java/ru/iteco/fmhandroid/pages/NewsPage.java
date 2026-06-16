package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.tests.BaseTest.waitForElement;

import ru.iteco.fmhandroid.R;

public class NewsPage {
    private final int mainMenuButton = R.id.main_menu_image_button;

    private final String newsMenuItemText = "News";
    private final String aboutMenuItemText = "About";

    private final int filterButton = R.id.filter_news_material_button;
    private final int categoryField = R.id.news_item_category_text_auto_complete_text_view;
    private final int dateStartField = R.id.news_item_publish_date_start_text_input_edit_text;
    private final int dateEndField = R.id.news_item_publish_date_end_text_input_edit_text;
    private final int filterOkButton = R.id.filter_button;
    private final int newsList = R.id.news_list_recycler_view;

    public void openNewsViaBurger() {
        onView(withId(mainMenuButton)).perform(click());
        onView(withText(newsMenuItemText)).perform(click());
    }

    public void openAboutViaBurger() {
        onView(withId(mainMenuButton)).perform(click());
        onView(withText(aboutMenuItemText)).perform(click());
    }

    public void openFilter() {

        waitForElement(R.id.news_list_recycler_view, 10000);
        waitForElement(R.id.filter_news_material_button, 5000);
        onView(withId(R.id.filter_news_material_button)).perform(click());
    }

    public void filterBy(String category, String dateStart, String dateEnd) {
        onView(withId(categoryField)).perform(replaceText(category), closeSoftKeyboard());
        onView(withId(dateStartField)).perform(replaceText(dateStart));
        onView(withId(dateEndField)).perform(replaceText(dateEnd));
        onView(withId(filterOkButton)).perform(click());
    }

    public void checkNewsListIsDisplayed() {
        onView(withId(newsList)).check(matches(isDisplayed()));
    }

    public void openSort() {
        waitForElement(R.id.news_list_recycler_view, 10000);
        waitForElement(R.id.filter_news_material_button, 5000);
        onView(withId(R.id.sort_news_material_button)).perform(click());
    }
}