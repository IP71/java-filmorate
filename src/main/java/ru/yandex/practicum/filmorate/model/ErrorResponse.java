package ru.yandex.practicum.filmorate.model;

/*
Класс, описывающий объект, возвращаемый в ответ на запрос в случае ошибки
*/

public class ErrorResponse {

    private final String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
