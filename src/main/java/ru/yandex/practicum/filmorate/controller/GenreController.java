package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.GenreStorage;

import java.util.List;

/*
Класс-контроллер для работы с жанрами
Метод get() возвращает информацию о всех жанрах фильмов (GET /genres)
Метод getGenreById(int id) возвращает информацию о жанре по id (GET /genres/{id})
*/

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreStorage storage;

    @Autowired
    public GenreController(GenreStorage storage) {
        this.storage = storage;
    }

    @GetMapping
    public List<Genre> get() {
        return storage.get();
    }

    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable int id) {
        return storage.getGenreById(id);
    }
}
