package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Класс-контроллер для работы с фильмами
@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    private final Map<Integer, Film> films = new HashMap<>();
    private int id = 0;

    // Метод, обрабатывающий POST запрос (добавление фильма)
    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        Validator.validateFilm(film);
        film.setId(++id);
        films.put(film.getId(), film);
        log.info("Фильм {} был добавлен", film.getName());
        return film;
    }

    // Метод, обрабатывающий PUT запрос (обновление фильма)
    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        Validator.validateFilm(film);
        if (!films.containsKey(film.getId())) {
            throw new ValidationException("Фильм с таким id не найден");
        }
        films.put(film.getId(), film);
        log.info("Фильм {} был добавлен", film.getName());
        return film;
    }

    // Метод, обрабатывающий GET запрос (получение всех фильмов)
    @GetMapping
    public List<Film> get() {
        return List.copyOf(films.values());
    }
}
