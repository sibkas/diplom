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
import static ru.iteco.fmhandroid.tests.BaseTest.withIndex;

import ru.iteco.fmhandroid.R;

public class NewsPage {

    // Статические константы
    private static final int MAIN_MENU_BUTTON = R.id.main_menu_image_button;
    private static final int FILTER_BUTTON = R.id.filter_news_material_button;
    private static final int SORT_BUTTON = R.id.sort_news_material_button;
    private static final int CATEGORY_FIELD = R.id.news_item_category_text_auto_complete_text_view;
    private static final int DATE_START_FIELD = R.id.news_item_publish_date_start_text_input_edit_text;
    private static final int DATE_END_FIELD = R.id.news_item_publish_date_end_text_input_edit_text;
    private static final int FILTER_OK_BUTTON = R.id.filter_button;
    private static final int NEWS_LIST = R.id.news_list_recycler_view;
    private static final int VIEW_BUTTON = R.id.view_news_item_image_view;
    private static final int DESCRIPTION_TEXT = R.id.news_item_description_text_view;

    // Методы действий
    public void openNewsViaBurger() {
        onView(withId(MAIN_MENU_BUTTON)).perform(click());
        onView(withText("News")).perform(click());
    }

    public void openAboutViaBurger() {
        onView(withId(MAIN_MENU_BUTTON)).perform(click());
        onView(withText("About")).perform(click());
    }

    public void openFilter() {
        waitForElement(NEWS_LIST, 10000);
        onView(withId(FILTER_BUTTON)).perform(click());
    }

    public void filterBy(String category, String dateStart, String dateEnd) {
        onView(withId(CATEGORY_FIELD)).perform(replaceText(category), closeSoftKeyboard());
        onView(withId(DATE_START_FIELD)).perform(replaceText(dateStart), closeSoftKeyboard());
        onView(withId(DATE_END_FIELD)).perform(replaceText(dateEnd), closeSoftKeyboard());
        onView(withId(FILTER_OK_BUTTON)).perform(click());
    }

    public void openSort() {
        onView(withId(SORT_BUTTON)).perform(click());
    }

    public void checkNewsListIsDisplayed() {
        onView(withId(NEWS_LIST)).check(matches(isDisplayed()));
    }

    public void expandNewsCard(int index) {
        onView(withIndex(withId(VIEW_BUTTON), index)).perform(click());
    }

    public void checkDescriptionIsDisplayed(int index) {
        onView(withIndex(withId(DESCRIPTION_TEXT), index)).check(matches(isDisplayed()));
    }
}