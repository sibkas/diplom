package ru.iteco.fmhandroid.data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TestData {

    // Форматировщики
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    // Данные для формы создания новости (динамические)
    public static final String NEWS_DATE = LocalDate.now().format(DATE_FORMATTER);
    public static final String NEWS_TIME = LocalTime.now().plusHours(1).format(TIME_FORMATTER);

    public static final String NEWS_CATEGORY = "Объявление";
    public static final String NEWS_TITLE = "Тест " + System.currentTimeMillis();
    public static final String NEWS_DESCRIPTION = "Это описание тестовой новости для диплома";
    public static final String EMPTY = "";

    // Категории
    public static final String CATEGORY_MASSAGE = "Массаж";

    // Даты
    public static String getTodayDate() {
        return LocalDate.now().format(DATE_FORMATTER);
    }
    public static String getPastDate() {
        return LocalDate.now().minusYears(10).format(DATE_FORMATTER);
    }

    // Данные авторизации
    public static final String VALID_LOGIN = "login2";
    public static final String VALID_PASSWORD = "password2";
    public static final String INVALID_LOGIN = "wrong_login";
    public static final String INVALID_PASSWORD = "wrong_password";
}