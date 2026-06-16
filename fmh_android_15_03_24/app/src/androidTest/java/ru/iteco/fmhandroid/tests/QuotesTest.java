package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import org.junit.Test;

import ru.iteco.fmhandroid.R;

public class QuotesTest extends BaseTest {
    @Test
    public void openQuotesSectionTest() {
        quotesPage.openQuotesSection();


        onView(withId(R.id.our_mission_item_list_recycler_view))
                .check(matches(isDisplayed()));
    }

    @Test
    public void expandQuoteCardTest() {
        quotesPage.openQuotesSection();


        quotesPage.expandQuote(0);


        quotesPage.expandQuote(0);

        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        onView(withId(R.id.our_mission_item_list_recycler_view))
                .check(matches(atPosition(0, hasDescendant(withId(R.id.our_mission_item_description_text_view)))));
    }
}