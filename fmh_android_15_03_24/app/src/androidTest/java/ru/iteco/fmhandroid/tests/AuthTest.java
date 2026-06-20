package ru.iteco.fmhandroid.tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.data.TestData;
import ru.iteco.fmhandroid.pages.LoginPage;

@RunWith(AndroidJUnit4.class)
public class AuthTest extends BaseTest {

    @Override
    @Before
    public void setUp() {
        try {

            waitForElement(LoginPage.getLoginFieldId(), 10000);
        } catch (Exception e) {
            mainPage.logoutIfLoggedIn();
            waitForElement(LoginPage.getLoginFieldId(), 10000);
        }
    }

    @Override
    @After
    public void tearDown() {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());


        if (!device.hasObject(By.res("ru.iteco.fmhandroid:id/login_text_input_edit_text"))) {
            mainPage.logoutIfLoggedIn();
            waitForElement(LoginPage.getLoginFieldId(), 5000);
        }
    }

    @Test
    public void successfulLoginTest() {
        loginPage.login();
        mainPage.checkMainPageLoaded();
    }

    @Test
    public void emptyFieldsLoginTest() {
        loginPage.login(TestData.EMPTY, TestData.EMPTY);
        waitForElement(LoginPage.getLoginFieldId(), 5000);


        loginPage.checkEnterButtonIsDisplayed();
    }

    @Test
    public void invalidPasswordLoginTest() {
        loginPage.login(TestData.VALID_LOGIN, TestData.INVALID_PASSWORD);
        loginPage.checkEnterButtonIsDisplayed();
    }

    @Test
    public void invalidLoginLoginTest() {
        loginPage.login(TestData.INVALID_LOGIN, TestData.VALID_PASSWORD);
        loginPage.checkEnterButtonIsDisplayed();
    }
}