package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

/*
Интерфейс, описывающий хранилище фильмов
*/

public interface FilmStorage {

    Film create(Film film);

    Film update(Film film);

    List<Film> get();

    Film getFilmById(int id);
}
