package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;

public class LoginPage {

    public static final String VALID_LOGIN = "login2";
    public static final String VALID_PASSWORD = "password2";


    public static final String INVALID_LOGIN = "wrong_login";
    public static final String INVALID_PASSWORD = "wrong_password";
    public static final String EMPTY = "";


    private final int loginField = R.id.login_text_input_edit_text;
    private final int passwordField = R.id.password_text_input_edit_text;
    private final int enterButton = R.id.enter_button;


    public void login() {

        login(VALID_LOGIN, VALID_PASSWORD);
    }


    public void login(String login, String password) {
        onView(withId(loginField))
                .perform(clearText(), typeText(login));

        onView(withId(passwordField))
                .perform(clearText(), typeText(password));

        onView(withId(enterButton))
                .perform(click());
    }
}