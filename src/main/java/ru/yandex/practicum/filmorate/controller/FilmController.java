package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    private final Map<Integer, Film> films = new HashMap<>();
    private int id = 0;

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        validate(film);
        film.setId(++id);
        films.put(film.getId(), film);
        log.info("Фильм {} был добавлен", film.getName());
        return film;
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        validate(film);
        if (!films.containsKey(film.getId())) {
            throw new ValidationException("Фильм с таким id не найден");
        }
        films.put(film.getId(), film);
        log.info("Фильм {} был добавлен", film.getName());
        return film;
    }

    @GetMapping
    public List<Film> get() {
        return List.copyOf(films.values());
    }

    public void validate(Film film) {
        if (film.getName() == null || film.getName().isBlank()) {
            log.error("Название фильма пустое");
            throw new ValidationException("Название фильма не может быть пустым");
        }
        if (film.getDescription().length() > 200) {
            log.error("Превышена максимальная длина описания фильма");
            throw new ValidationException("Описание фильма не может превышать 200 символов");
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, Month.DECEMBER, 28))) {
            log.error("Дата релиза фильма раньше допустимого порога");
            throw new ValidationException("Дата релиза не должна быть ранее 28.12.1895");
        }
        if (film.getDuration() <= 0) {
            log.error("Продолжительность фильма - не положительное число");
            throw new ValidationException("Продолжительность фильма должна быть положительна");
        }
    }
}
