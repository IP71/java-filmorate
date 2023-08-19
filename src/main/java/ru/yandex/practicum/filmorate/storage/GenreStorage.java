package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Genre;

import java.util.List;

/*
Интерфейс, описывающий хранилище жанров фильмов
*/

public interface GenreStorage {

    List<Genre> get();

    Genre getGenreById(int id);
}
