package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.tests.BaseTest.waitForElement;
import static ru.iteco.fmhandroid.tests.BaseTest.withIndex;

import ru.iteco.fmhandroid.R;

public class ControlPanelPage {
    private final int activeCheckBox = R.id.filter_news_active_material_check_box;

    // селекторы в константы
    private final int addNewsButton = R.id.add_news_image_view;
    private final int categoryField = R.id.news_item_category_text_auto_complete_text_view;
    private final int titleField = R.id.news_item_title_text_input_edit_text;
    private final int dateField = R.id.news_item_publish_date_text_input_edit_text;
    private final int timeField = R.id.news_item_publish_time_text_input_edit_text;
    private final int descriptionField = R.id.news_item_description_text_input_edit_text;
    private final int saveButton = R.id.save_button;
    private final int editNewsButton = R.id.edit_news_item_image_view;
    private final int deleteNewsButton = R.id.delete_news_item_image_view;
    private final int confirmButton = android.R.id.button1;
    private final int controlPanelButton = R.id.edit_news_material_button;
    private final int activeSwitcher = R.id.switcher;
    private final int filterButton = R.id.filter_news_material_button;
    private final int filterApplyButton = R.id.filter_button;
    private final int newsListRecyclerView = R.id.news_list_recycler_view;
    private final int createDateTextView = R.id.news_item_create_date_text_view;

    // константы в методах
    public void clickAddNewsButton() {
        onView(withId(addNewsButton)).perform(click());
    }

    public void fillNewsFields(String category, String title, String date, String time, String description) {
        onView(withId(categoryField)).perform(replaceText(category), closeSoftKeyboard());
        onView(withId(titleField)).perform(replaceText(title), closeSoftKeyboard());
        onView(withId(dateField)).perform(replaceText(date), closeSoftKeyboard());
        onView(withId(timeField)).perform(replaceText(time), closeSoftKeyboard());
        onView(withId(descriptionField)).perform(replaceText(description), closeSoftKeyboard());
    }

    public void clickSaveButton() {
        onView(withId(saveButton)).perform(click());
    }

    public void clickEditNewsButton(int index) {
        onView(withIndex(withId(editNewsButton), index)).perform(click());
    }

    public void clickDeleteNewsButton(int index) {
        onView(withIndex(withId(deleteNewsButton), index)).perform(click());
    }

    public void confirmDeletion() {
        onView(withId(confirmButton)).perform(click());
    }

    public void editNews(String newTitle, String newDescription) {
        onView(withId(titleField)).perform(replaceText(newTitle), closeSoftKeyboard());
        onView(withId(descriptionField)).perform(replaceText(newDescription), closeSoftKeyboard());
    }

    public void openControlPanel() {
        onView(withId(controlPanelButton)).perform(click());
        waitForElement(addNewsButton, 3000);
    }

    public void clickActiveSwitcher() {
        onView(withId(activeSwitcher)).perform(click());
    }

    public void clickFilterButton() {
        onView(withId(filterButton)).perform(click());
    }

    public void selectFilterCheckbox(int checkboxId) {
        onView(withId(checkboxId)).perform(click());
    }

    public void clickFilterApplyButton() {
        onView(withId(filterApplyButton)).perform(click());
    }
    // Методы проверок
    public void checkTitleFieldIsDisplayed() {
        onView(withId(titleField)).check(matches(isDisplayed()));
    }

    public void waitForNewsList(long timeout) {
        waitForElement(newsListRecyclerView, timeout);
    }

    public void checkDateIsDisplayed(int index, String expectedDate) {
        onView(withIndex(withId(createDateTextView), index))
                .check(matches(isDisplayed()));
        onView(withIndex(withId(createDateTextView), index))
                .check(matches(withText(expectedDate)));
    }

    public void checkActiveCheckBoxIsChecked() {
        onView(withId(activeCheckBox)).check(matches(isChecked()));
    }

    public void checkNewsWithTitleDoesNotExist(String title) {
        onView(allOf(withText(title), isDescendantOfA(withId(R.id.news_list_recycler_view))))
                .check(doesNotExist());
    }

    public void checkSaveButtonIsDisplayed() {
        onView(withId(saveButton)).check(matches(isDisplayed()));
    }

    public void checkNewsListHasItems(int minCount) {
        onView(withId(R.id.news_list_recycler_view)).check(matches(hasMinimumChildCount(minCount)));
    }

    public void checkNewsExists(String title) {
        onView(withText(title)).check(matches(isDisplayed()));
    }

    // Геттер для получения ID чекбокса
    public int getActiveCheckBoxId() {
        return activeCheckBox;
    }
}