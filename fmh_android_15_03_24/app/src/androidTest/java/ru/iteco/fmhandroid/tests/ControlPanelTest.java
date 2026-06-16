package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import org.junit.Test;

import ru.iteco.fmhandroid.R;

public class ControlPanelTest extends BaseTest {
    @Test
    public void addNewsButtonIsClickableTest() {
        mainPage.openNews();
        controlPanelPage.openControlPanel();

        controlPanelPage.clickAddNewsButton();

        onView(withId(R.id.news_item_title_text_input_edit_text)).check(matches(isDisplayed()));
    }

    @Test
    public void createNewsTest() {

        mainPage.openNews();

        controlPanelPage.openControlPanel();


        controlPanelPage.clickAddNewsButton();


        controlPanelPage.fillNewsFields(
                "Объявление",
                "Заголовок теста",
                "15.06.2026",
                "12:00",
                "Описание новости"
        );


        controlPanelPage.clickSaveButton();


        BaseTest.waitForElement(R.id.news_list_recycler_view, 10000);
    }

    @Test
    public void checkDateFormatTest() {
        mainPage.openNews();
        controlPanelPage.openControlPanel();


        BaseTest.waitForElement(R.id.news_item_create_date_text_view, 10000);


        onView(withIndex(withId(R.id.news_item_create_date_text_view), 0))
                .check(matches(isDisplayed()));


        onView(withIndex(withId(R.id.news_item_create_date_text_view), 0))
                .check(matches(withText("15.06.2026")));
    }

    @Test
    public void editNewsTest() {
        mainPage.openNews();
        controlPanelPage.openControlPanel();


        controlPanelPage.clickEditNewsButton(0);


        String updatedTitle = "Обновленный заголовок";
        String updatedDescription = "Обновленное описание новости";
        controlPanelPage.editNews(updatedTitle, updatedDescription);


        controlPanelPage.clickSaveButton();


        onView(withText(updatedTitle)).check(matches(isDisplayed()));
    }

    @Test
    public void deleteNewsTest() {
        mainPage.openNews();
        controlPanelPage.openControlPanel();


        controlPanelPage.clickDeleteNewsButton(0);


        controlPanelPage.confirmDeletion();


        BaseTest.waitForElement(R.id.news_list_recycler_view, 10000);
    }

    @Test
    public void createNewsActiveSwitcherTest() {
        mainPage.openNews();
        controlPanelPage.openControlPanel();
        controlPanelPage.clickAddNewsButton();


        controlPanelPage.fillNewsFields(
                "Объявление",
                "Неактивная новость",
                "15.06.2026",
                "12:00",
                "Описание новости"
        );


        controlPanelPage.clickActiveSwitcher();

        onView(withId(R.id.filter_news_active_material_check_box))
                .check(matches(isChecked()));


        controlPanelPage.clickSaveButton();


        mainPage.openNews();


        BaseTest.waitForElement(R.id.news_list_recycler_view, 5000);


        onView(allOf(withText("Неактивная новость"), isDescendantOfA(withId(R.id.news_list_recycler_view))))
                .check(doesNotExist());
    }

    @Test
    public void createNewsWithEmptyFieldsTest() {
        mainPage.openNews();
        controlPanelPage.openControlPanel();


        onView(withId(R.id.add_news_image_view))
                .check(matches(isDisplayed()))
                .perform(click());

        controlPanelPage.clickSaveButton();
        onView(withId(R.id.save_button)).check(matches(isDisplayed()));
    }

    @Test
    public void editNewsActiveSwitcherTest() {
        mainPage.openNews();
        controlPanelPage.openControlPanel();


        controlPanelPage.clickEditNewsButton(0);


        controlPanelPage.clickActiveSwitcher();
        controlPanelPage.clickSaveButton();


        mainPage.openNews();

    }

    @Test
    public void filterByActiveNewsTest() {
        mainPage.openNews();
        controlPanelPage.openControlPanel();
        controlPanelPage.clickFilterButton();


        controlPanelPage.selectFilterCheckbox(R.id.filter_news_active_material_check_box);
        controlPanelPage.clickFilterApplyButton();


        BaseTest.waitForElement(R.id.news_list_recycler_view, 5000);
        onView(withId(R.id.news_list_recycler_view))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void filterByNotActiveNewsTest() {
        mainPage.openNews();
        controlPanelPage.openControlPanel();
        controlPanelPage.clickFilterButton();


        controlPanelPage.selectFilterCheckbox(R.id.filter_news_inactive_material_check_box);
        controlPanelPage.clickFilterApplyButton();


        BaseTest.waitForElement(R.id.news_list_recycler_view, 5000);


        onView(withId(R.id.news_list_recycler_view))
                .check(matches(hasMinimumChildCount(1)));

    }
}
