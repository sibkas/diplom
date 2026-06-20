package ru.iteco.fmhandroid.tests;

import org.junit.Test;

public class QuotesTest extends BaseTest {

    @Test
    public void openQuotesSectionTest() {
        quotesPage.openQuotesSection();
        quotesPage.checkQuotesListIsDisplayed();
    }

    @Test
    public void expandQuoteCardTest() {
        quotesPage.openQuotesSection();
        quotesPage.expandQuote(0);
        quotesPage.checkQuoteDescriptionIsDisplayed(0);
    }
}