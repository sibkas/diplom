package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.data.TestData;

public class LoginPage {

    //  Статические константы
    private static final int LOGIN_FIELD = R.id.login_text_input_edit_text;
    private static final int PASSWORD_FIELD = R.id.password_text_input_edit_text;
    private static final int ENTER_BUTTON = R.id.enter_button;

    // Геттер для получения ID
    public static int getLoginFieldId() {
        return LOGIN_FIELD;
    }

    // метод login
    public void login() {
        login(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
    }

    //  Основной метод действий
    public void login(String login, String password) {
        onView(withId(LOGIN_FIELD)).perform(clearText(), typeText(login), closeSoftKeyboard());
        onView(withId(PASSWORD_FIELD)).perform(clearText(), typeText(password), closeSoftKeyboard());
        onView(withId(ENTER_BUTTON)).perform(click());
    }

    //  Метод проверки
    public void checkEnterButtonIsDisplayed() {
        onView(withId(ENTER_BUTTON)).check(matches(isDisplayed()));
    }
}