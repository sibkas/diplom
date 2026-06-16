package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.contrib.RecyclerViewActions;

import ru.iteco.fmhandroid.R;

public class QuotesPage {

    private int butterflyIconId = R.id.our_mission_image_button;

    private int quotesListId = R.id.our_mission_item_list_recycler_view;

    private int expandButtonId = R.id.our_mission_item_open_card_image_button;

    private int descriptionTextId = R.id.our_mission_item_description_text_view;

    public void openQuotesSection() {
        onView(withId(butterflyIconId)).perform(click());
    }

    public void expandQuote(int position) {

        onView(withId(quotesListId))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
    }
}