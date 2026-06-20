package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.tests.BaseTest.atPosition;
import static ru.iteco.fmhandroid.tests.BaseTest.waitForElement;

import ru.iteco.fmhandroid.R;

public class QuotesPage {

    //Статические константы
    private static final int BUTTERFLY_ICON = R.id.our_mission_image_button;
    private static final int QUOTES_LIST = R.id.our_mission_item_list_recycler_view;
    private static final int QUOTE_CARD = R.id.our_mission_item_material_card_view;
    private static final int QUOTE_DESCRIPTION = R.id.our_mission_item_description_text_view;

    //Методы действий
    public void openQuotesSection() {
        onView(withId(BUTTERFLY_ICON)).perform(click());
    }

    public void expandQuote(int index) {
        onView(atPosition(index, withId(QUOTE_CARD))).perform(click());
    }

    public void checkQuotesListIsDisplayed() {
        onView(withId(QUOTES_LIST)).check(matches(isDisplayed()));
    }

    public void checkQuoteDescriptionIsDisplayed(int index) {
        waitForElement(atPosition(index, hasDescendant(withId(QUOTE_DESCRIPTION))), 3000);
        onView(withId(QUOTES_LIST)).check(matches(atPosition(index, hasDescendant(withId(QUOTE_DESCRIPTION)))));
    }
}