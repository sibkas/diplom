package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.tests.BaseTest.withIndex;

import ru.iteco.fmhandroid.R;

public class ControlPanelPage {


    public void clickAddNewsButton() {
        onView(withId(R.id.add_news_image_view)).perform(click());
    }

    public void fillNewsFields(String category, String title, String date, String time, String description) {
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(replaceText(category), closeSoftKeyboard());
        onView(withId(R.id.news_item_title_text_input_edit_text)).perform(replaceText(title), closeSoftKeyboard());
        onView(withId(R.id.news_item_publish_date_text_input_edit_text)).perform(replaceText(date), closeSoftKeyboard());
        onView(withId(R.id.news_item_publish_time_text_input_edit_text)).perform(replaceText(time), closeSoftKeyboard());
        onView(withId(R.id.news_item_description_text_input_edit_text)).perform(replaceText(description), closeSoftKeyboard());
    }

    public void clickSaveButton() {
        onView(withId(R.id.save_button)).perform(click());
    }

    public void clickEditNewsButton(int index) {

        onView(withIndex(withId(R.id.edit_news_item_image_view), index)).perform(click());
    }

    public void clickDeleteNewsButton(int index) {

        onView(withIndex(withId(R.id.delete_news_item_image_view), index)).perform(click());
    }

    public void confirmDeletion() {

        onView(withId(android.R.id.button1)).perform(click());
    }

    public void editNews(String newTitle, String newDescription) {

        onView(withId(R.id.news_item_title_text_input_edit_text)).perform(replaceText(newTitle), closeSoftKeyboard());

        onView(withId(R.id.news_item_description_text_input_edit_text)).perform(replaceText(newDescription), closeSoftKeyboard());
    }

    public void openControlPanel() {

        onView(withId(R.id.edit_news_material_button)).perform(click());


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }

    public void clickActiveSwitcher() {

        onView(withId(R.id.switcher)).perform(click());
    }

    public void clickFilterButton() {
        onView(withId(R.id.filter_news_material_button)).perform(click());
    }

    public void selectFilterCheckbox(int checkboxId) {

        onView(withId(checkboxId)).perform(click());
    }

    public void clickFilterApplyButton() {
        onView(withId(R.id.filter_button)).perform(click());
    }
}