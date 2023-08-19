package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.List;

/*
Интерфейс, описывающий хранилище рейтингов фильмов
*/

public interface MpaStorage {

    List<Mpa> get();

    Mpa getMpaById(int id);
}
