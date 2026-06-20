package ru.iteco.fmhandroid.tests;

import org.junit.Test;
import ru.iteco.fmhandroid.data.TestData;

public class ControlPanelTest extends BaseTest {

    @Test
    public void addNewsButtonIsClickableTest() {
        mainPage.openNews();
        controlPanelPage.openControlPanel();
        controlPanelPage.clickAddNewsButton();
        controlPanelPage.checkTitleFieldIsDisplayed();
    }

    @Test
    public void createNewsTest() {
        mainPage.openNews();
        controlPanelPage.openControlPanel();
        controlPanelPage.clickAddNewsButton();
        controlPanelPage.fillNewsFields(
                TestData.NEWS_CATEGORY,
                TestData.NEWS_TITLE,
                TestData.NEWS_DATE,
                TestData.NEWS_TIME,
                TestData.NEWS_DESCRIPTION
        );
        controlPanelPage.clickSaveButton();
        controlPanelPage.waitForNewsList(10000);
    }

    @Test
    public void checkDateFormatTest() {
        mainPage.openNews();
        controlPanelPage.openControlPanel();
        controlPanelPage.waitForNewsList(10000);
        controlPanelPage.checkDateIsDisplayed(0, TestData.NEWS_DATE);
    }

    @Test
    public void editNewsTest() {
        mainPage.openNews();
        controlPanelPage.openControlPanel();
        controlPanelPage.clickEditNewsButton(0);

        String updatedTitle = TestData.NEWS_TITLE + "_UPD";
        controlPanelPage.editNews(updatedTitle, TestData.NEWS_DESCRIPTION);
        controlPanelPage.clickSaveButton();

        controlPanelPage.checkNewsExists(updatedTitle);
    }

    @Test
    public void deleteNewsTest() {
        mainPage.openNews();
        controlPanelPage.openControlPanel();
        controlPanelPage.clickDeleteNewsButton(0);
        controlPanelPage.confirmDeletion();
        controlPanelPage.waitForNewsList(10000);
    }

    @Test
    public void createNewsActiveSwitcherTest() {
        mainPage.openNews();
        controlPanelPage.openControlPanel();
        controlPanelPage.clickAddNewsButton();

        controlPanelPage.fillNewsFields(
                TestData.NEWS_CATEGORY, "Неактивная новость",
                TestData.NEWS_DATE, TestData.NEWS_TIME, TestData.NEWS_DESCRIPTION
        );
        controlPanelPage.clickActiveSwitcher();
        controlPanelPage.checkActiveCheckBoxIsChecked();
        controlPanelPage.clickSaveButton();

        mainPage.openNews();
        controlPanelPage.waitForNewsList(5000);
        controlPanelPage.checkNewsWithTitleDoesNotExist("Неактивная новость");
    }

    @Test
    public void createNewsWithEmptyFieldsTest() {
        mainPage.openNews();
        controlPanelPage.openControlPanel();
        controlPanelPage.clickAddNewsButton();
        controlPanelPage.fillNewsFields(
                TestData.EMPTY, TestData.EMPTY, TestData.EMPTY, TestData.EMPTY, TestData.EMPTY
        );
        controlPanelPage.clickSaveButton();
        controlPanelPage.checkSaveButtonIsDisplayed();
    }

    @Test
    public void filterByActiveNewsTest() {
        mainPage.openNews();
        controlPanelPage.openControlPanel();
        controlPanelPage.clickFilterButton();
        controlPanelPage.selectFilterCheckbox(controlPanelPage.getActiveCheckBoxId());
        controlPanelPage.clickFilterApplyButton();
        controlPanelPage.waitForNewsList(5000);
        controlPanelPage.checkNewsListHasItems(1);
    }
}