package ru.iteco.fmhandroid.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.iteco.fmhandroid.data.TestData;

public class NewsTest extends BaseTest {

    @Before
    public void goToNews() {
        mainPage.openNews();
    }

    @After
    public void clearState() {

        mainPage.returnToMain();
    }

    @Test
    public void aboutButtonShouldBeClickableInNews() {
        newsPage.openAboutViaBurger();
        aboutPage.checkVersionDisplayed();
    }

    @Test
    public void filterNewsByCategoryTest() {
        newsPage.openFilter();
        newsPage.filterBy(TestData.CATEGORY_MASSAGE, TestData.EMPTY, TestData.EMPTY);
        newsPage.checkNewsListIsDisplayed();
    }

    @Test
    public void filterNewsByDateTest() {
        newsPage.openFilter();
        newsPage.filterBy(TestData.EMPTY, TestData.getPastDate(), TestData.getTodayDate());
        newsPage.checkNewsListIsDisplayed();
    }

    @Test
    public void sortNewsByDateButtonTest() {
        newsPage.openSort();
        newsPage.checkNewsListIsDisplayed();
    }

    @Test
    public void newsListShouldBeDisplayed() {
        newsPage.checkNewsListIsDisplayed();
    }

    @Test
    public void expandNewsCardTest() {
        newsPage.expandNewsCard(0);
        newsPage.checkDescriptionIsDisplayed(0);
    }
}